package co.soft.controller;

import java.io.File;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.soft.domain.LocationInfoBean;
import co.soft.domain.UserInfoBean;
import co.soft.service.LocationService;


@Controller
public class LocationController {

	@Autowired
	@Lazy
	private LocationService locationService;
	
	@RequestMapping("/getLocationList")
	public String getLocationList(@ModelAttribute("user") UserInfoBean user, Model model, LocationInfoBean loc) {
//		if (user.getId() == null) {
//			return "redirect:login";
//		}

		List<LocationInfoBean> locationList = locationService.getLocationList(loc);

		model.addAttribute("locationList", locationList);
		return "getLocationList";
	}

	@GetMapping("/insertLocation")
	public String insertLocationView(@ModelAttribute("user") UserInfoBean user) {
//		if (user.getId() == null) {
//			return "redirect:login";
//		}

		return "insertLocation";
	}

	@PostMapping("/insertLocation")
	public String insertLocation(@ModelAttribute("user") UserInfoBean user, LocationInfoBean loc) {
//		if (user.getId() == null) {
//			return "redirect:login";
//		}

//		LocationService.insertLocation(loc);
		return "redirect:getBoardList";
	}

	@GetMapping("/getLocation")
	public String getLocation(@ModelAttribute("user") UserInfoBean user, LocationInfoBean loc, Model model) {
//		if (user.getId() == null) {
//			return "redirect:login";
//		}

//		model.addAttribute("location", LocationService.getLocation(loc));
		return "getLocation";
	}

	@PostMapping("/updateLocation")
	public String updateBoard(@ModelAttribute("user") UserInfoBean user, LocationInfoBean loc) {
//		if (user.getId() == null) {
//			return "redirect:login";
//		}

//		LocationService.updateLocation(loc);
		return "forward:getLocationdList";
	}

	@GetMapping("/deleteLocation")
	public String deleteLocation(@ModelAttribute("user") UserInfoBean user, LocationInfoBean loc) {
//		if (user.getId() == null) {
//			return "redirect:login";
//		}

//		LocationService.deleteLocation(loc);
		return "forward:getLocationList";
	}
	

}
