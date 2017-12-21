package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import common.HrmConstants;
import domain.Document;
import domain.User;
import service.HrmService;
import tag.PageModel;

@Controller
public class DocumentController {
	
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/*
	 * 处理模糊查询请求
	 */
	@RequestMapping(value="/document/selectDocument")
	public String selectDocument(Integer pageIndex,@ModelAttribute Document document,Model model){
		PageModel pageModel=new PageModel();
		if(pageIndex!=null){
			pageModel.setPageIndex(pageIndex);
		}
		List<Document> documents=hrmService.findDocument(document, pageModel);
		model.addAttribute("documents", documents);
		model.addAttribute("pageModel", pageModel);
		return "document/document";
	}
	
	/*
	 * 处理添加请求
	 */
	@RequestMapping(value="/document/addDocument")
	public ModelAndView addDocument(String flag,@ModelAttribute Document document,ModelAndView mv,HttpSession session) throws IllegalStateException, IOException{
		if(flag.equals("1")){
			mv.setViewName("document/showAddDocument");
		}else{
			String path=session.getServletContext().getRealPath("/upload/");
			String fileName=document.getFile().getOriginalFilename();
			document.getFile().transferTo(new File(path+File.separator+fileName));
			document.setFilename(fileName);
			User user=(User)session.getAttribute(HrmConstants.USER_SESSION);
			document.setUser(user);
			hrmService.addDocument(document);
			mv.setViewName("redirect:/document/selectDocument");
		}
		return mv;
	}
	
	/*
	 * 处理删除请求
	 */
	@RequestMapping(value="/document/removeDocument")
	public ModelAndView removeDocument(String ids,ModelAndView mv){
		String[] idArray=ids.split(",");
		for(String id:idArray){
			hrmService.removeDocumentById(Integer.parseInt(id));
		}
		mv.setViewName("redirect:/document/selectDocument");
		return mv;
	}
	
	/*
	 * 处理修改请求
	 */
	@RequestMapping(value="/document/updateDocument")
	public ModelAndView updateDocument(String flag,@ModelAttribute Document document,ModelAndView mv){
		if(flag.equals("1")){
			Document target=hrmService.findDocumentById(document.getId());
			mv.addObject("document", target);
			mv.setViewName("document/showUpdateDocument");
		}else{
			hrmService.modifyDocument(document);
			mv.setViewName("redirect:/document/selectDocument");
		}
		return mv;
	}
	
	/*
	 * 处理下载请求
	 */
	@RequestMapping(value="/document/downLoad")
	public ResponseEntity<byte[]> downLoad(Integer id,HttpSession session) throws IOException{
		Document target=hrmService.findDocumentById(id);
		String fileName=target.getFilename();
		String path=session.getServletContext().getRealPath("/upload/");
		File file=new File(path+File.separator+fileName); //至此获得要下载的File对象
		HttpHeaders headers=new HttpHeaders();  //创建HttpHeaders对象 
		String downloadFileName=new String(fileName.getBytes("UTF-8"),"iso-8859-1"); //下载显示的文件名，解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", downloadFileName); //通知浏览器以attachment（下载方式）打开图片
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); //二进制流数据（最常见的文件下载）
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	}
}
