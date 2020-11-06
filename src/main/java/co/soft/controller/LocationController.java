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
import co.soft.service.UserService;
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
   public String topMenu(HttpServletRequest request, Model model) {
	   String menu=request.getParameter("menu"); //OK
	   System.out.println(menu);
	   List<LocationInfoBean> locationList = locationService.getLocationListByFoodtype(menu);
	   System.out.println(locationList);
	   model.addAttribute("menu",menu);
	   
	   return "forward:home";
   }
}