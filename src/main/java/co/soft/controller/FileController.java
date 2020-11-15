package co.soft.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import co.soft.domain.FileInfoBean;
import co.soft.service.FileService;

@SessionAttributes("file")
@Controller
public class FileController {

	@Autowired
	private FileService fileService;

	//파일 DB 삭제
	@RequestMapping("/deleteFile")
	public String deleteFile(Model model, HttpServletRequest request) {
		Long boardidx = Long.parseLong(request.getParameter("boardidx"));
		FileInfoBean fileidx= fileService.getFile(boardidx);
		if(fileidx != null) {
			fileService.deleteFile(fileidx);
		}
		return "/location/Location_Delete_Success";
	}

	// 파일 업로드
	@RequestMapping(method = RequestMethod.POST, path = "/fileUpload")
	public String fileupload(@RequestParam("filename") MultipartFile files, HttpServletRequest request, Model model) {
		try {
			Long boardidx = Long.parseLong(request.getParameter("boardidx"));
			String origFilename = files.getOriginalFilename();
			String filename = boardidx + origFilename;
			// 실행되는 위치의 'imgs' 폴더에 파일이 저장
			String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\imgs";
			// 파일이 저장되는 폴더가 없으면 폴더를 생성
			if (!new File(savePath).exists()) {
				try {
					new File(savePath).mkdir();
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
			String filePath = savePath + "\\" + filename;
			files.transferTo(new File(filePath));

			FileInfoBean fileinfo = new FileInfoBean();
			fileinfo.setBoardidx(boardidx);
			fileinfo.setOrigFilename(origFilename);
			fileinfo.setFilename(filename);
			fileinfo.setFilePath(filePath);
			fileService.insertFile(fileinfo);

			String location = request.getParameter("location");
			String restaurant = request.getParameter("restaurant");
			fileinfo.setLocation(location);
			fileinfo.setRestaurant(restaurant);
			model.addAttribute("map", fileinfo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/location/upload_Success";
	}
	
	//사진 업로드 후 기존 페이지로 돌아가기 위한 자료 저장 - location_write 용
	@GetMapping("/fileUploadSuccess")
	public String fileUploadSuccess(HttpServletRequest request, Model model) {
		Long boardidx = Long.parseLong(request.getParameter("boardidx"));
		String location = request.getParameter("location");
		String restaurant = request.getParameter("restaurant");
		String origFilename = request.getParameter("origFilename");
		
		FileInfoBean bean =fileService.getFile(boardidx);
		bean.setLocation(location);
		bean.setRestaurant(restaurant);
		bean.setOrigFilename(origFilename);
		model.addAttribute("map",bean);
		return "/location/Location_Write";
	}

	//사진 다운로드 용 - 사용하지 않음
	@GetMapping("/download/{boardidx}")
	public ResponseEntity<Resource> fileDonwload(@PathVariable("boardidx") Long fileId) throws IOException {
		FileInfoBean fileDto = fileService.getFile(fileId); // boardidx 기준으로 파일정보 빈에 저장
		Path path = Paths.get(fileDto.getFilePath()); // 파일 경로
		Resource resource = new InputStreamResource(Files.newInputStream(path)); // 파일 불러옴
		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
				.body(resource);
	}

	//사진 보여주기 - location페이지용
	@GetMapping("/show/{boardidx}")
	public String fileShow(@PathVariable("boardidx") Long fileId, Model model) throws IOException {
		FileInfoBean fileDto = fileService.getFile(fileId); // boardidx 기준으로 파일정보 빈에 저장
		model.addAttribute("file",fileDto);
		return "location/upload_Check";
	}

}
