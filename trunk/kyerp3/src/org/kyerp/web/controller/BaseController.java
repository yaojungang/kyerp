/**
 * 
 */
package org.kyerp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8上午10:15:20
 */
@Controller
public class BaseController {
	@RequestMapping("/index.html")
	private void index(ModelMap model) {
	}

}
