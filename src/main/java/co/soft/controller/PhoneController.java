package co.soft.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.soft.service.certificationService;

@Controller
public class PhoneController extends certificationService{
	
	//인증번호
	@GetMapping("/sendSMS")
	 public @ResponseBody String sendSMS(String phoneNumber) {

	        Random rand  = new Random();
	        String numStr = "";
	        for(int i=0; i<4; i++) {
	            String ran = Integer.toString(rand.nextInt(10));
	            numStr+=ran;
	        }

	        System.out.println("수신자 번호 : " + phoneNumber);
	        System.out.println("인증번호 : " + numStr);
	        certifiedPhoneNumber(phoneNumber, numStr); //인증번호 핸드폰 전송
	        return numStr;
	    }
	
	//게시글정보
	@GetMapping("/sendSMS2")
	 public @ResponseBody String sendSMS(String phoneNumber,String restaurant,String foodType,String date, String time,String location, String loc_userPhone) {
			String str=(date+" "+time);
	        certifiedPhoneNumber2(phoneNumber, restaurant, foodType, str, location, loc_userPhone);
	        certifiedPhoneNumber3(phoneNumber, restaurant, foodType, str, location, loc_userPhone);
	        return str;
	    }
	
	// 글 삭제
		@GetMapping("/sendSMS3")
		public @ResponseBody String sendSMS(String phoneNumber,String loc_userPhone, String subject) {
	        String str = subject;
	        System.out.println(phoneNumber+":"+loc_userPhone+":"+str+" - sendSMS3");
	        if(phoneNumber.equals(loc_userPhone)) { //host가 지울때
	        	certifiedPhoneNumber4(phoneNumber, loc_userPhone, str);
	        }else {									//guest가 지울때
	        	certifiedPhoneNumber4(phoneNumber, loc_userPhone, str);
	        	certifiedPhoneNumber5(phoneNumber, loc_userPhone, str);
	        }
	        return str;
	    }
}
