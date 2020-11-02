package co.soft.domain;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;

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
	@GeneratedValue
	private Long useridx;
	@Transient	
	private boolean userIdExist;
	@Transient
	private boolean userLogin;
	
	@Size(min = 2, max = 4)
	@Pattern(regexp = "[가-힣]*")
	private String userName; // 이름

	@Size(min = 2, max = 10)
	@Pattern(regexp = "[가-힣a-zA-Z0-9]*")
	private String userNickName; // 닉네임
	
	@Column(length = 6)
	private String userBirth; // 생년월일

	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String userId; // 아이디

	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String userPassword; // 비밀번호
	
	@Transient
	private String userPassword2; // 비밀번호확인

	
	private String userGender; // 성별

	@Size(min=4, max=30)
	@Email
	private String userEmail; // 이메일

	@Size(min = 11,max = 11)
	private String userPhone; // 전화번호
	
	public UserInfoBean() {
		this.userIdExist = false;
		this.userLogin = false;
	}

	public boolean isUserIdExist() {
		return userIdExist;
	}

	public void setUserIdExist(boolean userIdExist) {
		this.userIdExist = userIdExist;
	}

	public boolean isUserLogin() {
		return userLogin;
	}

	public void setUserLogin(boolean userLogin) {
		this.userLogin = userLogin;
	}

}