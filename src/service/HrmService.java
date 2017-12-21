package service;

import java.util.List;

import domain.*;
import tag.PageModel;

public interface HrmService {
	
	User login(String loginname,String password);  //�û���¼
	User findUserById(Integer id);   //����id��ѯ�û�
	List<User> findUser(User user,PageModel pageModel); //��������û�
	void removeUserById(Integer id); //����idɾ���û�
	void modifyUser(User user); //�޸��û�
	void addUser(User user); //����û�
	
	List<Employee> findEmployee(Employee employee,PageModel pageModel); //��������û�
	void removeEmployeeById(Integer id); //����idɾ��Ա��
	Employee findEmployeeById(Integer id); //����id����Ա��
	void addEmployee(Employee employee); //���Ա��
	void modifyEmployee(Employee employee); //�޸�Ա��
	
	List<Dept> findDept(Dept dept,PageModel pageModel); //������в���,��ҳ��ѯ
	List<Dept> findAllDept(); //������в���
	public void removeDeptById(Integer id); //����idɾ������
	void addDept(Dept dept); //��Ӳ���
	Dept findDeptById(Integer id); //����id���Ҳ���
	void modifyDept(Dept dept); //�޸Ĳ���
	
	List<Job> findAllJob(); //�������ְλ
	List<Job> findJob(Job job,PageModel pageModel);  //��ҳ�������ְλ
	void removeJobById(Integer id); //����idɾ��ְλ
	void addJob(Job job); //���ְλ
	Job findJobById(Integer id); //����id��ѯְλ
	void modifyJob(Job job); //�޸Ĺ���
	
	List<Notice> findNotice(Notice notice,PageModel pageModel); //��ҳ������й���
	Notice findNoticeById(Integer id); //����id��ѯ����
	void removeNoticeById(Integer id);
	void addNotice(Notice notice);
	void modifyNotice(Notice notice);
	
	List<Document> findDocument(Document document,PageModel pageModel);
	void addDocument(Document document);
	Document findDocumentById(Integer id);
	void removeDocumentById(Integer id);
	void modifyDocument(Document document);
	
}