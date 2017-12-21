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
import domain.Notice;

public interface NoticeDao {

	//动态查询
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="selectWithParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="create_date",property="createDate",javaType=java.util.Date.class),
		@Result(column="user_id",property="user",one=@One(select="dao.UserDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Notice> selectByPage(Map<String,Object> params);
	
	//根据条件查询总数
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	//动态插入
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="insertNotice")
	void save(Notice notice);
	
	//动态更新
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="updateNotice")
	void update(Notice notice);
	
	@Select("select * from "+HrmConstants.NOTICETABLE+" where id=#{id} ")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="create_date",property="createDate",javaType=java.util.Date.class),
		@Result(column="user_id",property="user",one=@One(select="dao.UserDao.selectById",fetchType=FetchType.EAGER))
	})
	Notice selectById(@Param("id") Integer id);
	
	@Select("select * from "+HrmConstants.NOTICETABLE+" ")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="create_date",property="createDate",javaType=java.util.Date.class),
		@Result(column="user_id",property="user",one=@One(select="dao.UserDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Notice> selectAllNotice();
	
	@Delete(" delete from "+HrmConstants.NOTICETABLE+" where id=#{id}")
	void deleteById(@Param("id") Integer id);
}
