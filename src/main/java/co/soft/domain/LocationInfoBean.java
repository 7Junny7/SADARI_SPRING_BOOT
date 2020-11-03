package co.soft.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "LOCATION_INFO_TABLE")
public class LocationInfoBean {
	
	
	//글내용
	
	@Id
	@GeneratedValue
	private Long boardidx; //글번호 히든
	
	@Column(updatable = false)
	private String subject; //제목, 업데이트 불가
	
	@Size(min = 4, max = 20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String password; // 비밀번호
	
	@Temporal(TemporalType.DATE)
	private Date date; //원하는 날짜 설정  수정
	
	@Temporal(TemporalType.TIME)
	private Date time; //원하는 시간 설정 수정
	
	@Column(updatable = false)
	private String writer; //작성자, 업데이트 불가
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(updatable = false)
//	private Date writingTime; //글 작성 시간
	
	@Column(nullable = true)
	private String wantWho; //같이 먹고 싶은 사람, 빈칸 가능
	
	@Column(nullable = true)
	private String foodComment; //글 내용, 빈칸 가능


	
}