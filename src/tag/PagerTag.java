package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PagerTag extends SimpleTagSupport{
	private static final String TAG="{0}"; //定义请求url中的占位符常量
	private int pageIndex; //当前页码
	private int pageSize; //每页显示的数量
	private int recordCount;  //总记录条数
	private String submitUrl; //请求url page.action?pageIndex={0}
	private String style="sabrosus"; //样式
	private int totalPage=0;  //定义总页数
	
	// 在页面上引用自定义标签就会触发一个标签处理类
	@Override
	public void doTag() throws JspException,IOException{
		StringBuilder res=new StringBuilder();
		StringBuilder str=new StringBuilder();
		if(recordCount>0){
			totalPage=(this.recordCount-1)/this.pageSize+1;
			if(this.pageIndex==1){
				str.append("<span class='disabled'>上一页</span>");
				this.calaPage(str);
				if(this.pageIndex==totalPage){
					str.append("<span class='disabled'>下一页</span>");
				}else{
					String tempUrl=this.submitUrl.replace(TAG, String.valueOf(pageIndex+1));
					str.append("<a href='"+tempUrl+"'>下一页</a>");
				}
			}else if(this.pageIndex==totalPage){
				String tempUrl=this.submitUrl.replace(TAG, String.valueOf(pageIndex-1));
				str.append("<a href='"+tempUrl+"'>上一页</a>");
				this.calaPage(str);
				str.append("<span class='disabled'>下一页</span>");
			}else{
				String tempUrl=this.submitUrl.replace(TAG, String.valueOf(pageIndex-1));
				str.append("<a href='"+tempUrl+"'>上一页</a>");
				this.calaPage(str);
				tempUrl=this.submitUrl.replace(TAG, String.valueOf(pageIndex+1));
				str.append("<a href='"+tempUrl+"'>下一页</a>");
			}
			res.append("<table width='100%' align='center' style='font-size:13px;' class='"+style+"'>");
			res.append("<tr><td style='color:#0061de;margin_right:3px;padding-top:2px;text-decoration:none'>"+str.toString());
			res.append("&nbsp;跳转到&nbsp;&nbsp;<input style='text-align:center;border-right:#aaaadd 1px solid;padding-right:5px;border-top:#aaaadd 1px solid;"
					+ "padding-left:5px;padding-bottom:2px;margin:2px;border-left:#aaaadd 1px solid;color:#000099;"
					+ "padding-top:2px;border-bottom:#aaaadd 1px solid;text-decoration:none' type='text' size='2' id='pager_jump_page_size'/>");
			res.append("&nbsp;<input type='button' style='text-align:center;border-right:#dedfde 1px solid;padding-right:6px;"
					+ "background-position:50% bottom; border-top:#dedfde 1px solid;padding-left:6px;padding-bottom:2px;"
					+ "border-left:#dedfde 1px solid;color:#0061de;margin-right:3px;padding-top:2px;border-bottom:#dedfde 1px solid;"
					+ "text-decoration:none' value='确定' id='pager_jump_btn'/>");
			res.append("</td></tr>");
			res.append("<tr algin='center'><td style='font-size:13px;'><tr><td style='color:#0061de;margin-right:3px;"
					+ "padding-top:2px;text-decoration:none'>");
			int startNum=(this.pageIndex-1)*this.pageSize+1;
			int endNum=(this.pageIndex==this.totalPage)?this.recordCount:this.pageIndex*this.pageSize;
			res.append("总共<font color='red'>"+this.recordCount+"</font>条记录,当前显示"+startNum+"-"+endNum+"条记录。");
			res.append("</td></tr>"); //??少了结束符
			res.append("</table>");
			res.append("<script type='text/javascript'>");
			res.append("document.getElementById('pager_jump_btn').onclick=function(){");
			res.append("var page_size=document.getElementById('pager_jump_page_size').value;");
			res.append(" if(!/^[1-9]\\d*$/.test(page_size)||page_size<1||page_size>"+this.totalPage+"){");
			res.append(" alert('请输入[1-"+this.totalPage+"]之间的页码!');");
			res.append(" }else{");
			res.append(" var submit_url='"+this.submitUrl+"';");
			res.append(" window.location=submit_url.replace('"+TAG+"',page_size);");
			res.append("  }");
			res.append("}");
			res.append("</script>");
		}else{
			res.append("<table algin='center' style='font-size:13px;'><tr><td style='color:#0061de;margin-right:3px;padding-top:2px;"
					+ "text-decoration:none'>总共<font color='red'>0</font>条记录，当前显示0-0条记录。</td></tr></table>");
		}
		this.getJspContext().getOut().print(res.toString());
	}
	private void calaPage(StringBuilder str){
		if(this.totalPage<=11){
			for(int i=1;i<=this.totalPage;i++){
				if(this.pageIndex==i){
					str.append("<span class='current'>"+i+"</span>");
				}else{
					String tempUrl=this.submitUrl.replace(TAG, String.valueOf(i));
					str.append("<a href='"+tempUrl+"'>"+i+"</a>");
				}
			}
		}else{
			if(this.pageIndex<=8){
				for(int i=1;i<=10;i++){
					if(this.pageIndex==i){
						str.append("<span class='current'>"+i+"</span>");
					}else{
						String tempUrl=this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+tempUrl+"'>"+i+"</a>");
					}
				}
				str.append("...");
				String tempUrl=this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
				str.append("<a href='"+tempUrl+"'>"+this.totalPage+"</a>");
			}else if(this.pageIndex+8>=this.totalPage){
				String tempUrl=this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='"+tempUrl+"'>"+1+"</a>");
				str.append("...");
				for(int i=this.totalPage-10;i<=this.totalPage;i++){
					if(this.pageIndex==i){
						str.append("<span class='current'>"+i+"</span>");
					}else{
						tempUrl=this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+tempUrl+"'>"+i+"</a>");
					}
				}
			}else{
				String tempUrl=this.submitUrl.replace(TAG, String.valueOf(1));
				str.append("<a href='"+tempUrl+"'>"+1+"</a>");
				str.append("...");
				for(int i=this.pageIndex-4;i<=this.pageIndex+4;i++){
					if(this.pageIndex==i){
						str.append("<span class='current'>"+i+"</span>");
					}else{
						tempUrl=this.submitUrl.replace(TAG, String.valueOf(i));
						str.append("<a href='"+tempUrl+"'>"+i+"</a>");
					}
				}
				str.append("...");
				tempUrl=this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
				str.append("<a href='"+tempUrl+"'>"+this.totalPage+"</a>");
			}
		}
	}
	
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}
	
	public void setStyle(String style) {
		this.style = style;
	}
	
}
