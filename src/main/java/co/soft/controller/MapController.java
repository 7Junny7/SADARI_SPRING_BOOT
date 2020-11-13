package co.soft.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
public class MapController {

	@Autowired
	private MapService mapService;
	
	@Autowired
	private LocationService locationService;

	@GetMapping("/insertMap")
	public String insertMapView(UserInfoBean user, HttpServletRequest request, Model model) {
//	  if (user.getUserId() == null) { //로그인 안하면 작성 못함
//		   return "redirect:login";
//	  }
		String res = request.getParameter("w_res");
		String x = request.getParameter("w_x");
		String y = request.getParameter("w_y");
		String loc = request.getParameter("w_loc");
		MapInfoBean map = new MapInfoBean();
		map.setRestaurant(res);
		map.setR_location_x(x);
		map.setR_location_y(y);
		map.setLocation(loc);
		map.setFilename("null");
		mapService.insertMap(map);
		model.addAttribute("map", map);
		
		return "/location/Location_Write"; 
	}

	@PostMapping("/insertMap")
	public String insertMap(UserInfoBean user, MapInfoBean map, Model model, HttpServletRequest request) {
//      if (user.getUserId() == null) { //로그인 안하면 작성 못함
//         return "redirect:login";
//      }
		map.setFilename("null");
		mapService.insertMap(map);
		model.addAttribute("map", map);
		return "/location/Location_Write";
	}

	@RequestMapping("/mapList")
	public String getMapList(MapInfoBean map, Model model) { // 리스트는 로그인 없어도 뜸
		List<MapInfoBean> mapList = mapService.getMapList(map);
		model.addAttribute("mapList", mapList);
		return "/map/MapList"; // 맵 리스트 페이지 지정할 것
	}

//   @GetMapping("/getMap")
//   public String getMap(UserInfoBean user, MapInfoBean map, Model model) {
////      if (user.getUserId() == null) { //로그인 안하면 못 봄
////         return "redirect:login";
////      }
//      model.addAttribute("map", mapService.getMap(map));
//      return "/map/"; //맵 정보 띄울 페이지 지정할 것
//      // 맵 페이지에서 location page로 연동
//   }

	@GetMapping("/deleteMap")
	public String deleteMap(UserInfoBean user, MapInfoBean map, Model model, HttpServletRequest request) {
//      if (user.getUserId() == null) { //로그인 안하면 실행 불가
//         return "redirect:login";
//      }
		Long boardidx = Long.parseLong(request.getParameter("boardidx"));
		List<MapInfoBean> mapidx = mapService.locList(boardidx);
		mapService.deleteMap(mapidx.get(0));
		return "redirect:deleteFile?boardidx="+boardidx;
	}
	
	@GetMapping("/errorMap")
	public String errorMap(HttpServletRequest request) {
		Long boardidx = Long.parseLong(request.getParameter("boardidx"));
		List<MapInfoBean> mapidx = mapService.locList(boardidx);
		mapService.deleteMap(mapidx.get(0));
		return "redirect:logout";
	}
	
	@RequestMapping("/getMarker")
	public String getMarker(HttpServletRequest request, Model model, MapInfoBean mapinfo) {
		String menu = request.getParameter("menu");
		String keyword = request.getParameter("keyword");

		List<LocationInfoBean> locationList = locationService.getLocationListByFoodtype(menu);
		List<Long> idx = new ArrayList<Long>();
		Map<Integer, List<MapInfoBean>> idxList = new HashMap<Integer, List<MapInfoBean>>();
		String key[] = { "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "금천구", "구로구", "노원구", "도봉구", "동대문구", "동작구", "마포구",
				"서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구" };
		if (keyword == null) {
			keyword = "";
		}
		
		Integer tempIdx;
		for (int i = 0; i < locationList.size(); i++) {
			tempIdx = i;
			idx.add(locationList.get(i).getBoardidx());

			idxList.put(tempIdx, (List<MapInfoBean>) mapService.locList(locationList.get(i).getBoardidx()));
			if (idxList.get(i).get(0).getLocation().indexOf(keyword) == -1) {
				idxList.remove(i);
			}
		}

		if (menu == "" || menu.equals("") || menu == null) {
			List<MapInfoBean> mapList = mapService.getMapList(mapinfo);
			for (int i = 0; i < mapList.size(); i++) {
				if (mapList.get(i).getLocation().indexOf(keyword) == -1) {
					mapList.remove(i);
					i--;
				}
			}
			model.addAttribute("mapListOther", mapList);
		} else {
			model.addAttribute("boardmenu", idxList);
			model.addAttribute("locationList", locationList);
		}
		model.addAttribute("menu", menu);
		model.addAttribute("key", key);
		model.addAttribute("keyword", keyword);
		return "forward:home";
	}

}