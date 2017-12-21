package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import domain.*;
import tag.PageModel;
import dao.*;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImp implements HrmService {
	
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private JobDao jobDao;
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly=true)
	@Override
	public User login(String loginname, String password) {
		// TODO Auto-generated method stub
		System.out.println("HrmServiceImp login -- >>");
		return userDao.selectByLoginnameAndPassword(loginname, password);
	}
	
	@Transactional(readOnly=true)
	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.selectById(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<User> findUser(User user, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("user", user);
		int recordCount=userDao.count(params);
		System.out.println("recordCount -- >>"+recordCount);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pageModel);
		}
		return userDao.selectByPage(params);
	}

	@Override
	public void removeUserById(Integer id) {
		// TODO Auto-generated method stub
		userDao.deleteById(id);
	}

	@Override
	public void modifyUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("employee", employee);
		int recordCount=employeeDao.count(params);
		System.out.println("recordCount -- >>"+recordCount);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pageModel);
		}
		return employeeDao.slectByPage(params);
	}

	@Override
	public void removeEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		employeeDao.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Employee findEmployeeById(Integer id) {
		// TODO Auto-generated method stub
		return employeeDao.selectById(id);
	}

	@Override
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.save(employee);
	}

	@Override
	public void modifyEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.update(employee);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findDept(Dept dept, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("dept", dept);
		int recordCount=deptDao.count(params);
		System.out.println("recordCount -- >>"+recordCount);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pageModel);
		}
		return deptDao.selectByPage(params);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Dept> findAllDept() {
		// TODO Auto-generated method stub
		return deptDao.selectAllDept();
	}

	@Override
	public void removeDeptById(Integer id) {
		// TODO Auto-generated method stub
		deptDao.deleteById(id);
	}

	@Override
	public void addDept(Dept dept) {
		// TODO Auto-generated method stub
		deptDao.save(dept);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Dept findDeptById(Integer id) {
		// TODO Auto-generated method stub
		return deptDao.selectById(id);
	}

	@Override
	public void modifyDept(Dept dept) {
		// TODO Auto-generated method stub
		deptDao.update(dept);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Job> findAllJob() {
		// TODO Auto-generated method stub
		return jobDao.selectAllJob();
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Job> findJob(Job job, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("job", job);
		int recordCount=jobDao.count(params);
		System.out.println("recordCount -- >>"+recordCount);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pageModel);
		}
		return jobDao.selectByPage(params);
	}

	@Override
	public void removeJobById(Integer id) {
		// TODO Auto-generated method stub
		jobDao.deleteById(id);
	}

	@Override
	public void addJob(Job job) {
		// TODO Auto-generated method stub
		jobDao.save(job);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Job findJobById(Integer id) {
		// TODO Auto-generated method stub
		return jobDao.selectById(id);
	}

	@Override
	public void modifyJob(Job job) {
		// TODO Auto-generated method stub
		jobDao.update(job);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Notice> findNotice(Notice notice, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("notice", notice);
		int recordCount=noticeDao.count(params);
		System.out.println("recordCount -- >>"+recordCount);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pageModel);
		}
		return noticeDao.selectByPage(params);
	}

	@Transactional(readOnly=true)
	@Override
	public Notice findNoticeById(Integer id) {
		// TODO Auto-generated method stub
		return noticeDao.selectById(id);
	}

	@Override
	public void removeNoticeById(Integer id) {
		// TODO Auto-generated method stub
		noticeDao.deleteById(id);
	}

	@Override
	public void addNotice(Notice notice) {
		// TODO Auto-generated method stub
		noticeDao.save(notice);

	}

	@Override
	public void modifyNotice(Notice notice) {
		// TODO Auto-generated method stub
		noticeDao.update(notice);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Document> findDocument(Document document, PageModel pageModel) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("document", document);
		int recordCount=documentDao.count(params);
		System.out.println("recordCount -- >>"+recordCount);
		pageModel.setRecordCount(recordCount);
		if(recordCount>0){
			params.put("pageModel", pageModel);
		}
		return documentDao.selectByPage(params);
	}

	@Override
	public void addDocument(Document document) {
		// TODO Auto-generated method stub
		documentDao.save(document);
	}

	@Transactional(readOnly=true)
	@Override
	public Document findDocumentById(Integer id) {
		// TODO Auto-generated method stub
		return documentDao.selectById(id);
	}

	@Override
	public void removeDocumentById(Integer id) {
		// TODO Auto-generated method stub
		documentDao.deleteById(id);
	}

	@Override
	public void modifyDocument(Document document) {
		// TODO Auto-generated method stub
		documentDao.update(document);
	}
}
