package dao;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import common.HrmConstants;
import domain.Employee;

public class EmployeeDynaSqlProvider {
	
	public String selectWithParam(Map<String,Object> params){
		String sql=new SQL(){
			{
				SELECT("*");
				FROM(HrmConstants.EMPLOYEETABLE);
				if(params.get("employee")!=null){
					Employee employee=(Employee)params.get("employee");
					if(employee.getDept()!=null&&employee.getDept().getId()!=null&&employee.getDept().getId()!=0){
						WHERE(" dept_id=#{employee.dept.id} ");
					}
					if(employee.getJob()!=null&&employee.getJob().getId()!=null&&employee.getJob().getId()!=0){
						WHERE(" job_id=#{employee.job.id} ");
					}
					if(employee.getPhone()!=null&&!employee.getPhone().equals("")){
						WHERE(" phone LIKE CONCAT ('%',#{employee.phone},'%') ");
					}
					if(employee.getName()!=null&&!employee.getName().equals("")){
						WHERE(" name LIKE CONCAT ('%',#{employee.name},'%') ");
					}
					if(employee.getCardId()!=null&&!employee.getCardId().equals("")){
						WHERE(" card_id LIKE CONCAT ('%',#{employee.cardId},'%') ");
					}
					if(employee.getSex()!=null&&employee.getSex()!=0){
						WHERE(" sex=#{employee.sex} ");
					}
				}
			}
		}.toString();
		if(params.get("pageModel")!=null){
			sql+=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
		}
		return sql;
	}
	
	public String count(Map<String,Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(HrmConstants.EMPLOYEETABLE);
				if(params.get("employee")!=null){
					Employee employee=(Employee)params.get("employee");
					if(employee.getDept()!=null&&employee.getDept().getId()!=null&&employee.getDept().getId()!=0){
						WHERE(" dept_id=#{employee.dept.id} ");
					}
					if(employee.getJob()!=null&&employee.getJob().getId()!=null&&employee.getJob().getId()!=0){
						WHERE(" job_id=#{employee.job.id} ");
					}
					if(employee.getPhone()!=null&&!employee.getPhone().equals("")){
						WHERE(" phone LIKE CONCAT ('%',#{employee.phone},'%') ");
					}
					if(employee.getName()!=null&&!employee.getName().equals("")){
						WHERE(" name LIKE CONCAT ('%',#{employee.name},'%') ");
					}
					if(employee.getCardId()!=null&&!employee.getCardId().equals("")){
						WHERE(" card_id LIKE CONCAT ('%',#{employee.cardId},'%') ");
					}
					if(employee.getSex()!=null&&employee.getSex()!=0){
						WHERE(" sex=#{employee.sex} ");
					}
				}
			}
		}.toString();
	}
	
	public String insertEmployee(Employee employee){
		return new SQL(){
			{
				INSERT_INTO(HrmConstants.EMPLOYEETABLE);
				if(employee.getName()!=null&&!employee.getName().equals("")){
					VALUES("name","#{name}");
				}
				if(employee.getCardId()!=null&&!employee.getCardId().equals("")){
					VALUES("card_id","#{cardId}");
				}
				if(employee.getAddress()!=null&&!employee.getAddress().equals("")){
					VALUES("address","#{address}");
				}
				if(employee.getPostCode()!=null&&!employee.getPostCode().equals("")){
					VALUES("post_code","#{postCode}");
				}
				if(employee.getTel()!=null){
					VALUES("tel","#{tel}");
				}
				if(employee.getPhone()!=null&&!employee.getPhone().equals("")){
					VALUES("phone","#{phone}");
				}
				if(employee.getQqNum()!=null){
					VALUES("qq_num","#{qqNum}");
				}
				if(employee.getEmail()!=null&&!employee.getEmail().equals("")){
					VALUES("email","#{email}");
				}
				if(employee.getSex()!=null&&employee.getSex()!=0){
					VALUES("sex","#{sex}");
				}
				if(employee.getParty()!=null&&!employee.getParty().equals("")){
					VALUES("party","#{party}");
				}
				if(employee.getBirthday()!=null){
					VALUES("birthday","#{birthday}");
				}
				if(employee.getRace()!=null){
					VALUES("race","#{race}");
				}
				if(employee.getEducation()!=null){
					VALUES("education","#{education}");
				}
				if(employee.getSpeciality()!=null){
					VALUES("speciality","#{speciality}");
				}
				if(employee.getHobby()!=null){
					VALUES("hobby","#{hobby}");
				}
				if(employee.getRemark()!=null){
					VALUES("remark","#{remark}");
				}
				if(employee.getCreateDate()!=null){
					VALUES("create_date","#{createDate}");
				}
				if(employee.getDept()!=null){
					VALUES("dept_id","#{dept.id}");
				}
				if(employee.getJob()!=null){
					VALUES("job_id","#{job.id}");
				}
			}
		}.toString();
	}
	
	public String updateEmployee(Employee employee){
		return new SQL(){
			{
				UPDATE(HrmConstants.EMPLOYEETABLE);
				if(employee.getName()!=null&&!employee.getName().equals("")){
					SET(" name=#{name} ");
				}
				if(employee.getCardId()!=null&&!employee.getCardId().equals("")){
					SET(" card_id=#{cardId} ");
				}
				if(employee.getAddress()!=null&&!employee.getAddress().equals("")){
					SET(" address=#{address} ");
				}
				if(employee.getPostCode()!=null&&!employee.getPostCode().equals("")){
					SET(" post_code=#{postCode} ");
				}
				if(employee.getTel()!=null){
					SET(" tel=#{tel} ");
				}
				if(employee.getPhone()!=null&&!employee.getPhone().equals("")){
					SET(" phone=#{phone} ");
				}
				if(employee.getQqNum()!=null){
					SET(" qq_num=#{qqNum} ");
				}
				if(employee.getEmail()!=null&&!employee.getEmail().equals("")){
					SET(" email=#{email} ");
				}
				if(employee.getSex()!=null&&employee.getSex()!=0){
					SET(" sex=#{sex} ");
				}
				if(employee.getParty()!=null&&!employee.getParty().equals("")){
					SET(" party=#{party} ");
				}
				if(employee.getBirthday()!=null){
					SET(" birthday=#{birthday} ");
				}
				if(employee.getRace()!=null){
					SET(" race=#{race} ");
				}
				if(employee.getEducation()!=null){
					SET(" education=#{education} ");
				}
				if(employee.getSpeciality()!=null){
					SET(" speciality=#{speciality} ");
				}
				if(employee.getHobby()!=null){
					SET(" hobby=#{hobby} ");
				}
				if(employee.getRemark()!=null){
					SET(" remark=#{remark} ");
				}
				if(employee.getCreateDate()!=null){
					SET(" create_date=#{createDate} ");
				}
				if(employee.getDept()!=null){
					SET(" dept_id=#{dept.id} ");
				}
				if(employee.getJob()!=null){
					SET(" job_id=#{job.id} ");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
}
