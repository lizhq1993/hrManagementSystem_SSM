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
	 * ��½����
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
			mv.setViewName("redirect:/main"); //�ͻ�����ת
		}else{
			mv.addObject("message", "��¼���������������������");
			mv.setViewName("forward:/loginForm"); //��������ת
		}
		return mv;
	}
	
	/*
	 * ע���˳�
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
	 * ģ����ѯuser
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
	 * ����ɾ���û�����
	 */
	@RequestMapping(value="/user/removeUser")
	public ModelAndView removeUser(String ids,ModelAndView mv){  //Ϊʲô����@RequestParam
		String[] idArray=ids.split(",");
		for(String id:idArray){
			hrmService.removeUserById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/user/selectUser");
		return mv;
	}
	
	/*
	 * �����޸��û�����
	 * @param String flag��ǣ�1��ʾ��ת���޸�ҳ�棬2��ʾִ���޸Ĳ���
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
	 * �����������
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
