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
	
	//파일정보 DB 저장용
	public void insertFile(FileInfoBean fileinfo) {
		fileRepo.save(fileinfo);
	}
	
	//파일정보 DB 삭제용
	public void deleteFile(FileInfoBean fileinfo) {
		fileRepo.deleteById(fileinfo.getBoardidx());
	}
	
	//파일정보 확인용 (idx 기준) 파일정보 없을 경우 null 값 return
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
