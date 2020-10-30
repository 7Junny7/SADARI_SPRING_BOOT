package co.soft.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Board_INFO_TABLE")
public class BoardInfoBean {

	@Id
	@GeneratedValue
	private int boardidx; //글번호
	
	private String subject; //제목
	
	private String location; //지역
	
	@Temporal(TemporalType.DATE)
	private Date date; //원하는 날짜 설정
	
	@Temporal(TemporalType.TIME)
	private Date hour; //원하는 시간 설정
	
	private String foodType; //음식종류
	
	private String writer; //작성자
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date writingTime; //글 작성 시간
	
	@Column(nullable = true)
	private String wantWho; //같이 먹고 싶은 사람, 빈칸 가능
	
	@Column(nullable = true)
	private String content; //글 내용, 빈칸 가능
	
	
	
}
