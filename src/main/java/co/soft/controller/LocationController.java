package co.soft.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.soft.domain.LocationInfoBean;
import co.soft.domain.MapInfoBean;
import co.soft.domain.UserInfoBean;
import co.soft.service.LocationService;

@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@RequestMapping("/locationList")
	public String getLocationList(@ModelAttribute("user") UserInfoBean user, Model model, LocationInfoBean loc) {
//		if (user.getUserId() == null) { //목록만 보는거에는 로그인 필요 없을 듯?
//			return "redirect:login";
//		}

		List<LocationInfoBean> locationList = locationService.getLocationList(loc);
		model.addAttribute("locationList", locationList);
		return "/loaction/LocationList";
	}

	@GetMapping("/insertLocation")
	public String insertLocationView(@ModelAttribute("user") UserInfoBean user, @ModelAttribute("map") MapInfoBean map, Model model) {
//		if (user.getUserId() == null) {
//			return "redirect:login";
//		}
		model.addAttribute(map);
		model.addAttribute(user);
		return "/location/Location_Write";
	}

	@PostMapping("/insertLocation")
	public String insertLocation(@ModelAttribute("user") UserInfoBean user, @ModelAttribute("map") MapInfoBean map, LocationInfoBean loc) {
//		if (user.getUserId() == null) {
//			return "redirect:login";
//		}

		locationService.insertLocation(loc);
		return "redirect:getLocationList";
	}

	@GetMapping("/getLocation")
	public String getLocation(@ModelAttribute("user") UserInfoBean user, LocationInfoBean loc, Model model) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		model.addAttribute("location", locationService.getLocation(loc));
		return "/location/Location"; //파일 있음?
	}
	
	@GetMapping("/updateLocation")
	public String updateLocationView(@ModelAttribute("user") UserInfoBean user, LocationInfoBean loc) {
//		if (user.getUserId() == null) {
//			return "redirect:login";
//		}
		return "/location/LocationList_Modify";
	}
	
	@PostMapping("/updateLocation")
	public String updateLocation(@ModelAttribute("user") UserInfoBean user, LocationInfoBean loc) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		locationService.updateLocation(loc);
		return "/location/LocationList_Modify_Success";
	}

	@GetMapping("/deleteLocation")
	public String deleteLocation(@ModelAttribute("user") UserInfoBean user, LocationInfoBean loc) {
		if (user.getUserId() == null) {
			return "redirect:login";
		}

		locationService.deleteLocation(loc);
		return "forward:locationList";
	}
}
