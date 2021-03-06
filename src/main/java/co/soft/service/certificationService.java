package co.soft.service;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
@Service
public class certificationService {
	
	// 인증번호 생성 용
	public void certifiedPhoneNumber(String phoneNumber, String cerNum) {
        String api_key = "NCSDHS4SD7DTVQO4";
        String api_secret = "Z2MNXCR4WWBAOTQHXVSM98G1U4SBRTDO";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01071399170");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
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
	
	//참석자 문자 정보 전송 용 - 참석자에게
	public void certifiedPhoneNumber2(String phoneNumber,String restaurant,String foodType,String str,String location, String loc_userPhone) {
        String api_key = "NCSDHS4SD7DTVQO4";
        String api_secret = "Z2MNXCR4WWBAOTQHXVSM98G1U4SBRTDO";
        Message coolsms = new Message(api_key, api_secret);
        // 4 params(to, from, type, text) are mandatory. must be filled
        System.out.println(str);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01071399170");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "LMS");
        params.put("text","우리 같이 먹을까?\n"+"장소: "+restaurant+"\n"+"음식: "+foodType+"\n"+"주소: "+location+"\n"+"시간:"+str+"\n"+"호스트 연락처:"+loc_userPhone);
 
//        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
	
	//참석자 문자 정보 전송 용 - host에게
	public void certifiedPhoneNumber3(String phoneNumber,String restaurant,String foodType,String str,String location, String loc_userPhone) {
		String api_key = "NCSDHS4SD7DTVQO4";
		String api_secret = "Z2MNXCR4WWBAOTQHXVSM98G1U4SBRTDO";
		Message coolsms = new Message(api_key, api_secret);
		// 4 params(to, from, type, text) are mandatory. must be filled
		System.out.println(str);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", loc_userPhone);    // 수신전화번호
		params.put("from", "01071399170");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
		params.put("type", "LMS");
		params.put("text","우리 같이 먹을까?\n"+"장소: "+restaurant+"\n"+"음식: "+foodType+"\n"+"주소: "+location+"\n"+"시간:"+str+"\n"+"참석자 연락처:"+phoneNumber);
		
//        params.put("app_version", "test app 1.2"); // application name and version
		
		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
	
	//삭제 문자 정보 전송 용 - host에게
	public void certifiedPhoneNumber4(String phoneNumber, String loc_userPhone, String str) {
		String api_key = "NCSDHS4SD7DTVQO4";
		String api_secret = "Z2MNXCR4WWBAOTQHXVSM98G1U4SBRTDO";
		Message coolsms = new Message(api_key, api_secret);
		// 4 params(to, from, type, text) are mandatory. must be filled
		System.out.println(str);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNumber);    // 수신전화번호
		params.put("from", "01071399170");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
		params.put("type", "LMS");
		params.put("text","참여하셨던"+str+"모임이 취소가 되었습니다. 다음에 더 맛있는 곳에서 만나요!");
		
//        params.put("app_version", "test app 1.2"); // application name and version
		
		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
			System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
	
	//삭제 문자 정보 전송 용 - guest에게
	public void certifiedPhoneNumber5(String phoneNumber, String loc_userPhone, String str) {
		String api_key = "NCSDHS4SD7DTVQO4";
		String api_secret = "Z2MNXCR4WWBAOTQHXVSM98G1U4SBRTDO";
		Message coolsms = new Message(api_key, api_secret);
		// 4 params(to, from, type, text) are mandatory. must be filled
		System.out.println(str);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", loc_userPhone);    // 수신전화번호
		params.put("from", "01071399170");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
		params.put("type", "LMS");
		params.put("text","참여하셨던"+str+"모임이 취소가 되었습니다. 다음에 더 맛있는 곳에서 만나요!");
		
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
