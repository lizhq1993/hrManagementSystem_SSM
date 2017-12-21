package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.Dept;
import domain.Employee;
import domain.Job;
import service.HrmService;
import tag.PageModel;

@Controller
public class EmployeeController {
	
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/*
	 * 模糊查询员工信息并分页展示
	 */
	@RequestMapping(value="/employee/selectEmployee")
	public String selectEmployee(Integer pageIndex,Integer job_id,Integer dept_id,@ModelAttribute Employee employee,Model model){
		this.genericAssociation(job_id,dept_id,employee);
		//System.out.println("selectEmployee -->>"+employee);
		PageModel pageModel=new PageModel();
		if(pageIndex!=null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Job> jobs=hrmService.findAllJob();
		List<Dept> depts=hrmService.findAllDept();
		List<Employee> employees=hrmService.findEmployee(employee, pageModel);
		model.addAttribute("employees", employees);
		model.addAttribute("jobs", jobs);
		model.addAttribute("depts", depts);
		model.addAttribute("pageModel", pageModel);
		return "employee/employee";
	}
	
	/*
	 * 处理添加员工请求
	 */
	@RequestMapping(value="/employee/addEmployee")
	public ModelAndView addEmployee(String flag,Integer job_id,Integer dept_id,@ModelAttribute Employee employee,ModelAndView mv){
		if(flag.equals("1")){
			List<Job> jobs=hrmService.findAllJob();
			List<Dept> depts=hrmService.findAllDept();
			mv.addObject("jobs", jobs);
			mv.addObject("depts", depts);
			mv.setViewName("employee/showAddEmployee");
		}else{
			this.genericAssociation(job_id, dept_id, employee);
			hrmService.addEmployee(employee);
			mv.setViewName("redirect:/employee/selectEmployee");
		}
		return mv;
	}
	
	/*
	 * 处理删除员工请求
	 */
	@RequestMapping(value="/employee/removeEmployee")
	public ModelAndView removeEmployee(String ids,ModelAndView mv){
		String[] idArray=ids.split(",");
		for(String id:idArray){
			hrmService.removeEmployeeById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/employee/selectEmployee");
		return mv;
	}
	
	/*
	 * 处理修改员工请求
	 */
	@RequestMapping(value="/employee/updateEmployee")
	public ModelAndView updateEmployee(String flag,Integer job_id,Integer dept_id,@ModelAttribute Employee employee,ModelAndView mv){
		if(flag.equals("1")){
			Employee target=hrmService.findEmployeeById(employee.getId());
			List<Job> jobs=hrmService.findAllJob();
			List<Dept> depts=hrmService.findAllDept();
			mv.addObject("employee", target);
			mv.addObject("jobs", jobs);
			mv.addObject("depts", depts);
			mv.setViewName("employee/showUpdateEmployee");
		}else{
			this.genericAssociation(job_id, dept_id, employee);
			System.out.println("updateEmployee -->>"+employee);
			hrmService.modifyEmployee(employee);
			mv.setViewName("redirect:/employee/selectEmployee");
		}
		return mv;
	}
	
	/*
	 * 由于部门和职位在employee中是对象关联映射
	 * 所以不能直接接收参数，需要创建job对象和dept对象
	 */
	private void genericAssociation(Integer job_id, Integer dept_id, Employee employee) {
		// TODO Auto-generated method stub
		if(job_id!=null){
			Job job=new Job();
			job.setId(job_id);
			employee.setJob(job);
		}
		if(dept_id!=null){
			Dept dept=new Dept();
			dept.setId(dept_id);
			employee.setDept(dept);
		}
	}
}
