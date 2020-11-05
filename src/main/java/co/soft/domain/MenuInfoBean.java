package co.soft.domain;

import javax.persistence.Column;
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
@Table(name = "MENU_INFO_TABLE")
public class MenuInfoBean {
 
	//좌상단 메뉴 인터셉터 용
	//1.한식 - 11.면류, 12.육류, 13.탕/국/찌개/전골, 14.일반한식
	//2.중식 - 21.일반중식, 22.마라, 23.양꼬치
	//3.일식 - 31.회/초밥, 32.면류, 33.일본가정식, 34.튀김, 35.꼬치
	//4.분식 - 41.분식 (서브메뉴 없음,필요?)
	//5.양식 - 51.파스타/피자 (서브메뉴 없음,필요?)
	//6.패스트푸드 - 61.피자, 62.치킨, 63.햄버거
	//7.기타
	
	@Id
	@Column(insertable = false, updatable = false)
	private Long menu_info_idx;
	@Column(insertable = false, updatable = false)
	private String menu_info_name;
	
	
}
