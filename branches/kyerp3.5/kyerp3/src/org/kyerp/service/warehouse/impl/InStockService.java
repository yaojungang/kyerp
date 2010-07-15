package org.kyerp.service.warehouse.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.BillStatus;
import org.kyerp.domain.warehouse.InStock;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.domain.warehouse.OutStock;
import org.kyerp.domain.warehouse.OutStockDetail;
import org.kyerp.domain.warehouse.Stock;
import org.kyerp.domain.warehouse.StockDetail;
import org.kyerp.service.warehouse.IInOutTypeService;
import org.kyerp.service.warehouse.IInStockDetailService;
import org.kyerp.service.warehouse.IInStockService;
import org.kyerp.service.warehouse.IOutStockDetailService;
import org.kyerp.service.warehouse.IOutStockService;
import org.kyerp.service.warehouse.IStockDetailService;
import org.kyerp.service.warehouse.IStockService;
import org.kyerp.utils.SerialNumberHelper;
import org.kyerp.utils.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
@Transactional
public class InStockService extends DaoSupport<InStock> implements IInStockService{
	@Autowired
	IStockService			stockService;
	@Autowired
	IStockDetailService		stockDetailService;
	@Autowired
	IInStockDetailService	inStockDetailService;
	@Autowired
	IOutStockDetailService	outStockDetailService;
	@Autowired
	IInOutTypeService		inOutTypeService;
	@Autowired
	IOutStockService		outStockService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveInStock(InStock e) throws Exception {
		// 设置单据状态
		e.setStatus(BillStatus.WRITING);
		// 设置填单人
		if(null == e.getWriteUser()) {
			e.setWriteUser(WebUtil.getCurrentUser());
			e.setWriteEmployee(WebUtil.getCurrentEmployee());
		}
		// 设置填单时间
		if(null == e.getWriteDate()) {
			e.setWriteDate(new Date());
		}
		if(null == e.getSerialNumber() || e.getSerialNumber().length() == 0) {
			// 如果没有填写单号则设置单号
			e.setSerialNumber(nextSerialNumber());
		}
		save(e);
	}

	/*
	 * 审核入库单 改变状态为已审核,设置审核人，审核时间
	 * 
	 * @see org.kyerp.service.warehouse.IInStockService#checkInStock(
	 * org.kyerp.domain.warehouse.InStock)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String checkInStock(Long inStockId) throws Exception {
		InStock inStock = find(inStockId);
		if(BillStatus.CHECKED == inStock.getStatus()) {
			return "该单据已经审核过，不能再审核。";
		}

		// 设置单据状态
		inStock.setStatus(BillStatus.CHECKED);
		// 设置审核日期
		inStock.setCheckDate(new Date());
		// 设置审核人
		inStock.setCheckUser(WebUtil.getCurrentUser());
		inStock.setCheckEmployee(WebUtil.getCurrentEmployee());

		List<Stock> stockList = new ArrayList<Stock>();

		// 循环取出入库单明细里的每个条目，构建库存操作队列
		for (InStockDetail inStockDetail : inStock.getDetails()) {
			boolean flag = false;
			if(inStock.getDetails().size() == 0) {
				return "至少需要一条入库项目！";
			}
			Stock stock = new Stock();
			stock.setMaterial(inStockDetail.getMaterial());
			stock.setUnit(inStockDetail.getUnit());
			String wherejpql = "o.material.id=" + inStockDetail.getMaterial().getId();
			if(stockService.getScrollData(wherejpql, null, null).getTotalrecord() > 0) {
				logger.debug("查询到库存记录jpql:" + wherejpql);
				stock = stockService.getScrollData(wherejpql, null, null).getResultlist().get(0);
			}
			if(stockList.size() == 0) {
				flag = true;
			}
			if(stockList.size() > 0) {
				for (int i = 0; i < stockList.size(); i++) {
					Stock stock0 = (Stock) stockList.get(i);
					if(null != stock.getMaterial() && stock.getMaterial().getId().compareTo(stock0.getMaterial().getId()) == 0) {
						flag = false;
						break;
					} else {
						flag = true;
					}
				}
			}

			if(flag) {
				logger.debug("物料ID：" + stock.getMaterial().getId() + "  加入库存队列！");
				stockList.add(stock);
			}
		}
		for (Stock stock : stockList) {
			if(null != stock.getId() && stock.getId() > 0) {
				logger.debug("更新Stock" + stock.getId());
				stockService.update(stock);
			} else {
				logger.debug("保存Stock");
				stockService.save(stock);
			}
		}
		// 循环取出入库单明细里的每个条目,加入到库存操作队列里
		for (InStockDetail inStockDetail : inStock.getDetails()) {
			for (Stock stock : stockList) {
				if(inStockDetail.getMaterial().getId().compareTo(stock.getMaterial().getId()) == 0) {
					// 查询库存明细表中是否存在 同批次号、同库房的库存记录，如果存在则选中
					String wherejpql2 = "o.batchNumber='" + inStockDetail.getBatchNumber() + "' and o.warehouse.id=" + inStockDetail.getWarehouse().getId();
					StockDetail stockDetail = new StockDetail();
					if(stockDetailService.getScrollData(wherejpql2, null, null).getTotalrecord() > 0) {
						logger.debug("查询到库存明细表中存在 同批次号、同库房的库存记录jpql:" + wherejpql2);
						stockDetail = stockDetailService.getScrollData(wherejpql2, null, null).getResultlist().get(0);
						stockDetail.setStock(stock);
						updateStockAmountCostAndPrice(inStockDetail, stock);

						// 更新这个批次并且存放在这个库房的物料的数量
						stockDetail.setAmount(stockDetail.getAmount().add(inStockDetail.getInStockCount()));
						// 如果库存总数为0则删除这条库存记录，否则更新库存金额和绝对平均价格
						if(BigDecimal.ZERO.compareTo(stockDetail.getAmount()) == 0) {
							logger.debug("删除库存记录：" + stockDetail.getBatchNumber() + ";stockDetail id:" + stockDetail.getId());
							stockDetail.setStock(null);
							stock.getStockDetails().remove(stockDetail);
							stockDetailService.delete(stockDetail.getId());
							// stockDetailService.update(stockDetail);
						} else {
							stockDetail.setCost(stockDetail.getCost().add(inStockDetail.getBillCost()));
							stockDetail.setPrice(stockDetail.getCost().divide(stockDetail.getAmount(), 4, BigDecimal.ROUND_HALF_UP));
							stockDetailService.update(stockDetail);
						}
					} else {
						logger.debug("没有查询到库存明细表中存在 同批次号、同库房的库存记录.");
						stockDetail.setStock(stock);

						updateStockAmountCostAndPrice(inStockDetail, stock);

						// 如果没有设置批次号，则设置批次号
						if(StringUtils.hasText(inStockDetail.getBatchNumber())) {
							stockDetail.setBatchNumber(inStockDetail.getBatchNumber());
						} else {
							// 如果没有填写批次号
							String jpql = "select count(o) from StockDetail o where o.stock.id=" + stock.getId() + " and o.createTime>=?1";
							try {
								stockDetail.setBatchNumber(SerialNumberHelper.buildSerialNumber(em, jpql));
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
						}

						stockDetail.setWarehouse(inStockDetail.getWarehouse());
						stockDetail.setAmount(inStockDetail.getInStockCount());
						stockDetail.setUnit(inStockDetail.getUnit());
						stockDetail.setPrice(inStockDetail.getPrice());
						stockDetail.setCost(inStockDetail.getBillCost());
						stockDetailService.save(stockDetail);
					}
				}
			}
		}
		for (Stock stock : stockList) {
			if(null != stock.getId() && stock.getId() > 0) {
				logger.debug("更新Stock,Id:" + stock.getId());
				stockService.update(stock);

				for (StockDetail detail : stock.getStockDetails()) {
					if(BigDecimal.ZERO.compareTo(detail.getAmount()) == 0) {
						// logger.debug("删除 detail:" + detail.getId());
						// stockDetailService.delete(detail.getId());
					}
				}

			} else {
				logger.debug("保存Stock");
				stockService.save(stock);
			}
		}
		update(inStock);
		return "success";
	}

	@Transactional(rollbackFor = Exception.class)
	public String checkInStock1(Long inStockId) throws Exception {
		InStock inStock = find(inStockId);
		if(BillStatus.CHECKED == inStock.getStatus()) {
			return "该单据已经审核过，不能再审核。";
		}

		// 设置单据状态
		inStock.setStatus(BillStatus.CHECKED);
		// 设置审核日期
		inStock.setCheckDate(new Date());
		// 设置审核人
		inStock.setCheckUser(WebUtil.getCurrentUser());
		inStock.setCheckEmployee(WebUtil.getCurrentEmployee());

		// 循环取出入库单明细里的每个条目
		for (InStockDetail inStockDetail : inStock.getDetails()) {
			if(inStock.getDetails().size() == 0) {
				return "至少需要一条入库项目！";
			}
			Stock stock = new Stock();
			stock.setMaterial(inStockDetail.getMaterial());
			stock.setUnit(inStockDetail.getUnit());
			String wherejpql = "o.material.id=" + inStockDetail.getMaterial().getId();
			if(stockService.getScrollData(wherejpql, null, null).getTotalrecord() > 0) {
				logger.debug("查询到库存记录jpql:" + wherejpql);
				stock = stockService.getScrollData(wherejpql, null, null).getResultlist().get(0);

				// 查询库存明细表中是否存在 同批次号、同库房的库存记录，如果存在则选中
				String wherejpql2 = "o.batchNumber='" + inStockDetail.getBatchNumber() + "' and o.warehouse.id=" + inStockDetail.getWarehouse().getId();
				StockDetail stockDetail = new StockDetail();
				if(stockDetailService.getScrollData(wherejpql2, null, null).getTotalrecord() > 0) {
					logger.debug("查询到库存明细表中存在 同批次号、同库房的库存记录jpql:" + wherejpql2);
					stockDetail = stockDetailService.getScrollData(wherejpql2, null, null).getResultlist().get(0);
					stockDetail.setStock(stock);
					// updateStockAmountCostAndPrice(inStockDetail, stock);

					// 更新这个批次并且存放在这个库房的物料的数量
					// stockDetail.setAmount(stockDetail.getAmount().add(inStockDetail.getInStockCount()));
					// 如果库存总数为0则删除这条库存记录，否则更新库存金额和绝对平均价格
					if(BigDecimal.ZERO.compareTo(stockDetail.getAmount()) == 0) {
						logger.debug("删除库存记录：" + stockDetail.getBatchNumber());
						stockDetailService.delete(stockDetail.getId());
					} else {
						stockDetail.setCost(stockDetail.getCost().add(inStockDetail.getBillCost()));
						stockDetail.setPrice(stockDetail.getCost().divide(stockDetail.getAmount(), 4, BigDecimal.ROUND_HALF_UP));
						stockDetailService.update(stockDetail);
					}
				} else {
					logger.debug("没有查询到库存明细表中存在 同批次号、同库房的库存记录.");
					stockDetail.setStock(stock);

					updateStockAmountCostAndPrice(inStockDetail, stock);

					// 如果没有设置批次号，则设置批次号
					if(StringUtils.hasText(inStockDetail.getBatchNumber())) {
						stockDetail.setBatchNumber(inStockDetail.getBatchNumber());
					} else {
						// 如果没有填写批次号
						String jpql = "select count(o) from StockDetail o where o.stock.id=" + stock.getId() + " and o.createTime>=?1";
						try {
							stockDetail.setBatchNumber(SerialNumberHelper.buildSerialNumber(em, jpql));
						} catch (ParseException e1) {
							e1.printStackTrace();
						}
					}

					stockDetail.setWarehouse(inStockDetail.getWarehouse());
					stockDetail.setAmount(inStockDetail.getInStockCount());
					stockDetail.setUnit(inStockDetail.getUnit());
					stockDetail.setPrice(inStockDetail.getPrice());
					stockDetail.setCost(inStockDetail.getBillCost());
					stockDetailService.save(stockDetail);
				}
				logger.debug("更新Stock,Id:" + stock.getId());
				stockService.update(stock);
			} else {
				logger.debug("没有查询到库存记录jpql:" + wherejpql);
				logger.debug("保存Stock");
				stockService.save(stock);
			}

		}

		update(inStock);
		return "success";
	}

	@Transactional(rollbackFor = Exception.class)
	private void updateStockAmountCostAndPrice(InStockDetail inStockDetail, Stock stock) {
		logger.debug("更新库存表的总数量");
		logger.debug("更新的数量=" + inStockDetail.getInStockCount());
		logger.debug("更新前的总数量=" + stock.getTotalAmount());
		stock.setTotalAmount(stock.getTotalAmount().add(inStockDetail.getInStockCount()));
		logger.debug("更新后的总数量=" + stock.getTotalAmount());
		// 如果库存总数为0则删除这条库存记录，否则更新库存金额和绝对平均价格
		if(BigDecimal.ZERO.compareTo(stock.getTotalAmount()) == 0) {
			logger.debug("库存总数为0,删除这条库存记录");
			stockService.delete(stock.getId());
		} else {
			logger.debug("更新库存金额和绝对平均价格");
			stock.setCost(stock.getCost().add(inStockDetail.getBillCost()));
			stock.setPrice(stock.getCost().divide(stock.getTotalAmount(), 4, BigDecimal.ROUND_HALF_UP));
			// logger.debug("before update stock:" + stock.getTotalAmount());
			stockService.update(stock);
			// logger.debug("after update stock:" + stock.getTotalAmount());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kyerp.service.warehouse.IInStockService#nextSerialNumber()
	 */
	@Override
	public String nextSerialNumber() throws Exception {
		int serno = 1;
		String head = "RK" + new SimpleDateFormat("yyyy").format(new Date());
		StringBuffer wherejpql = new StringBuffer("");
		List<Object> queryParams = new ArrayList<Object>();
		wherejpql.append(" o.serialNumber like ?").append((queryParams.size() + 1));
		queryParams.add(head + "%");
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("serialNumber", "desc");
		if(getScrollData(wherejpql.toString(), queryParams.toArray(), orderby).getTotalrecord() > 0) {
			InStock inStock = getScrollData(wherejpql.toString(), queryParams.toArray(), orderby).getResultlist().get(0);
			if(inStock != null) {
				String code = inStock.getSerialNumber();
				serno = Integer.parseInt(code.substring(head.length())) + 1;
			}
		}
		return head + new DecimalFormat("0000").format(serno);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kyerp.service.warehouse.IInStockService#congXiao(java.lang.Long)
	 */
	@Override
	public String congXiao(Long inStockId) throws Exception {

		InStock inStock = find(inStockId);
		if(null != inStock) {
			if(BillStatus.CONGXIAO.equals(inStock.getStatus())) {
				return "已经冲销，不能再次冲销！";
			} else {
				OutStock outStock = new OutStock();
				// 设置收发类型为 "冲销入库"
				outStock.setInOutType(inOutTypeService.find(11L));
				outStock.setWriteDate(new Date());
				outStock.setWriteEmployee(WebUtil.getCurrentEmployee());
				outStock.setKeeper(WebUtil.getCurrentEmployee());
				outStock.setRemark("入库单：" + outStock.getSerialNumber() + "的冲销单");
				outStock.setStatus(BillStatus.WAITING_FOR_CHECK);
				// 设置出库部门，与出库人
				outStock.setReceiveDepartment(WebUtil.getCurrentEmployee().getDepartment());
				outStock.setReceiveEmployee(WebUtil.getCurrentEmployee());
				outStock.setKeeper(WebUtil.getCurrentEmployee());

				outStockService.saveOutStock(outStock);
				List<OutStockDetail> outStockDetails = new ArrayList<OutStockDetail>();
				for (InStockDetail inStockDetail : inStock.getDetails()) {
					OutStockDetail outStockDetail = new OutStockDetail();
					outStockDetail.setMaterial(inStockDetail.getMaterial());
					outStockDetail.setBatchNumber(inStockDetail.getBatchNumber());
					outStockDetail.setWarehouse(inStockDetail.getWarehouse());
					outStockDetail.setPrice(inStockDetail.getPrice());
					outStockDetail.setUnit(inStockDetail.getUnit());
					outStockDetail.setOutStockCount(inStockDetail.getInStockCount());
					outStockDetail.setOutStock(outStock);
					outStockDetailService.save(outStockDetail);
					outStockDetails.add(outStockDetail);
				}
				outStock.setDetails(outStockDetails);
				outStockService.update(outStock);

				inStock.setStatus(BillStatus.CONGXIAO);
				inStock.setRemark("已冲销!出库单号：" + inStock.getSerialNumber());
				update(outStock);
				return "success";
			}
		} else {
			return "没有找到相应的入库单！";
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kyerp.service.warehouse.IInStockService#addInStockDetail(org.kyerp.domain.warehouse.InStockDetail)
	 */
	@Override
	public void addInStockDetail(InStockDetail inStockDetail) throws Exception {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kyerp.service.warehouse.IInStockService#calculateInStockCount(org.kyerp.domain.warehouse.InStock)
	 */
	@Override
	public void calculateInStockCount(InStock inStock) throws Exception {
		BigDecimal _billCountBigDecimal = new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
		BigDecimal _billCostBigDecimal = new BigDecimal("0.0000").setScale(4, BigDecimal.ROUND_HALF_UP);
		inStock.setBillCount(BigDecimal.ZERO);
		inStock.setBillCost(BigDecimal.ZERO);
		for (InStockDetail detail : inStock.getDetails()) {
			_billCountBigDecimal = inStock.getBillCount().add(detail.getInStockCount());
			_billCostBigDecimal = inStock.getBillCost().add(detail.getBillCost());
		}
		inStock.setBillCount(_billCountBigDecimal);
		inStock.setBillCost(_billCostBigDecimal);

	}
}
