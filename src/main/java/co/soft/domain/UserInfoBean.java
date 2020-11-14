package co.soft.domain;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
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
	 
	@Size(min = 2, max = 20)
	@Pattern(regexp = "[가-힣a-zA-Z0-9]*")
	private String userNickName; // 닉네임
	
	@Column(length = 6)
	private String userBirth; // 생년월일

	@Id
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String userId; // 아이디

	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String userPassword; // 비밀번호
	
	@Transient
	private String userPassword2; // 비밀번호확인

	private String userGender; // 성별

	@Size(min = 11,max = 11)
	private String userPhone; // 전화번호

}