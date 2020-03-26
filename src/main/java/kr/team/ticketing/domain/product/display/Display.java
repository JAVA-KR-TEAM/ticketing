package kr.team.ticketing.domain.product.display;

import kr.team.ticketing.domain.BaseEntity;
import kr.team.ticketing.domain.object.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Display extends BaseEntity {
	@Column
	private Long productId;
	@Column
	private String openingHours;
	@Embedded
	private Address address;
	@Column
	private String tel;
	@Column
	private String homePage;
	@Embedded
	private Email email;

	@Builder
	public Display(Long productId, String openingHours, Address address, String tel, String homePage, Email email) {
		this.productId = productId;
		this.openingHours = openingHours;
		this.address = address;
		this.tel = tel;
		this.homePage = homePage;
		this.email = email;
	}

	public void update(Display displayDto) {
		this.productId = displayDto.getProductId();
		this.openingHours = displayDto.getOpeningHours();
		this.address = displayDto.getAddress();
		this.tel = displayDto.getTel();
		this.homePage = displayDto.getHomePage();
		this.email = displayDto.getEmail();
	}
}
