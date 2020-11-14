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

	// Map에서 좌표 입력 받아서 마커 DB에 저장 후 location 입력을 위하여 location_write 페이지로 이동
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

	//Map 삭제 후, File 삭제를 위하여 deleteFile 페이지로 이동
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
	
	// 빈 마커의 경우 확인하여 삭제 후 오류 방지를 위하여 logout 후, login 페이지로 이동
	@GetMapping("/errorMap")
	public String errorMap(HttpServletRequest request) {
		Long boardidx = Long.parseLong(request.getParameter("boardidx"));
		List<MapInfoBean> mapidx = mapService.locList(boardidx);
		mapService.deleteMap(mapidx.get(0));
		return "redirect:logout";
	}
	
	// 마커를 검색하여 페이지에 띄우기 용
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