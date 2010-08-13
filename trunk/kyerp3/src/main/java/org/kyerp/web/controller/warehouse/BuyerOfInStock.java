package org.kyerp.web.controller.warehouse;

import org.kyerp.service.warehouse.IBuyerOfInStockService;
import org.kyerp.service.warehouse.IMaterialService;
import org.kyerp.service.warehouse.ISupplierService;
import org.kyerp.service.warehouse.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
@SessionAttributes("buyerOfEnteringMaterial")
public class BuyerOfInStock{
	@Autowired
	IBuyerOfInStockService	buyerOfInStockService;
	@Autowired
	ISupplierService		supplierService;
	@Autowired
	IWarehouseService		warehouseService;
	@Autowired
	IMaterialService		materialService;

	@RequestMapping("/warehouse/BuyerOfEnteringMaterial/index.html")
	public void list(ModelMap model, Integer page) {
	}
}
