package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import common.HrmConstants;
import domain.Notice;
import domain.User;
import service.HrmService;
import tag.PageModel;

@Controller
public class NoticeController {
	
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/*
	 * ����ģ����ѯ��������
	 */
	@RequestMapping(value="/notice/selectNotice")
	public String selectNotice(Integer pageIndex,@ModelAttribute Notice notice,Model model){
		PageModel pageModel=new PageModel();
		if(pageIndex!=null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Notice> notices=hrmService.findNotice(notice, pageModel);
		model.addAttribute("notices", notices);
		model.addAttribute("pageModel", pageModel);
		return "notice/notice";
	}
	
	/*
	 * �������id��ѯ��������
	 */
	@RequestMapping(value="/notice/previewNotice")
	public String previewNotice(Integer id,Model model){
		Notice notice=hrmService.findNoticeById(id);
		model.addAttribute("notice", notice);
		return "notice/previewNotice";
	}
	
	/*
	 * ����ɾ����������
	 */
	@RequestMapping(value="/notice/removeNotice")
	public ModelAndView removeNotice(String ids,ModelAndView mv){
		String[] idArray=ids.split(",");
		for(String id:idArray){
			hrmService.removeNoticeById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/notice/selectNotice");
		return mv;
	}
	
	/*
	 * ������ӹ�������
	 */
	@RequestMapping(value="/notice/addNotice")
	public ModelAndView addNotice(String flag,@ModelAttribute Notice notice,ModelAndView mv,HttpSession session){
		if(flag.equals("1")){
			mv.setViewName("notice/showAddNotice");
		}else{
			User user=(User)session.getAttribute(HrmConstants.USER_SESSION);
			notice.setUser(user);
			hrmService.addNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
		}
		return mv;
	}
	
	/*
	 * ������¹�������
	 */
	@RequestMapping(value="/notice/updateNotice")
	public ModelAndView updateNotcie(String flag,@ModelAttribute Notice notice,ModelAndView mv){
		if(flag.equals("1")){
			Notice target=hrmService.findNoticeById(notice.getId());
			mv.addObject("notice", target);
			mv.setViewName("notice/showUpdateNotice");
		}else{
			hrmService.modifyNotice(notice);
			mv.setViewName("redirect:/notice/selectNotice");
		}
		return mv;
	}
}
