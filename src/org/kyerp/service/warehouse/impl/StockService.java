package org.kyerp.service.warehouse.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.domain.warehouse.OutStockDetail;
import org.kyerp.domain.warehouse.Stock;
import org.kyerp.domain.warehouse.StockDetail;
import org.kyerp.service.warehouse.IInventoryOwnerService;
import org.kyerp.service.warehouse.IStockDetailService;
import org.kyerp.service.warehouse.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StockService extends DaoSupport<Stock> implements IStockService {
	@Autowired
	IStockDetailService stockDetailService;
	@Autowired
	IInventoryOwnerService inventoryOwnerService;

	public Stock findStockByMaterialIdAndOwnerId(Long materailId, Long ownerId) {
		String wherejpql = "o.material.id = " + materailId
				+ " and o.owner.id = " + ownerId;
		logger.debug("查找库存记录,jpql=" + wherejpql);
		List<Stock> stocks = getScrollData(wherejpql, null, null)
				.getResultlist();
		if (stocks.size() > 0) {
			logger.debug("找到了的库存记录,jpql=" + wherejpql);
			return stocks.get(0);
		} else {
			logger.debug("没有找到库存记录，创建了一条库存记录,jpql=" + wherejpql);
			return null;
		}
	}

	@Override
	/**
	 * 思路：先查找库存记录
	 */
	public StockDetail inStock(InStockDetail inStockDetail) throws Exception {
		StockDetail stockDetail = stockDetailService.find(inStockDetail
				.getOwner().getId(), inStockDetail.getMaterial().getId(),
				inStockDetail.getBatchNumber(), inStockDetail.getWarehouse()
						.getId(), inStockDetail.getPrice());
		Stock stock = null;
		if (null != stockDetail) {
			logger.debug("库中存在同批次、同Id、同库房的物料，直接在相应批次上加数"
					+ inStockDetail.toString());
			stock = stockDetail.getStock();
			stockDetail = stockDetailService.in(stockDetail, inStockDetail);
		} else {
			stock = findStockByMaterialIdAndOwnerId(inStockDetail.getMaterial()
					.getId(), inStockDetail.getOwner().getId());
			if (null != stock && stock.getId() > 0) {
				logger.debug("库中存在同ownerId ,同物料ID的记录");
				logger.debug("库中不存在同批次、同Id、同库房的物料，入库一个新批次"
						+ inStockDetail.toString());
				stockDetail = stockDetailService
						.addStockDetailfromInstockDetail(stock, inStockDetail);
			} else {
				logger.debug("库中不存在同ownerId ,同物料ID的记录");
				stock = add(inStockDetail);
				stockDetail = stock.getStockDetails().get(0);
			}
		}

		if (0 == updateAmountPriceAndCost(stock)) {
			logger.debug("入库后，库存物料数量为零，删除库存记录 " + stock.toString());
			delete(stock.getId());
		} else {
			logger.debug("更新库存记录 " + stock.toString());
			update(stock);
		}
		return stockDetail;
	}

	@Override
	public StockDetail outStock(OutStockDetail outStockDetail) throws Exception {
		StockDetail stockDetail = stockDetailService.find(outStockDetail
				.getOwner().getId(), outStockDetail.getMaterial().getId(),
				outStockDetail.getBatchNumber(), outStockDetail.getWarehouse()
						.getId(), outStockDetail.getPrice());
		if (null != stockDetail) {
			stockDetail = stockDetailService.out(stockDetail, outStockDetail);
			Stock stock = stockDetail.getStock();
			if (0 == updateAmountPriceAndCost(stock)) {
				logger.debug("出库后，库存物料数量为零，删除库存记录 " + stock.toString());
				delete(stockDetail.getStock().getId());
			} else {
				logger.debug("保存库存记录 " + stock);
				update(stock);
			}
			return stockDetail;
		} else {
			// throw new Exception("没有找到相应的库存批次,请核对物料所有者，名称、批次号、库房,价格");
			logger.debug("没有找到相应的库存批次,请核对物料所有者，名称、批次号、库房,价格");
			return stockDetailService
					.createStockDetailfromInventoryDetail(outStockDetail);
		}
	}

	public void updateAmountPriceAndCost(Long stockId) throws Exception {
		Stock stock = find(stockId);
		if (null != stock && stock.getId() > 0) {
			if (0 == updateAmountPriceAndCost(stock)) {
				delete(stock.getId());
			} else {
				update(stock);
			}

		} else {
			throw new Exception("没有找到Id为：" + stockId + " 的库存记录");
		}

	}

	/**
	 * 返回 总数量与0的比值 0:库存量为零 1:库存量为正数 -1:库存量为负数数
	 */
	public int updateAmountPriceAndCost(Stock stock) throws Exception {
		logger.debug("更新数量，金额和单价，并删除记录为零的批次");
		if (null != stock.getStockDetails()
				&& stock.getStockDetails().size() > 0) {
			stock.setTotalAmount(new BigDecimal("0.0000").setScale(4,
					BigDecimal.ROUND_HALF_UP));
			stock.setCost(new BigDecimal("0.0000").setScale(4,
					BigDecimal.ROUND_HALF_UP));
			// 更新数量和总金额
			for (StockDetail detail : stock.getStockDetails()) {
				stock.setTotalAmount(stock.getTotalAmount().add(
						detail.getAmount()));
				stock.setCost(stock.getCost().add(detail.getCost()));
			}
			// 更新价格（平均价格）
			// logger.info("stock:"+stock.toString());
			if (1 == stock.getCost().compareTo(BigDecimal.ZERO)
					&& 1 == stock.getTotalAmount().compareTo(BigDecimal.ZERO)) {
				stock.setPrice(stock.getCost().divide(stock.getTotalAmount(),
						BigDecimal.ROUND_HALF_UP));
			} else {
				stock.setPrice(BigDecimal.ZERO);
			}
			// 删除数量为零 的批次的库存记录
			List<StockDetail> stockDetails = stock.getStockDetails();
			Iterator<StockDetail> iterator = stockDetails.iterator();
			while (iterator.hasNext()) {
				StockDetail stockDetail = iterator.next();
				if (0 == BigDecimal.ZERO.compareTo(stockDetail.getAmount())) {
					iterator.remove();
					logger.debug("删除数量为零的库存批次" + stockDetail.toString());
					stockDetailService.delete(stockDetail.getId());
				}
			}
			stock.setStockDetails(stockDetails);
			return stock.getTotalAmount().compareTo(BigDecimal.ZERO);
		} else {
			logger.debug("库存记录中没有具体的批次，不能更新  stockdetails size:"
					+ stock.getStockDetails().size());
			throw new Exception("库存记录中没有具体的批次，不能更新");
		}

	}

	@Override
	public Stock add(InStockDetail inStockDetail) throws Exception {
		if (null != findStockByMaterialIdAndOwnerId(inStockDetail.getMaterial()
				.getId(), inStockDetail.getOwner().getId())) {
			throw new Exception("owner Id=" + inStockDetail.getOwner().getId()
					+ "material Id=" + inStockDetail.getMaterial().getId()
					+ "的库存记录已经存在，不用创建");
		} else {
			Stock stock = new Stock();
			stock.setOwner(inventoryOwnerService.find(inStockDetail
					.getInStock().getId()));
			stock.setMaterial(inStockDetail.getMaterial());
			stock.setOwner(inStockDetail.getOwner());
			stock.setUnit(inStockDetail.getMaterial().getUnit());
			stock.setTotalAmount(inStockDetail.getInStockCount());
			stock.setPrice(inStockDetail.getPrice());
			stock.setCost(inStockDetail.getCost());
			stock.setRemark(inStockDetail.getRemark());
			StockDetail stockDetailNew = stockDetailService
					.createStockDetailfromInventoryDetail(inStockDetail);
			stockDetailNew.setStock(stock);
			List<StockDetail> stockDetails = stock.getStockDetails();
			stockDetails.add(stockDetailNew);
			stock.setStockDetails(stockDetails);
			save(stock);
			logger.debug("创建了一条新的库存记录" + stock.toString());
			return stock;
		}
	}

	@Override
	public Stock add(OutStockDetail outStockDetail) throws Exception {
		if (null != findStockByMaterialIdAndOwnerId(outStockDetail
				.getMaterial().getId(), outStockDetail.getOwner().getId())) {
			throw new Exception("owner Id=" + outStockDetail.getOwner().getId()
					+ "material Id=" + outStockDetail.getMaterial().getId()
					+ "的库存记录已经存在，不用创建");
		} else {
			Stock stock = new Stock();
			stock.setOwner(outStockDetail.getOwner());
			stock.setMaterial(outStockDetail.getMaterial());
			stock.setUnit(outStockDetail.getMaterial().getUnit());
			stock.setTotalAmount(outStockDetail.getInStockCount());
			stock.setPrice(outStockDetail.getPrice());
			stock.setCost(outStockDetail.getCost());
			stock.setRemark(outStockDetail.getRemark());
			StockDetail stockDetailNew = stockDetailService
					.createStockDetailfromInventoryDetail(outStockDetail);
			stockDetailNew.setStock(stock);
			List<StockDetail> stockDetails = stock.getStockDetails();
			stockDetails.add(stockDetailNew);
			stock.setStockDetails(stockDetails);
			save(stock);
			logger.debug("创建了一条新的库存记录" + stock.toString());
			return stock;
		}
	}

	@Override
	public void removeZeroStock(Stock stock) throws Exception {
		if (0 == updateAmountPriceAndCost(stock)) {
			logger.debug("入库后，库存物料数量为零，删除库存记录 " + stock.toString());
			delete(stock.getId());
		} else {
			logger.debug("更新库存记录 " + stock.toString());
			update(stock);
		}
	}
}