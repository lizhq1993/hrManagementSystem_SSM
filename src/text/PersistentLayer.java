package text;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.DeptDao;
import dao.DocumentDao;
import dao.EmployeeDao;
import dao.JobDao;
import dao.NoticeDao;
import dao.UserDao;
import domain.Dept;
import domain.Document;
import domain.Employee;
import domain.Job;
import domain.Notice;
import domain.User;

public class PersistentLayer {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		Map<String,Object> map=new HashMap<String,Object>();
		
		// 测试userDao
		/*UserDao userDao=(UserDao)context.getBean("userDao");
		User user=userDao.selectById(1);
		System.out.println(user);
		User user1=userDao.selectByLoginnameAndPassword("admin","123456");
		System.out.println(user1);*/
		
		/*User user3=new User();
		user3.setId(2);
		user3.setLoginname("admin");
		user3.setPassword("931022");
		user3.setStatus(1);
		user3.setCreateDate(new Date());
		user3.setUsername("itBoy");
		userDao.save(user3);*/
		
		//userDao.deleteById(2);
		
		/*User updateUser=new User();
		updateUser.setId(1);
		updateUser.setLoginname("root");
		updateUser.setPassword("931022");
		updateUser.setCreateDate(new Date());
		userDao.update(updateUser);
		
		
		User user4=new User();
		user4.setUsername("管理");
		map.put("user", user4);
		List<User> list=userDao.selectByPage(map);
		System.out.println(list.get(0));
		System.out.println(userDao.count(map));*/
		
		// test DeptDao
		//DeptDao deptDao=(DeptDao)context.getBean("deptDao");
		/*List<Dept> deptlist=deptDao.selectAllDept();
		for(Dept dept:deptlist){
			System.out.println(dept);
		}*/
		
		//System.out.println(deptDao.selectById(2));
		
		/*Dept deptInsert=new Dept();
		deptInsert.setId(7);
		deptInsert.setName("公关部");
		deptDao.save(deptInsert);*/
		
		//deptDao.deleteById(10);
		//deptDao.deleteById(14);
		
		/*Dept dept=new Dept();
		dept.setName("技术");
		map.put("dept", dept);
		List<Dept> deptList=deptDao.selectByPage(map);
		System.out.println(deptList.get(0));
		System.out.println(deptDao.count(map));
		
		Dept deptUpdate=new Dept();
		deptUpdate.setId(1);
		deptUpdate.setName("技术部");
		//deptUpdate.setRemark("it集中营");
		deptUpdate.setRemark("技术部");
		deptDao.update(deptUpdate);*/
		
		//test jobDao
		//JobDao jobDao=(JobDao)context.getBean("jobDao");
		//Job job1=jobDao.selectById(4);
		//System.out.println(job1);
		
		/*List<Job> jobAll=jobDao.selectAllJob();
		for(Job job:jobAll){
			System.out.println(job);
		}*/
		
		/*Job jobInsert=new Job(10,"HR","人力资源");
		jobDao.save(jobInsert);*/
		
		//jobDao.deleteById(10);
		
		/*Job jobUpdate=new Job(2,"","java开发工程师");
		jobDao.update(jobUpdate);*/
		
		/*Job jobSearch=new Job();
		jobSearch.setName("java");
		map.put("job", jobSearch);
		List<Job> jobList=jobDao.selectByPage(map);
		for(Job job:jobList){
			System.out.println(job);
		}
		
		System.out.println(jobDao.count(map));*/
		
		//test noticeDao
		//NoticeDao noticeDao=(NoticeDao)context.getBean("noticeDao");
		/*Notice notice1=noticeDao.selectById(1);
		System.out.println(notice1);
		Notice noticeSearch=new Notice();
		noticeSearch.setContent("hello");
		map.put("notice", noticeSearch);
		List<Notice> noticeList=noticeDao.selectByPage(map);
		for(Notice notice:noticeList){
			System.out.println(notice);
		}
		System.out.println(noticeDao.count(map));*/
		
		/*Notice noticeInsert=new Notice(3,"test","test",new Date(),new User());
		noticeDao.save(noticeInsert);*/
		
		/*Notice noticeUpdate=new Notice();
		noticeUpdate.setTitle("例会通知");
		noticeUpdate.setId(2);
		noticeDao.update(noticeUpdate);*/
		
		//noticeDao.deleteById(19);
		
		// test documentDao
		//DocumentDao documentDao=(DocumentDao)context.getBean("documentDao");
		/*Document document1=documentDao.selectById(1);
		System.out.println(document1);
		Document documentSearch=new Document();
		documentSearch.setTitle("java");
		map.put("document", documentSearch);
		List<Document> documentList=documentDao.selectByPage(map);
		for(Document document:documentList){
			System.out.println(document);
		}
		System.out.println(documentDao.count(map));*/
		//DiskFileItem fileItem=new DiskFileItem(null, null, false, null, 0, null);
		//Document documentInsert=new Document(3,"test","test",new CommonsMultipartFile(fileItem),"test",new Date(),new User());
		/*Document documentInsert=new Document(3,"test","test","test",new Date(),new User());
		documentDao.save(documentInsert);*/
		
		/*Document documentUpdate=new Document();
		documentUpdate.setTitle("update");
		documentUpdate.setId(7);
		documentDao.update(documentUpdate);*/
		
		//documentDao.deleteById(7);
		
		//test employeeDao
		EmployeeDao employeeDao=(EmployeeDao)context.getBean("employeeDao");
		Employee employee1=employeeDao.selectById(1);
		System.out.println(employee1);
		/*Employee employeeSearch=new Employee();
		employeeSearch.setName("强");
		map.put("employee", employeeSearch);
		List<Employee> employeeList=employeeDao.slectByPage(map);
		for(Employee employee:employeeList){
			System.out.println(employee);
		}
		System.out.println(employeeDao.count(map));*/
		
		/*Employee employeeInsert=new Employee(2,new Dept(3,"财务部","财务部"),new Job(1,"职员","职员"),"味精","123456","广东","514400","139****","139****","79**","/qq.com",2,"少先队员",new Date(),"汉","本科","会计","吃","蠢",new Date());
		employeeDao.save(employeeInsert);*/
		//employeeDao.deleteById(22);
		/*Employee employeeUpdate=new Employee();
		employeeUpdate.setName("魏静");
		employeeUpdate.setId(23);
		employeeDao.update(employeeUpdate);*/
	}
}
