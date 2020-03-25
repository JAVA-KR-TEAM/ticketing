package kr.team.ticketing.domain.image.product;

import kr.team.ticketing.domain.image.Image;
import kr.team.ticketing.domain.image.ImageType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("PRODUCT")
public class ProductImage extends Image {
	@Enumerated(EnumType.STRING)
	@Column
	private ImageType imageType;

	@Builder
	public ProductImage(String imgName, String saveImgName, String contentType, ImageType imageType) {
		super(imgName, saveImgName, contentType);
		this.imageType = imageType;
	}
}
