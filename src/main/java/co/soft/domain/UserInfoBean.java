package co.soft.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private int useridx;
	private boolean userIdExist;
	private boolean userLogin;

	// @Size(min=2, max=4)
	// @Pattern(rㅈegexp = "[가-힣]*")
	private String userName; // 이름

	// @Size(min=2, max=10)
	// @Pattern(regexp = "[가-힣a-Z]*")
	private String userNickName; // 닉네임

	// @Size(min=6, max=6)
	// @Pattern(regexp = "[0-9]*")
	private int userBirth; // 생년월일

	// @Size(min=4, max=20)
	// @Pattern(regexp = "[a-zA-Z0-9]*")
	private String userId; // 아이디

	// @Size(min=4, max=20)
	// @Pattern(regexp = "[a-zA-Z0-9]*")
	private String userPassword; // 비밀번호
	private String userPassword2; // 비밀번호확인

	// @Size(min=0, max=5)
	// @Pattern(regexp = "[가-힣]*")
	private String userGender; // 성별

	// @Size(min=4, max=30)
	// @Pattern(regexp = "[a-zA-Z0-9]*")
	private String userEmail; // 이메일

	// @Size(min=1, max=12)
	// @Pattern(regexp = "[0-9]*")
	private int userPhone; // 전화번호

	// @Size(min=1, max=12)
	// @Pattern(regexp = "[0-9]*")
	private int userTemp; // 온도

	// @Size(min=1, max=12)
	// @Pattern(regexp = "[0-9]*")
	private int howManyuser; // 평가한사람수

	// @Size(min=0, max=5)
	// @Pattern(regexp = "[가-힣]*")
	private String favorite; // 좋아하는음식

	// @Size(min=0, max=5)
	// @Pattern(regexp = "[가-힣]*")
	private String userComment; // 한줄평

	public boolean isUserIdExist() {
		return userIdExist;
	}

	public boolean isUserLogin() {
		return userLogin;
	}

}