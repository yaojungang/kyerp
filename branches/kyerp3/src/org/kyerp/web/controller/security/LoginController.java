package org.kyerp.web.controller.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kyerp.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *org.kyerp.web.controller.security.LoginController.java
 * 
 * @author y109
 *         2010-4-1上午09:28:51
 */
@Controller
public class LoginController extends BaseController{

	// ~~~~~~~~~~~~~~~~~~~~~~~ Action Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~//
	/**
	 * 默认起始事件，获得登录用户信息
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/loginSuccess.html")
	public void loginSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().write("{success:true}");
	}

	/**
	 * 登录失败，返回失败原因
	 */
	@RequestMapping("/loginFailed.html")
	public void loginFailed(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.getWriter().write("{success:false,errorMsg:error.login.authenticationFailed}");
	}

	/**
	 * 加载当前菜单节点的下一级菜单
	 * 
	 * @return "success"
	 */
// public String loadMenu(){
// User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
// menu = loginService.loadChildNodes(currentUser,node);
// return SUCCESS;
// }

	/**
	 * 加载当前用户的配置项
	 * 
	 * @return "success"
	 */
// public String loadSetting(){
// User cUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
// List<Setting> settings = userService.getSettings(cUser.getId());
// for(Setting setting:settings){
// if("RowsPerPage".equals(setting.getName())){
// pageSize = setting.getValue();
// }
// if("Skin".equals(setting.getName())){
// skin = setting.getValue();
// }
// }
// return SUCCESS;
// }
}
