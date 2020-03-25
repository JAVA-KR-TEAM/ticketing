package kr.team.ticketing.domain.image.comment;

import kr.team.ticketing.domain.image.Image;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("COMMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentImage extends Image {
	@Column
	private Long reservationId;
	@Column
	private Long displayId;

	@Builder
	public CommentImage(String imgName, String saveImgName, String contentType, Long reservationId, Long displayId) {
		super(imgName, saveImgName, contentType);
		this.reservationId = reservationId;
		this.displayId = displayId;
	}
}
