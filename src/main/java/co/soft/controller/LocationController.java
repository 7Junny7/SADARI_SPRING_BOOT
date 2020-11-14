package co.soft.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import co.soft.domain.LocationInfoBean;
import co.soft.domain.MapInfoBean;
import co.soft.domain.UserInfoBean;
import co.soft.service.LocationService;
import co.soft.service.MapService;

@SessionAttributes("user")
@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private MapService mapService;
	
	// location_write 띄우기
	@GetMapping("/insertLocation")
	public String insertLocationView(UserInfoBean user, @ModelAttribute("map") MapInfoBean map, Model model) {
//      if (user.getUserId() == null) {
//         return "redirect:login";
//      }
		model.addAttribute(map);
		model.addAttribute(user);
		return "/location/Location_Write";
	}

	// location_write DB 저장
	@PostMapping("/insertLocation")
	public String insertLocation(UserInfoBean user, @ModelAttribute("map") MapInfoBean map, LocationInfoBean loc) {
//      if (user.getUserId() == null) {
//         return "redirect:login";
//      }
		locationService.insertLocation(loc);
		return "location/Location_Write_Success";
	}


	//location_modify 페이지 이동
	@GetMapping("/updateLocation")
	public String updateLocationView(UserInfoBean user, LocationInfoBean loc, Model model, HttpServletRequest request) {
//      if (user.getUserId() == null) {
//         return "redirect:login";
//      }
		Long boardidx = Long.parseLong(request.getParameter("boardidx"));
		LocationInfoBean idx = locationService.getLocation(boardidx);
		model.addAttribute("loca", idx);
		return "/location/LocationList_Modify";
	}

	//location DB update 후 location_success 페이지 이동
	@PostMapping("/updateLocation")
	public String updateLocation(UserInfoBean user, LocationInfoBean loc) {
//      if (user.getUserId() == null) {
//         return "redirect:login";
//      }
		locationService.updateLocation(loc);
		return "/location/LocationList_Modify_Success";
	}
	
	//참석 + 
	@GetMapping("/updateApply")
	public String updateApply(LocationInfoBean loc,HttpServletRequest request) {
		String userId = request.getParameter("userId");//현재접속중인유저
		String loc_userId = request.getParameter("loc_userId");
		String likeup = request.getParameter("likeup");
		Long boardidx = Long.parseLong(request.getParameter("boardidx"));
		
		LocationInfoBean idx = locationService.getLocation(boardidx);
		idx.setLikeup(likeup);
		idx.setUserId(userId.concat(","+loc_userId));
		locationService.updateLocationApply(idx);
		return "redirect:home";
	}

	//location삭제용 - location_delete (암호 확인 페이지)로 이동
	@GetMapping("/deleteLocation")
	public String deleteLocationView(UserInfoBean user, LocationInfoBean loc, Model model, HttpServletRequest request) {
//      if (user.getUserId() == null) {
//         return "redirect:login";
//      }
		Long boardidx = Long.parseLong(request.getParameter("boardidx"));
		LocationInfoBean idx = locationService.getLocation(boardidx);
		model.addAttribute("loca", idx);
		return "/location/LocationList_Delete";
	}

	//location DB에서 삭제 - location 삭제 후 Map 삭제하기 위해 deleteMap 페이로 이동
	@PostMapping("/deleteLocation")
	public String deleteLocation(UserInfoBean user, LocationInfoBean loc,HttpServletRequest request,Model model) {
//      if (user.getUserId() == null) {
//         return "redirect:login";
//      }
		Long boardidx = Long.parseLong(request.getParameter("boardidx"));
		LocationInfoBean locidx = locationService.getLocation(boardidx);
		locationService.deleteLocation(locidx);
		return "redirect:deleteMap?boardidx="+boardidx;
	}

	// location 상세정보 페이지 띄우기, Map 페이지 작성 후 오류로 인하여 location 페이지 작성 없이 닫긴 경우
	// Map 상에 허수 마커 (클릭시 오류)가 생기는데 확인하여 마커에 값이 없을 시 에러 페이지로 이동
	@RequestMapping("/location")
	public String location(Model model, LocationInfoBean loc, HttpServletRequest request,UserInfoBean user) {
		Long boardidx = Long.parseLong(request.getParameter("boardidx"));
		LocationInfoBean idx = locationService.getLocation(boardidx);
		if(idx != null) { //정상 작동
			List<MapInfoBean> mapinfo= mapService.locList(boardidx);
			model.addAttribute("user");
			model.addAttribute("mapinfo",mapinfo.get(0));
			model.addAttribute("loc", idx);
			return "/location/Location";
		}else { //마커에 정상값 없는 경우
			model.addAttribute("boardidx",boardidx);
			return "/location/Map_Error";
		}
	}

}