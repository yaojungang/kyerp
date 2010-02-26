package org.kyerp.web.controller.warehouse;

import java.io.Serializable;

import net.sf.json.JSONObject;

import org.kyerp.service.warehouse.IMaterialBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8下午03:36:16
 */
@Controller
public class MaterialBatchController {
	@Autowired
	IMaterialBatchService	materialBatchService;

	@Secured( { "ROLE_ADMIN" })
	@RequestMapping("/warehouse/MaterialBatch/jsonDelete.html")
	public String delete(ModelMap model, Long[] ids) {
		materialBatchService.delete((Serializable[]) ids);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		String text = "";
		try {
			text = jsonObject.toString();
			System.out.println(text);
		} catch (Exception e) {
			text = "";
		}
		model.addAttribute("jsonText", text);
		return "share/jsonTextView";
	}
}
