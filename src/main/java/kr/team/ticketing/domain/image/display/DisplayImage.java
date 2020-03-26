package kr.team.ticketing.domain.image.display;

import kr.team.ticketing.domain.image.Image;
import kr.team.ticketing.domain.image.ImageType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@DiscriminatorValue("DISPLAY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DisplayImage extends Image {
	@Enumerated(EnumType.STRING)
	@Column
	private ImageType imageType;

	@Builder
	public DisplayImage(String imgName, String saveImgName, String contentType, ImageType imageType) {
		super(imgName, saveImgName, contentType);
		this.imageType = imageType;
	}
}
