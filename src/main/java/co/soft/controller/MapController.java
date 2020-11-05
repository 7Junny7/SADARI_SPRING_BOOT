package co.soft.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import co.soft.domain.MapInfoBean;
import co.soft.domain.UserInfoBean;
import co.soft.service.MapService;

@SessionAttributes("user")
@Controller
public class MapController {
   
   @Autowired
   private MapService mapService;
   
   
   @GetMapping("/insertMap")
   public String insertMapView(UserInfoBean user,HttpServletRequest request,Model model) {
//	  if (user.getUserId() == null) { //로그인 안하면 작성 못함
//		   return "redirect:login";
//	  }
      model.addAttribute("latlngX",request.getParameter("latlngX"));
      model.addAttribute("latlngY",request.getParameter("latlngY"));
   
      return "/map/Map"; //맵 작성 페이지 지정할 것
   }
   
   @PostMapping("/insertMap")
   public String insertMap(UserInfoBean user, MapInfoBean map,Model model) {
//      if (user.getUserId() == null) { //로그인 안하면 작성 못함
//         return "redirect:login";
//      }
	  model.addAttribute("map",map);
      mapService.insertMap(map);
      return "/location/Location_Write";
   }
   
   @RequestMapping("/mapList")
   public String getMapList(MapInfoBean map, Model model) { //리스트는 로그인 없어도 뜸
      List<MapInfoBean> mapList=mapService.getMapList(map);
      model.addAttribute("mapList", mapList);
      return "/map/MapList"; //맵 리스트 페이지 지정할 것
   }
   
   @GetMapping("/getMap")
   public String getMap(UserInfoBean user, MapInfoBean map, Model model) {
//      if (user.getUserId() == null) { //로그인 안하면 못 봄
//         return "redirect:login";
//      }
      model.addAttribute("map", mapService.getMap(map));
      return "/map/"; //맵 정보 띄울 페이지 지정할 것
      // 맵 페이지에서 location page로 연동
   }
   
   @GetMapping("/deleteMap")
   public String deleteMap(UserInfoBean user, MapInfoBean map, Model model) {
//      if (user.getUserId() == null) { //로그인 안하면 실행 불가
//         return "redirect:login";
//      }
      mapService.deleteMap(map);
      return "foward:mapList"; 
   }

}