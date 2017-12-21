package domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Document implements Serializable {

	private static final long serialVersionUID = 1892787606330466230L;
	
	private Integer id;
	private String title;
	private String filename;
	private MultipartFile file;
	private String remark;
	private Date createDate;
	private User user;
	
	public Document(){};
	

	public Document(Integer id, String title, String filename, String remark, Date createDate, User user) {
		super();
		this.id = id;
		this.title = title;
		this.filename = filename;
		this.remark = remark;
		this.createDate = createDate;
		this.user = user;
	}



	public Document(Integer id, String title, String filename, MultipartFile file, String remark, Date createDate,
			User user) {
		super();
		this.id = id;
		this.title = title;
		this.filename = filename;
		this.file = file;
		this.remark = remark;
		this.createDate = createDate;
		this.user = user;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", title=" + title + ", filename=" + filename + ", file=" + file + ", remark="
				+ remark + ", createDate=" + createDate + ", user=" + user + "]";
	};
	
	
}
