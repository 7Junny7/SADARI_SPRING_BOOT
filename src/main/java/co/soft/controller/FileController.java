package co.soft.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.soft.domain.FileInfoBean;
import co.soft.service.FileService;
import co.util.MD5Generator;

@Controller
public class FileController {
	
	private FileService fileService;

	@RequestMapping("/io/fileadd")
	public String fileadd(HttpServletRequest request) {
		return "io/fileadd";
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/fileUpload")
	public String fileupload(@RequestParam("filename") MultipartFile files,HttpServletRequest request) {
		try {
			Long boardidx=Long.parseLong(request.getParameter("boardidx"));
			String origFilename = files.getOriginalFilename();
			String filename = new MD5Generator(origFilename).toString();
			// 실행되는 위치의 'files' 폴더에 파일이 저장
			String savePath = System.getProperty("user.dir")+"\\files";
			// 파일이 저장되는 폴더가 없으면 폴더를 생성
			if(!new File(savePath).exists()) {
				try {
					new File(savePath).mkdir();
				}catch(Exception e) {
					e.getStackTrace();
				}
			}
			String filePath = savePath + "\\" + filename;
			files.transferTo(new File(filePath)); //ok
			
			FileInfoBean fileinfo = new FileInfoBean();
			fileinfo.setBoardidx(boardidx);
			fileinfo.setOrigFilename(origFilename);
			fileinfo.setFilename(filename);
			fileinfo.setFilePath(filePath);
			
			System.out.println(fileinfo);
			fileService.insertFile(fileinfo);
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "location/upload_Success";
	}
	
	@GetMapping("/download/{boardidx}")
	public ResponseEntity<Resource> fileDonwload(@PathVariable("boardidx") Long fileId) throws IOException{
		FileInfoBean fileDto = fileService.getFile(fileId);
		Path path = Paths.get(fileDto.getFilePath());
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename()+"\"")
				.body(resource);
	}
	
}
