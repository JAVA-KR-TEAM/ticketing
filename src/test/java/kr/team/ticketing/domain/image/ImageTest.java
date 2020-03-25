package kr.team.ticketing.domain.image;

import kr.team.ticketing.domain.DomainTests;
import kr.team.ticketing.domain.image.comment.CommentImage;
import kr.team.ticketing.domain.image.display.DisplayImage;
import kr.team.ticketing.domain.image.product.ProductImage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class ImageTest extends DomainTests {
	@Autowired
	private ImageRepository imageRepository;

	@BeforeEach
	void setUp() {
		ProductImage productImage = ProductImage.builder()
			.imgName("productImage.png")
			.saveImgName("product/productImage.png")
			.contentType("image/png")
			.imageType(ImageType.MAIN)
			.build();

		DisplayImage displayImage = DisplayImage.builder()
			.imgName("displayImage.png")
			.saveImgName("display/displayImage.png")
			.contentType("image/png")
			.imageType(ImageType.ETC)
			.build();

		CommentImage commentImage = CommentImage.builder()
			.displayId(1l)
			.reservationId(1l)
			.imgName("commentImage.png")
			.saveImgName("comment/commentImage.png")
			.contentType("image/png")
			.build();

		imageRepository.save(productImage);
		imageRepository.save(displayImage);
		imageRepository.save(commentImage);

		em.clear();
	}

	@DisplayName("이미지 조회")
	@Test
	void findImage() {
		ProductImage productImage = (ProductImage)imageRepository.findAll().get(0);
		DisplayImage displayImage = (DisplayImage)imageRepository.findAll().get(1);
		CommentImage commentImage = (CommentImage)imageRepository.findAll().get(2);

		assertThat(productImage.getImgName()).isEqualTo("productImage.png");
		assertThat(displayImage.getImgName()).isEqualTo("displayImage.png");
		assertThat(commentImage.getImgName()).isEqualTo("commentImage.png");
	}

	@DisplayName("이미지 삭제")
	@Test
	void deleteImage() {
		ProductImage productImage = (ProductImage)imageRepository.findAll().get(0);
		productImage.delete();

		assertThat(productImage.getDeleted()).isEqualTo(1);
	}
}