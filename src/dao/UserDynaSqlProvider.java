package dao;

import java.util.Map;
import common.HrmConstants;
import domain.User;

import org.apache.ibatis.jdbc.SQL;

public class UserDynaSqlProvider {
	public String selectWithParam(Map<String,Object> params){
		String sql=new SQL(){
			{
				SELECT("*");
				FROM(HrmConstants.USERTABLE);
				if(params.get("user")!=null){
					User user=(User)params.get("user");
					if(user.getUsername()!=null&&!user.getUsername().equals("")){
						WHERE(" username LIKE CONCAT ('%',#{user.username},'%') ");
					}
					if(user.getStatus()!=null&&!user.getStatus().equals("")){
						WHERE(" status LIKE CONCAT ('%',#{user.status},'%') ");
					}
				}
				
			}
		}.toString();
		
		if(params.get("pageModel")!=null){
			sql+=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize} ";
		}
		
		return sql;
	}
	
	public String count(Map<String,Object>params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(HrmConstants.USERTABLE);
				if(params.get("user")!=null){
					User user=(User)params.get("user");
					if(user.getUsername()!=null&&!user.getUsername().equals("")){
						WHERE(" username LIKE CONCAT ('%',#{user.username},'%') ");
					}
					if(user.getStatus()!=null&&!user.getStatus().equals("")){
						WHERE(" status LIKE CONCAT ('%',#{user.status},'%') ");
					}
				}
			}
		}.toString();
	}
	
	public String insertUser(User user){
		return new SQL(){
			{
				INSERT_INTO(HrmConstants.USERTABLE);
				if(user.getUsername()!=null){
					VALUES("username","#{username}");
				}
				if(user.getStatus()!=null&&!user.getStatus().equals("")){
					VALUES("status","#{status}");
				}
				if(user.getLoginname()!=null&&!user.getLoginname().equals("")){
					VALUES("loginname","#{loginname}");
				}
				if(user.getPassword()!=null&&!user.getPassword().equals("")){
					VALUES("password","#{password}");
				}
			}
		}.toString();
	}
	
	public String updateUser(User user){
		return new SQL(){
			{
				UPDATE(HrmConstants.USERTABLE);
				if(user.getUsername()!=null){
					SET(" username=#{username} ");
				}
				if(user.getStatus()!=null&&!user.getStatus().equals("")){
					SET(" status=#{status} ");
				}
				if(user.getLoginname()!=null&&!user.getLoginname().equals("")){
					SET(" loginname=#{loginname} ");
				}
				if(user.getPassword()!=null&&!user.getPassword().equals("")){
					SET(" password=#{password} ");
				}
				if(user.getCreateDate()!=null&&!user.getCreateDate().equals("")){
					SET(" createdate=#{createDate} ");
				}
				WHERE(" id=#{id} ");
			}
		}.toString();
	}
}
