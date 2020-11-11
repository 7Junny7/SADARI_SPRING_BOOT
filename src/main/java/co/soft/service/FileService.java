package co.soft.service;

import co.soft.domain.FileInfoBean;

public interface FileService {

	public void insertFile(FileInfoBean fileinfo);
	
	public FileInfoBean getFile(Long boardidx);
	
	public void deleteFile(FileInfoBean fileinfo);
}
