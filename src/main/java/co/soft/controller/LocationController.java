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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

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
    
   @RequestMapping("/locationList")
   public String getLocationList(UserInfoBean user, Model model, LocationInfoBean loc) {
//      if (user.getUserId() == null) { //목록만 보는거에는 로그인 필요 없을 듯?
//         return "redirect:login";
//      }

      List<LocationInfoBean> locationList = locationService.getLocationList(loc);
      model.addAttribute("locationList", locationList);
      return "/loaction/LocationList";
   }

   @GetMapping("/insertLocation")
   public String insertLocationView(UserInfoBean user, @ModelAttribute("map") MapInfoBean map, Model model) {
//      if (user.getUserId() == null) {
//         return "redirect:login";
//      }
      model.addAttribute(map);
      model.addAttribute(user);
      return "/location/Location_Write";
   }

   @PostMapping("/insertLocation")
   public String insertLocation(UserInfoBean user, @ModelAttribute("map") MapInfoBean map, LocationInfoBean loc) {
//      if (user.getUserId() == null) {
//         return "redirect:login";
//      }

      locationService.insertLocation(loc);
      return "redirect:home";
   }

   @GetMapping("/getLocation")
   public String getLocation(UserInfoBean user, LocationInfoBean loc, Model model) {
//      if (user.getUserId() == null) {
//         return "redirect:login";
//      }

      model.addAttribute("location", locationService.getLocation(loc));
      return "/location/Location"; //파일 있음?
   }
   
   @GetMapping("/updateLocation")
   public String updateLocationView(UserInfoBean user, LocationInfoBean loc, Model model) {
//      if (user.getUserId() == null) {
//         return "redirect:login";
//      }
      model.addAttribute("location", locationService.getLocation(loc));
      
      return "/location/LocationList_Modify";
   }
   
   @PostMapping("/updateLocation")
   public String updateLocation(UserInfoBean user, LocationInfoBean loc) {
//      if (user.getUserId() == null) {
//         return "redirect:login";
//      }

      locationService.updateLocation(loc);
      return "/location/LocationList_Modify_Success";
   }

   @GetMapping("/deleteLocation")
   public String deleteLocationView(UserInfoBean user, LocationInfoBean loc, Model model) {
//      if (user.getUserId() == null) {
//         return "redirect:login";
//      }
      model.addAttribute("location", locationService.getLocation(loc));
   
      return "/location/LocationList_Delete";
   }
   
   @PostMapping("/deleteLocation")
   public String deleteLocation(UserInfoBean user, LocationInfoBean loc) {
//      if (user.getUserId() == null) {
//         return "redirect:login";
//      }
      locationService.deleteLocation(loc);
      return "forward:locationList";
   }
   
   @RequestMapping("/bbom")
   public String bbom(Model model) {
	   model.addAttribute("bbom",10000);
	   return "/pay/Pay";
   }
   
   @RequestMapping("/location")
   public String location(Model model, MapInfoBean map,LocationInfoBean loc,HttpServletRequest request) {
	   Long boardidx = Long.parseLong(request.getParameter("boardidx"));
	   LocationInfoBean idx = locationService.getLocation(boardidx);
	   model.addAttribute("loc",idx);
	   return "/location/Location";
   }
    
   @RequestMapping("/topmenu")
   public String topMenu(HttpServletRequest request, Model model,MapInfoBean mapinfo) {
	   String menu=request.getParameter("menu");
	   String keyword=request.getParameter("keyword");
	   
	   
	   List<LocationInfoBean> locationList = locationService.getLocationListByFoodtype(menu);
	   List<Long> idx= new ArrayList<Long>();
	   Map<Integer,List<MapInfoBean>> idxList= new HashMap<Integer,List<MapInfoBean>>();
	   String key [] = {"강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "금천구", "구로구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"};
	   if(keyword == null) {
		   keyword="종로구";
	   }
	   Integer tempIdx;
	   for(int i =0; i<locationList.size(); i++) {
		   tempIdx = i;
		   idx.add(locationList.get(i).getBoardidx()); 
		  
		  idxList.put(tempIdx,(List<MapInfoBean>) mapService.locList(locationList.get(i).getBoardidx()));
		  if(idxList.get(i).get(0).getLocation().indexOf(keyword) == -1) {
			  idxList.remove(i);
		  }
	   }
	   
	   if(menu == "" || menu.equals("") || menu == null) {
		   List<MapInfoBean> mapList = mapService.getMapList(mapinfo);
		   for(int i=0; i<mapList.size(); i++) {
			   if(mapList.get(i).getLocation().indexOf(keyword) == -1) {
				   mapList.remove(i);
				   i--;
			   }
		   }		   
		   model.addAttribute("mapListOther", mapList);
	   } else {
		   model.addAttribute("boardmenu", idxList);
		   model.addAttribute("locationList",locationList);   
	   }
	   model.addAttribute("menu",menu);
	   model.addAttribute("key",key);
	   model.addAttribute("keyword",keyword);
	   return "forward:home";
   }
   
}