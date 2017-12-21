package dao;

import common.HrmConstants;
import domain.Document;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;


public class DocumentDynaSqlProvider {
	
	public String selectWithParam(Map<String,Object> params){
		String sql=new SQL(){
			{
				SELECT("*");
				FROM(HrmConstants.DOCUMENTTABLE);
				if(params.get("document")!=null){
					Document document=(Document)params.get("document");
					if(document.getTitle()!=null&&!document.getTitle().equals("")){
						WHERE(" title LIKE CONCAT ('%',#{document.title},'%') ");
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
				FROM(HrmConstants.DOCUMENTTABLE);
				if(params.get("document")!=null){
					Document document=(Document)params.get("document");
					if(document.getTitle()!=null&&!document.getTitle().equals("")){
						WHERE(" title LIKE CONCAT ('%',#{document.title},'%') ");
					}
				}
			}
		}.toString();
	}
	
	public String insertDocument(Document document){
		return new SQL(){
			{
				INSERT_INTO(HrmConstants.DOCUMENTTABLE);
				if(document.getTitle()!=null&&!document.getTitle().equals("")){
					VALUES("title","#{title}");
				}
				if(document.getFilename()!=null&&!document.getFilename().equals("")){
					VALUES("filename","#{filename}");
				}
				if(document.getRemark()!=null&&!document.getRemark().equals("")){
					VALUES("remark","#{remark}");
				}
				if(document.getUser()!=null&&document.getUser().getId()!=null){
					VALUES("user_id","#{user.id}");
				}
			}
		}.toString();
	}
	
	public String updateDocument(Document document){
		return new SQL(){
			{
				UPDATE(HrmConstants.DOCUMENTTABLE);
				if(document.getTitle()!=null&&!document.getTitle().equals("")){
					SET(" title=#{title}");
				}
				if(document.getFilename()!=null&&!document.getFilename().equals("")){
					SET(" filename=#{filename}");
				}
				if(document.getRemark()!=null&&!document.getRemark().equals("")){
					SET(" remark=#{remark}");
				}
				if(document.getUser()!=null&&document.getUser().getId()!=null){
					SET(" use_id=#{user.id");
				}
				WHERE(" id=#{id}");
			}
		}.toString();
	}
}
