package dao;

import domain.Document;

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

public interface DocumentDao {
	
	// 动态查询
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="selectWithParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="create_date",property="createDate",javaType=java.util.Date.class),
		@Result(column="user_id",property="user",one=@One(select="dao.UserDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Document> selectByPage(Map<String,Object> params);
	
	//根据条件统计总数
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	//动态插入
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="insertDocument")
	void save(Document document);
	
	//动态更新
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="updateDocument")
	void update(Document document);
	
	@Select("select * from "+HrmConstants.DOCUMENTTABLE+" where id=#{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="create_date",property="createDate",javaType=java.util.Date.class),
		@Result(column="user_id",property="user",one=@One(select="dao.UserDao.selectById",fetchType=FetchType.EAGER))
	})
	Document selectById(@Param("id") Integer id);
	
	@Delete("delete from "+HrmConstants.DOCUMENTTABLE+" where id=#{id}")
	void deleteById(@Param("id") Integer id);
}
