package co.soft.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
	private Long boardidx;
	
	@Column(nullable = false)
	private String origFilename;
	
	@Column(nullable = false)
	private String filename;
	
	@Column(nullable = false)
	private String filePath;
	
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
