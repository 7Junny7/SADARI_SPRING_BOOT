package co.soft.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "USER_INFO_TABLE")
public class UserInfoBean {
	@Id
	private Long useridx;
	private boolean userIdExist;
	private boolean userLogin;
	
	@Size(min = 2, max = 4)
	@Pattern(regexp = "[가-힣]*")
	private String userName; // 이름

	@Size(min = 2, max = 10)
	@Pattern(regexp = "[가-힣a-zA-Z0-9]*")
	private String userNickName; // 닉네임
	
	@Min(6)
	@Max(6)
	private int userBirth; // 생년월일

	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String userId; // 아이디

	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String userPassword; // 비밀번호
	private String userPassword2; // 비밀번호확인

	@Size(min = 0, max = 5)
	@Pattern(regexp = "[가-힣]*")
	private String userGender; // 성별

	@Size(min=4, max=30)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String userEmail; // 이메일
	
	@Min(0)
	@Max(9)
	private int userPhone; // 전화번호

	public boolean isUserIdExist() {
		return userIdExist;
	}

	public boolean isUserLogin() {
		return userLogin;
	}

}