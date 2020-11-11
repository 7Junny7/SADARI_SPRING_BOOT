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

	@RequestMapping("/deleteFile") // ok
	public String deleteFile(Model model, HttpServletRequest request) {
		Long boardidx = Long.parseLong(request.getParameter("boardidx"));
		FileInfoBean fileidx= fileService.getFile(boardidx);
		fileService.deleteFile(fileidx);
		return "/location/Location_Delete_Success";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/fileUpload") // upload test1 - ok
	public String fileupload(@RequestParam("filename") MultipartFile files, HttpServletRequest request, Model model) {
		try {
			Long boardidx = Long.parseLong(request.getParameter("boardidx"));
			String origFilename = files.getOriginalFilename();
			String filename = boardidx + origFilename;
			// 실행되는 위치의 'imgs' 폴더에 파일이 저장
			String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\imgs";
//			String savePath = request.getSession().getServletContext().getRealPath("/").concat("resources") + File.separator +"imgs";
			// 파일이 저장되는 폴더가 없으면 폴더를 생성
			if (!new File(savePath).exists()) {
				try {
					new File(savePath).mkdir();
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
			String filePath = savePath + "\\" + filename;
			files.transferTo(new File(filePath)); // ok

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
			System.out.println(fileinfo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/location/Location_Write";
	}

//	@PostMapping("/fileUpload2")
//	public String fileUpload2(FileInfoBean fileinfo, MultipartFile file, HttpServletRequest request) throws Exception {
//		String uploadPath = request.getSession().getServletContext().getRealPath("/").concat("resources");
//		String imgUploadPath = uploadPath + File.separator + "imgUpload"; // 이미지를 업로드할 폴더를 설정 = /uploadPath/imgUpload
//		String ymdPath = UpLoadFileUtils.calcPath(imgUploadPath); // 위의 폴더를 기준으로 연월일 폴더를 생성
//		String fileName = null; // 기본 경로와 별개로 작성되는 경로 + 파일이름
//
//		if (file.getOriginalFilename() != null && !file.getOriginalFilename().equals("")) {
//			// 파일 인풋박스에 첨부된 파일이 없다면(=첨부된 파일이 이름이 없다면)
//
//			fileName = UpLoadFileUtils.fileUpload(imgUploadPath, file.getOriginalFilename(), file.getBytes(), ymdPath);
//
//			// gdsImg에 원본 파일 경로 + 파일명 저장
//			fileinfo.setFilePath(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
//			// gdsThumbImg에 썸네일 파일 경로 + 썸네일 파일명 저장
////			fileinfo.setGdsThumbImg(
////					File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);
//
//		} else { // 첨부된 파일이 없으면
//			fileName = File.separator + "images" + File.separator + "none.jpg";
//			// 미리 준비된 none.png파일을 대신 출력함
//
//			fileinfo.setGdsImg(fileName);
////			fileinfo.setGdsThumbImg(fileName);
//		}
//		System.out.println("fileName : " + fileName);
//		adminService.register(vo);
//
//		return "redirect:/admin/index";
//	}

	@GetMapping("/download/{boardidx}") // 다운로드 ok
	public ResponseEntity<Resource> fileDonwload(@PathVariable("boardidx") Long fileId) throws IOException {
		FileInfoBean fileDto = fileService.getFile(fileId); // boardidx 기준으로 파일정보 빈에 저장
		Path path = Paths.get(fileDto.getFilePath()); // 파일 경로
		Resource resource = new InputStreamResource(Files.newInputStream(path)); // 파일 불러옴
		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
				.body(resource);
	}

	@GetMapping("/show/{boardidx}")
	public String fileShow(@PathVariable("boardidx") Long fileId, Model model) throws IOException {
		FileInfoBean fileDto = fileService.getFile(fileId); // boardidx 기준으로 파일정보 빈에 저장
		Path path = Paths.get(fileDto.getFilePath()); // 파일 경로
		Resource resource = new InputStreamResource(Files.newInputStream(path)); // 파일 불러옴
		
		model.addAttribute("path",path);
		model.addAttribute("file",fileDto);
		model.addAttribute("re",resource);
		System.out.println(fileDto);
		return "location/upload_Check";
	}

}
