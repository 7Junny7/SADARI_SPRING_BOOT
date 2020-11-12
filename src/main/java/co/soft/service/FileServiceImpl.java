package co.soft.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.soft.domain.FileInfoBean;
import co.soft.persistence.FileRepository;

@Service
public class FileServiceImpl implements FileService{
	
	@Autowired
	private FileRepository fileRepo;
	
	public void insertFile(FileInfoBean fileinfo) {
		fileRepo.save(fileinfo);
	}
	
	public void deleteFile(FileInfoBean fileinfo) {
		fileRepo.deleteById(fileinfo.getBoardidx());
	}
	
	public FileInfoBean getFile(Long boardidx) {
		FileInfoBean fileDto = new FileInfoBean();
		Optional<FileInfoBean> filel = Optional.ofNullable(fileRepo.findById(boardidx).orElse(null));
		if(filel.isPresent()) {
			FileInfoBean file = fileRepo.findById(boardidx).get();
			fileDto=FileInfoBean.builder()
					.boardidx(boardidx)
					.origFilename(file.getOrigFilename())
					.filename(file.getFilename())
					.filePath(file.getFilePath())
					.build();
		}else {
			fileDto=FileInfoBean.builder()
					.boardidx(boardidx)
					.origFilename(null)
					.filename(null)
					.filePath(null)
					.build();
		}
		return fileDto;
	}
}
