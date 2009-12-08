package org.kyerp.web.controller.print;

import javax.annotation.Resource;

import org.kyerp.domain.base.views.PageView;
import org.kyerp.domain.base.views.QueryResult;
import org.kyerp.domain.print.Presswork;
import org.kyerp.service.print.IPressworkService;
import org.kyerp.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author y109 2009-12-8上午10:16:43
 */
@Controller
public class PressworkController extends BaseController {
	@Resource(name = "pressworkService")
	IPressworkService	pressworkService;

	@RequestMapping("/print/Presswork/list.html")
	public void list(ModelMap model, Integer page) {
		page = null == page || page < 1 ? 1 : page;
		PageView<Presswork> pageView = new PageView<Presswork>(12, page);
		QueryResult<Presswork> qureyResult = pressworkService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult());
		pageView.setQueryResult(qureyResult);
		model.addAttribute("pageView", pageView);
	}
}
