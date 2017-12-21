package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import common.HrmConstants;
import domain.Employee;

public interface EmployeeDao {
	
	//动态查询
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="selectWithParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="card_id",property="cardId"),
		@Result(column="post_code",property="postCode"),
		@Result(column="qq_num",property="qqNum"),
		@Result(column="birthday",property="birthday",javaType=java.util.Date.class),
		@Result(column="create_date",property="createDate",javaType=java.util.Date.class),
		@Result(column="dept_id",property="dept",one=@One(select="dao.DeptDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="job_id",property="job",one=@One(select="dao.JobDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Employee> slectByPage(Map<String,Object> params);
	
	//根据条件统计总数
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	//动态插入
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="insertEmployee")
	void save(Employee employee);
	
	//动态更新
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="updateEmployee")
	void update(Employee employee);
	
	@Select("select * from "+HrmConstants.EMPLOYEETABLE+" where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="card_id",property="cardId"),
		@Result(column="post_code",property="postCode"),
		@Result(column="qq_num",property="qqNum"),
		@Result(column="birthday",property="birthday",javaType=java.util.Date.class),
		@Result(column="create_date",property="createDate",javaType=java.util.Date.class),
		@Result(column="dept_id",property="dept",one=@One(select="dao.DeptDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="job_id",property="job",one=@One(select="dao.JobDao.selectById",fetchType=FetchType.EAGER))
	})
	Employee selectById(@Param("id") Integer id);
	
	@Delete(" delete from "+HrmConstants.EMPLOYEETABLE+" where id=#{id}")
	void deleteById(@Param("id") Integer id);
}
