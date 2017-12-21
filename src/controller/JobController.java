package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.Job;
import service.HrmService;
import tag.PageModel;

@Controller
public class JobController {
	
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/*
	 * 模糊查询job
	 */
	@RequestMapping(value="/job/selectJob")
	public String selectJob(Integer pageIndex,@ModelAttribute Job job,Model model){
		System.out.println("selectJob -->>"+job);
		PageModel pageModel=new PageModel();
		if(pageIndex!=null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Job> jobs=hrmService.findJob(job, pageModel);
		model.addAttribute("jobs", jobs);
		model.addAttribute("pageModel", pageModel);
		return "job/job";
	}
	
	/*
	 * 处理删除职位请求
	 */
	@RequestMapping(value="/job/removeJob")
	public ModelAndView removeJob(String ids,ModelAndView mv){
		String[] idArray=ids.split(",");
		for(String id:idArray){
			hrmService.removeJobById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/job/selectJob");
		return mv;
	}
	
	/*
	 * 处理添加职位请求
	 */
	@RequestMapping(value="/job/addJob")
	public ModelAndView addJob(String flag,@ModelAttribute Job job,ModelAndView mv){
		if(flag.equals("1")){
			mv.setViewName("job/showAddJob");
		}else{
			hrmService.addJob(job);
			mv.setViewName("redirect:/job/selectJob");
		}
		return mv;
	}
	
	/*
	 * 处理修改职位请求
	 */
	@RequestMapping(value="/job/updateJob")
	public ModelAndView updateJob(String flag,@ModelAttribute Job job,ModelAndView mv){
		if(flag.equals("1")){
			Job target=hrmService.findJobById(job.getId());
			mv.addObject("job", target);
			mv.setViewName("job/showUpdateJob");
		}else{
			hrmService.modifyJob(job);
			mv.setViewName("redirect:/job/selectJob");
		}
		return mv;
	}
}
