package dao;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import common.HrmConstants;
import domain.Dept;

public class DeptDynaSqlProvider {
	
	public String selectWithParam(Map<String,Object> params){
		String sql=new SQL(){
			{
				SELECT("*");
				FROM(HrmConstants.DEPTTABLE);
				if(params.get("dept")!=null){
					Dept dept=(Dept)params.get("dept");
					if(dept.getName()!=null&&!dept.getName().equals("")){
						WHERE(" name LIKE CONCAT ('%',#{dept.name},'%') ");
					}
				}
			}
		}.toString();
		
		if(params.get("pageModel")!=null){
			sql+=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize} ";
		}
		return sql;
	}
	
	public String count(Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(HrmConstants.DEPTTABLE);
				if(params.get("dept")!=null){
					Dept dept=(Dept)params.get("dept");
					if(dept.getName()!=null&&!dept.getName().equals("")){
						WHERE(" name LIKE CONCAT ('%',#{dept.name},'%') ");
					}
				}
			}
		}.toString();
	}
	
	public String insertDept(Dept dept){
		return new SQL(){
			{
				INSERT_INTO(HrmConstants.DEPTTABLE);
				if(dept.getName()!=null&&!dept.getName().equals("")){
					VALUES("name","#{name}");
				}
				if(dept.getRemark()!=null){
					VALUES("remark","#{remark}");
				}
			}
		}.toString();
	}
	
	public String updateDept(Dept dept){
		return new SQL(){
			{
				UPDATE(HrmConstants.DEPTTABLE);
				if(dept.getName()!=null&&!dept.getName().equals("")){
					SET(" name=#{name} ");
				}
				if(dept.getRemark()!=null){
					SET(" remark=#{remark} ");
				}
				WHERE(" id=#{id} ");
			}
		}.toString();
	}
}
