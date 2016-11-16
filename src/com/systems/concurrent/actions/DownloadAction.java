package com.systems.concurrent.actions;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;

import com.opensymphony.xwork2.ActionContext;
import com.systems.concurrent.ejb.dao.ProjectdataDao;
import com.systems.concurrent.ejb.dto.ProjectdataData;

public class DownloadAction extends AbstractAction {

	private static final long serialVersionUID = 4500829695588986027L;
	private ProjectdataData projectData;
	private List<ProjectdataData> projectdataList;
	private File fileDownload;
	private InputStream fileInputStream;
	private String fileName;
	private String fileBase64;
	private String extension;

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFileBase64() {
		return fileBase64;
	}

	public void setFileBase64(String fileBase64) {
		this.fileBase64 = fileBase64;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public String execute() throws Exception {
		Map<String, Object> params = (Map<String, Object>) ActionContext.getContext().getParameters();
		Long id = null;
		Object fileId = params.get("id");
		id = Long.parseLong(((String[]) fileId)[0]);
		projectData = ProjectdataDao.getInstance().getItem(id);
		byte[] bFile = projectData.getFile();
		fileName = projectData.getFileName();
		fileDownload = new File(fileName);
		BufferedOutputStream bs = null;

		try {
			FileOutputStream fs = new FileOutputStream(fileDownload);
			bs = new BufferedOutputStream(fs);
			bs.write(bFile);
			bs.close();
			bs = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		fileInputStream = new FileInputStream(fileDownload);
		return SUCCESS;
	}

	public String preview() throws IOException {
		Map<String, Object> params = (Map<String, Object>) ActionContext.getContext().getParameters();
		Object fileId = params.get("id");
		Long id = Long.parseLong(((String[]) fileId)[0]);
		ProjectdataData item = ProjectdataDao.getInstance().getItem(id);
		byte[] bFile = item.getFile();
		fileBase64 = Base64.encodeBase64String(bFile);
		fileName = item.getFileName();
		extension = item.getFileExtension();
		return "preview";
	}

	public List<ProjectdataData> getProjectdataList() {
		return projectdataList;
	}

	public void setProjectdataList(List<ProjectdataData> projectdataList) {
		this.projectdataList = projectdataList;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFileDownload() {
		return fileDownload;
	}

	public void setFileDownload(File fileDownload) {
		this.fileDownload = fileDownload;
	}

}
