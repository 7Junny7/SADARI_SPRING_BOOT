package co.soft.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import co.soft.domain.FileInfoBean;
import co.soft.persistence.FileRepository;

@Service
public class FileService {

	private FileRepository fileRepository;
	
	public FileService(FileRepository fileRepository) {
		this.fileRepository=fileRepository;
	}
	
//	@Transactional
//	public Long saveFile(FileInfoBean file) {
//		return fileRepository.save(file.toEntity()).getBoardidx();
//	}
	
	@Transactional
	public void insertFile(FileInfoBean fileinfo) {
		fileRepository.save(fileinfo);
	}
	
	@Transactional
	public FileInfoBean getFile(Long boardidx) {
		FileInfoBean file=fileRepository.findById(boardidx).get();
		
		FileInfoBean fileDto=FileInfoBean.builder()
				.boardidx(boardidx)
				.origFilename(file.getOrigFilename())
				.filename(file.getFilename())
				.filePath(file.getFilePath())
				.build();
		return fileDto;
	}
}
