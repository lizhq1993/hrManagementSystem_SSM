package service;

import java.util.List;

import domain.*;
import tag.PageModel;

public interface HrmService {
	
	User login(String loginname,String password);  //用户登录
	User findUserById(Integer id);   //根据id查询用户
	List<User> findUser(User user,PageModel pageModel); //获得所有用户
	void removeUserById(Integer id); //根据id删除用户
	void modifyUser(User user); //修改用户
	void addUser(User user); //添加用户
	
	List<Employee> findEmployee(Employee employee,PageModel pageModel); //获得所有用户
	void removeEmployeeById(Integer id); //根据id删除员工
	Employee findEmployeeById(Integer id); //根据id查找员工
	void addEmployee(Employee employee); //添加员工
	void modifyEmployee(Employee employee); //修改员工
	
	List<Dept> findDept(Dept dept,PageModel pageModel); //获得所有部门,分页查询
	List<Dept> findAllDept(); //获得所有部门
	public void removeDeptById(Integer id); //根据id删除部门
	void addDept(Dept dept); //添加部门
	Dept findDeptById(Integer id); //根据id查找部门
	void modifyDept(Dept dept); //修改部门
	
	List<Job> findAllJob(); //获得所有职位
	List<Job> findJob(Job job,PageModel pageModel);  //分页获得所有职位
	void removeJobById(Integer id); //根据id删除职位
	void addJob(Job job); //添加职位
	Job findJobById(Integer id); //根据id查询职位
	void modifyJob(Job job); //修改工作
	
	List<Notice> findNotice(Notice notice,PageModel pageModel); //分页获得所有公告
	Notice findNoticeById(Integer id); //根据id查询公告
	void removeNoticeById(Integer id);
	void addNotice(Notice notice);
	void modifyNotice(Notice notice);
	
	List<Document> findDocument(Document document,PageModel pageModel);
	void addDocument(Document document);
	Document findDocumentById(Integer id);
	void removeDocumentById(Integer id);
	void modifyDocument(Document document);
	
}