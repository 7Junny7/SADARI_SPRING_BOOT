package co.soft.service;


import java.util.HashMap;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
@Service
public class certificationService {
	public void certifiedPhoneNumber(String phoneNumber, String cerNum) {
        String api_key = "NCSLHGS73Y1I5F1K";
        String api_secret = "JNGMSUCTTTIKB8ANG91E933YRULVRRBJ";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", phoneNumber);    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text","우리 같이 먹을까? 인증번호 :"+cerNum);
//        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
	public void certifiedPhoneNumber2(String phoneNumber,String restaurant,String foodType,String str,String location) {
        String api_key = "NCSLHGS73Y1I5F1K";
        String api_secret = "JNGMSUCTTTIKB8ANG91E933YRULVRRBJ";
        Message coolsms = new Message(api_key, api_secret);
        // 4 params(to, from, type, text) are mandatory. must be filled
        System.out.println(str);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01091183210");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text","우리 같이 먹을까?\n"+"장소: "+restaurant+"\n"+"음식: "+foodType+"\n"+"주소: "+location+"\n"+"시간:"+str);
 
//        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
