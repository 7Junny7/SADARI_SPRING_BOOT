package co.soft.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	//음식점 정보
	
	@Column(updatable = false)
	private String restaurant; // 음식점 명ㅋ, 업데이트 불가
	
	@Column(updatable = false)
	private String r_location_x; // 위치x, 업데이트 불가 히든
	
	@Column(updatable = false)
	private String r_location_y; // 위치y, 업데이트 불가 히든
	
	@Column(updatable = false)
	private String location; //지역, 업데이트 불가 
	
	@Column(updatable = false)
	private String foodType; // 음식종류, 업데이트 불가
	
	private int foodLikeScore; // 좋아요 점수  히든
	
	private int foodLikePerson; // 투표 인원수  -> 두개 조합으로 좋아요 rate(별점) 계산 히든
	
	//글내용
	
	@Id
	@GeneratedValue
	private Long boardidx; //글번호 히든
	
	@Column(updatable = false)
	private String subject; //제목, 업데이트 불가
	
	@Temporal(TemporalType.DATE)
	private Date date; //원하는 날짜 설정  수정
	
	@Temporal(TemporalType.TIME)
	private Date hour; //원하는 시간 설정 수정
	
	@Column(updatable = false)
	private String writer; //작성자, 업데이트 불가
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date writingTime; //글 작성 시간
	
	@Column(nullable = true)
	private String wantWho; //같이 먹고 싶은 사람, 빈칸 가능
	
	@Column(nullable = true)
	private String foodComment; //글 내용, 빈칸 가능

//	@Column(nullable = true)  // 에러남 확인 요
//	private MultipartFile foodPicture; //음식사진, 업로드
	
}