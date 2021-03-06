package co.soft.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "MAP_INFO_TABLE")
public class MapInfoBean {
	
	// 맵정보
	// 음식점 정보
	@Id	@GeneratedValue
	private Long boardidx; //글번호 히든
	
	@Column(updatable = false)
	private String restaurant; // 음식점 명, 업데이트 불가

	@Column(updatable = false)
	private String r_location_x; // 위치x, 업데이트 불가 히든

	@Column(updatable = false)
	private String r_location_y; // 위치y, 업데이트 불가 히든

	@Column(updatable = false)
	private String location; // 지역, 업데이트 불가
	
	@Transient
	private String filename; // 원 파일 이름
}