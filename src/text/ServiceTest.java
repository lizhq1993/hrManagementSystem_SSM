package text;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.HrmService;
import service.HrmServiceImp;
import tag.PageModel;
import domain.*;

public class ServiceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		HrmService hrmService=(HrmService)context.getBean("hrmService");
		// test user
		//User user1=hrmService.login("root", "931022");
		//System.out.println(user1);
		//System.out.println(hrmService.findUserById(1));
		/*User userSearch=new User();
		userSearch.setLoginname("ot");
		List<User> userList=hrmService.findUser(userSearch, new PageModel());
		for(User user:userList){
			System.out.println(user);
		}*/
		
		Dept dept1=new Dept();
		dept1.setName("²¿");
		PageModel pageModel=new PageModel();
		pageModel.setPageIndex(1);
		List<Dept> deptList=hrmService.findDept(dept1, pageModel);
		for(Dept dept:deptList){
			System.out.println(dept);
		}
	}

}
