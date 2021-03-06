package co.soft.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	private Long boardidx; //글번호 히든
	
	@Column(nullable = false)
	private String userId;// 참석자 아이디 확인용
	
	@Size(min = 11,max = 11)
	private String userPhone; // host 전화번호
	
	@Column(nullable = false)
	private String likeup;//참석눌렀을때 +1
	
	@Column(updatable = false)
	private String restaurant; // 음식점 명, 업데이트 불가
	
	@Column(updatable = false)
	private String subject; //제목, 업데이트 불가
	
	@Size(min = 4, max = 4)
	@Pattern(regexp = "[0-9]*")
	private String password; // 비밀번호
	
//	@Temporal(TemporalType.DATE)
	private String date; //원하는 날짜 설정  수정
	
//	@Temporal(TemporalType.TIME)
	private String time; //원하는 시간 설정 수정
	
	@Column(updatable = false)
	private String writer; //작성자, 업데이트 불가
	
	@Column(updatable = false)
	private String foodType; // 음식종류, 업데이트 불가
	
	@Column(nullable = true)
	private String wantWho; // 참여인원
	
	@Column(nullable = true)
	private String foodComment; //글 내용, 빈칸 가능
	
	private int foodLikeScore; // 좋아요 점수 히든

	private int foodLikePerson; // 투표 인원수 -> 두개 조합으로 좋아요 rate(별점) 계산 히든

}