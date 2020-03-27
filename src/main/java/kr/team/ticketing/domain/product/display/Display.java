package kr.team.ticketing.domain.product.display;

import kr.team.ticketing.domain.BaseEntity;
import kr.team.ticketing.domain.object.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Display extends BaseEntity {
	@Column
	private Long productId;
	@Column
	private String openingHours;
	@Column
	private LocalDateTime startDisplayDate;
	@Column
	private LocalDateTime endDisplayDate;
	@Embedded
	private Address address;
	@Column
	private String tel;
	@Column
	private String homePage;
	@Embedded
	private Email email;

	@Builder
	public Display(Long productId, String openingHours, LocalDateTime startDisplayDate, LocalDateTime endDisplayDate,
		Address address, String tel, String homePage, Email email) {
		this.productId = productId;
		this.openingHours = openingHours;
		this.startDisplayDate = startDisplayDate;
		this.endDisplayDate = endDisplayDate;
		this.address = address;
		this.tel = tel;
		this.homePage = homePage;
		this.email = email;
	}

	public void update(Display displayDto) {
		this.productId = displayDto.getProductId();
		this.openingHours = displayDto.getOpeningHours();
		this.startDisplayDate = displayDto.getStartDisplayDate();
		this.endDisplayDate = displayDto.getEndDisplayDate();
		this.address = displayDto.getAddress();
		this.tel = displayDto.getTel();
		this.homePage = displayDto.getHomePage();
		this.email = displayDto.getEmail();
	}
}
