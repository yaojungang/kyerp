package org.kyerp.web.controller.print;

import javax.annotation.Resource;

import org.kyerp.domain.common.view.PageView;
import org.kyerp.domain.common.view.QueryResult;
import org.kyerp.domain.print.Presswork;
import org.kyerp.service.print.IPressworkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8上午10:16:43
 */
@Controller
public class PressworkController {
	@Resource(name = "pressworkService")
	IPressworkService	pressworkService;

	@RequestMapping("/print/Presswork/index.html")
	public void list(ModelMap model, Integer page) {
		page = null == page || page < 1 ? 1 : page;
		PageView<Presswork> pageView = new PageView<Presswork>(12, page);
		QueryResult<Presswork> qureyResult = pressworkService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(qureyResult);
		model.addAttribute("pageView", pageView);
	}
}
