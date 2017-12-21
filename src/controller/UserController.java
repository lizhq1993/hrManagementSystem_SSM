package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import common.HrmConstants;
import domain.User;
import service.HrmService;
import tag.PageModel;

@Controller
public class UserController {
	
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/*
	 * 登陆控制
	 */
	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam("loginname") String loginname,@RequestParam("password") String password,
			HttpSession session,ModelAndView mv){
		User user=hrmService.login(loginname, password);
		//System.out.println(user);
		if(user!=null){
			//System.out.println(user);
			session.setAttribute(HrmConstants.USER_SESSION,user);
			//User userSession=(User)session.getAttribute(HrmConstants.USER_SESSION);
			//System.out.println(userSession);
			mv.setViewName("redirect:/main"); //客户端跳转
		}else{
			mv.addObject("message", "登录名或密码错误！请重新输入");
			mv.setViewName("forward:/loginForm"); //服务器跳转
		}
		return mv;
	}
	
	/*
	 * 注销退出
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request){
		HttpSession session=request.getSession();
		User curUser=(User)session.getAttribute(HrmConstants.USER_SESSION);
		System.out.println("User "+curUser.getLoginname()+" logs out");
		session.removeAttribute(HrmConstants.USER_SESSION);
		return "redirect:/loginForm";
	}
	
	/*
	 * 模糊查询user
	 */
	@RequestMapping(value="/user/selectUser")
	public String selectUser(Integer pageIndex,@ModelAttribute User user,Model model){
		System.out.println("user="+user);
		PageModel pageModel=new PageModel();
		if(pageIndex!=null){
			pageModel.setPageIndex(pageIndex);
		}
		List<User> users=hrmService.findUser(user, pageModel);
		model.addAttribute("users",users);
		model.addAttribute("pageModel", pageModel);
		return "user/user";
	}
	
	/*
	 * 处理删除用户请求
	 */
	@RequestMapping(value="/user/removeUser")
	public ModelAndView removeUser(String ids,ModelAndView mv){  //为什么不用@RequestParam
		String[] idArray=ids.split(",");
		for(String id:idArray){
			hrmService.removeUserById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/user/selectUser");
		return mv;
	}
	
	/*
	 * 处理修改用户请求
	 * @param String flag标记，1表示跳转到修改页面，2表示执行修改操作
	 */
	@RequestMapping(value="/user/updateUser")
	public ModelAndView updateUser(String flag,@ModelAttribute User user,ModelAndView mv){
		if(flag.equals("1")){
			User target=hrmService.findUserById(user.getId());
			mv.addObject("user", target);
			mv.setViewName("user/showUpdateUser");
		}else{
			hrmService.modifyUser(user);
			mv.setViewName("redirect:/user/selectUser");
		}
		return mv;
	}
	
	/*
	 * 处理添加请求
	 */
	@RequestMapping(value="/user/addUser")
	public ModelAndView addUser(String flag,@ModelAttribute User user,ModelAndView mv){
		if(flag.equals("1")){
			mv.setViewName("user/showAddUser");
		}else{
			hrmService.addUser(user);
			mv.setViewName("redirect:/user/selectUser");
		}
		return mv;
	}
}
