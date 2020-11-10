package co.soft.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.soft.service.certificationService;

@Controller
public class PhoneController extends certificationService{
	@GetMapping("/sendSMS")
	 public @ResponseBody
	    String sendSMS(String phoneNumber) {

	        Random rand  = new Random();
	        String numStr = "";
	        for(int i=0; i<4; i++) {
	            String ran = Integer.toString(rand.nextInt(10));
	            numStr+=ran;
	        }

	        System.out.println("수신자 번호 : " + phoneNumber);
	        System.out.println("인증번호 : " + numStr);
	        certifiedPhoneNumber(phoneNumber, numStr);
	        return numStr;
	    }
}
