package org.kyerp.service.warehouse.impl;

import java.util.Date;

import org.kyerp.dao.DaoSupport;
import org.kyerp.domain.warehouse.InStock;
import org.kyerp.domain.warehouse.InStockDetail;
import org.kyerp.domain.warehouse.OutStockDetail;
import org.kyerp.service.warehouse.IInOutTypeService;
import org.kyerp.service.warehouse.IInStockService;
import org.kyerp.service.warehouse.IInventoryOwnerService;
import org.kyerp.service.warehouse.IOutStockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author y109 2009-11-30上午02:26:14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OutStockDetailService extends DaoSupport<OutStockDetail> implements
		IOutStockDetailService {
	@Autowired
	IInStockService inStockService;
	@Autowired
	IInOutTypeService inOutTypeService;
	@Autowired
	IInventoryOwnerService inventoryOwnerService;
	@Override
	public void dealwithMoreOrLess(OutStockDetail outStockDetail)
			throws Exception {
		int lessOrMore = outStockDetail.getOutStockCount().compareTo(outStockDetail.getRealOutStockCount());
		switch (lessOrMore) {
		case -1:
			logger.debug("理论出库数量 < 实际出库数量");
			throw new Exception("理论出库数量 < 实际出库数量,请开补纸单！");
		case 1:
			logger.debug("理论出库数量 > 实际出库数量,有结余！");
			InStock inStock = new InStock();
			inStock.setInOutType(inOutTypeService.findByProperty("name","生产结余"));
			InStockDetail inStockDetail = new InStockDetail();
			inStockDetail.setBatchNumber(outStockDetail.getBatchNumber());
			inStockDetail.setHappenDate(new Date());
			inStockDetail.setOwner(inventoryOwnerService.findByProperty("name","本厂"));
			inStockDetail.setInStockCount(outStockDetail.getOutStockCount().subtract(outStockDetail.getRealOutStockCount()));
			inStockService.save(inStock);
			inStockService.checkInStock(inStock.getId());
			break;
		case 0:
			logger.debug("理论出库数量 = 实际出库数量,不用处理！");
			break;
		default:
			break;
		}
		
	}

}
