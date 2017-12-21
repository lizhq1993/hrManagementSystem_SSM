package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import common.HrmConstants;
import domain.User;

public interface UserDao {
	
	@Select("select * from "+HrmConstants.USERTABLE+" where loginname=#{loginname} and password=#{password}")
	@Results({
		@Result(column="createdate",property="createDate",javaType=java.util.Date.class)
	})
	User selectByLoginnameAndPassword(@Param("loginname") String loginname,@Param("password") String password);
	
	@Select("select * from "+HrmConstants.USERTABLE+" where id=#{id}")
	@Results({
		@Result(column="createdate",property="createDate",javaType=java.util.Date.class)
	})
	User selectById(@Param("id") Integer id);
	
	@Delete("delete from "+HrmConstants.USERTABLE+" where id=#{id}")
	void deleteById(@Param("id") Integer id);
	
	//动态查询
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWithParam")
	@Results({
		@Result(column="createdate",property="createDate",javaType=java.util.Date.class)
	})
	List<User> selectByPage(Map<String,Object> params);
	
	//动态修改
	@SelectProvider(type=UserDynaSqlProvider.class,method="updateUser")
	void update(User user);
	
	//根据参数查询用户总数
	@SelectProvider(type=UserDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	//动态插入
	@SelectProvider(type=UserDynaSqlProvider.class,method="insertUser")
	void save(User user);
}
