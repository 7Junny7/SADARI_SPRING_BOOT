package co.soft.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class FileInfoBean {

	@Id
	private Long boardidx; // 파일 인덱스 넘버 (공통)
	
	@Column(nullable = false)
	private String origFilename; // 원 파일 이름
	
	@Column(nullable = false)
	private String filename; // 스프링 부트 서버에 저장된 이름
	
	@Column(nullable = false)
	private String filePath; // 파일 경로
	
//	@Column(nullable = false)
//	private String img; //사진
//	
//	@Column(nullable = false)
//	private String thumbImg; //썸네일
	
	@Transient
	private String location;
	@Transient
	private String restaurant;
	
	
	
	public FileInfoBean toEntity() {
		FileInfoBean build=FileInfoBean.builder()
				.boardidx(boardidx)
				.origFilename(origFilename)
				.filename(filename)
				.filePath(filePath)
				.build();
		return build;
	}
	
	@Builder
	public FileInfoBean(Long boardidx, String origFilename, String filename, String filePath) {
		this.boardidx=boardidx;
		this.origFilename=origFilename;
		this.filename=filename;
		this.filePath=filePath;
	}
	
}
