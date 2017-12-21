package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import common.HrmConstants;
import domain.Job;

public interface JobDao {
	
	//动态查询
	@SelectProvider(type=JobDynaSqlProvider.class,method="selectWithParam")
	List<Job> selectByPage(Map<String,Object> params);
	
	//根据参数查询总数
	@SelectProvider(type=JobDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	//动态插入
	@SelectProvider(type=JobDynaSqlProvider.class,method="insertJob")
	void save(Job job);
	
	//动态更新
	@SelectProvider(type=JobDynaSqlProvider.class,method="updateJob")
	void update(Job job);
	
	@Select("select * from "+HrmConstants.JOBTABLE+" where id=#{id}")
	Job selectById(@Param("id") Integer id);
	
	@Select("select * from "+HrmConstants.JOBTABLE+" ")
	List<Job> selectAllJob();
	
	@Delete(" delete from "+HrmConstants.JOBTABLE+" where id=#{id}")
	void deleteById(@Param("id") Integer id);
}
