package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import common.HrmConstants;
import domain.Dept;

public interface DeptDao {
	//��̬��ѯ
	@SelectProvider(type=DeptDynaSqlProvider.class,method="selectWithParam")
	List<Dept> selectByPage(Map<String,Object> params);
	
	//���ݲ�����ѯ����
	@SelectProvider(type=DeptDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	//��̬����
	@SelectProvider(type=DeptDynaSqlProvider.class,method="insertDept")
	void save(Dept dept);
	
	//��̬����
	@SelectProvider(type=DeptDynaSqlProvider.class,method="updateDept")
	void update(Dept dept);
	
	@Select("select * from "+HrmConstants.DEPTTABLE+" ")
	List<Dept> selectAllDept();
	
	@Select("select * from "+HrmConstants.DEPTTABLE+" where id=#{id} ")
	Dept selectById(@Param("id") Integer id);
	
	@Delete("delete from "+HrmConstants.DEPTTABLE+" where id=#{id} ")
	void deleteById(@Param("id") Integer id);
}
