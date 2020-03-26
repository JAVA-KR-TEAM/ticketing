package kr.team.ticketing.domain.image;

import kr.team.ticketing.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Where(clause = "deleted = 0")
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Image extends BaseEntity {
	@Column
	private String imgName;
	@Column
	private String saveImgName;
	@Column
	private String contentType;
	@Column
	private int deleted = 0;

	public Image(String imgName, String saveImgName, String contentType) {
		this.imgName = imgName;
		this.saveImgName = saveImgName;
		this.contentType = contentType;
	}

	public void delete() {
		this.deleted = 1;
	}
}
