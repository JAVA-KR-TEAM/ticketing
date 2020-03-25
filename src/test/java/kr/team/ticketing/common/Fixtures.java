package kr.team.ticketing.common;

import kr.team.ticketing.domain.member.Member;
import kr.team.ticketing.domain.object.Email;
import kr.team.ticketing.domain.object.generic.money.Money;
import kr.team.ticketing.domain.object.generic.money.Ratio;
import kr.team.ticketing.domain.product.Product;
import kr.team.ticketing.domain.product.detail.Option;
import kr.team.ticketing.domain.product.detail.ProductType;
import kr.team.ticketing.domain.reservation.Reservation;
import kr.team.ticketing.domain.reservation.ReservationLineItem;
import kr.team.ticketing.domain.reservation.ReservationOption;
import kr.team.ticketing.web.reservation.request.ReservationOptionRequest;
import kr.team.ticketing.web.reservation.request.ReservationRequest;

import static java.util.Arrays.asList;

public class Fixtures {
	public static ReservationRequest.ReservationRequestBuilder aReservationRequest() {
		return ReservationRequest.builder()
			.name("김철수")
			.email("chulsu@naver.com")
			.tel("010-2314-4123")
			.optionRequests(asList(
				ReservationOptionRequest.builder()
					.description("뮤즈 공연입니다.")
					.productId(1l)
					.name("ADULT")
					.price(23000)
					.build(),
				ReservationOptionRequest.builder()
					.description("뮤즈 공연입니다.")
					.productId(1l)
					.name("TEENAGER")
					.price(19000)
					.build(),
				ReservationOptionRequest.builder()
					.description("뮤즈 공연입니다.")
					.productId(1l)
					.name("CHILDREN")
					.price(17000)
					.build()
			));
	}

	public static Member.MemberBuilder aMember() {
		return Member.builder()
			.id(1l)
			.name("rebwon12")
			.email(new Email("rebwon@naver.com"));
	}

	public static Reservation.ReservationBuilder aReservation() {
		return Reservation.builder()
			.id(1l)
			.memberId(1l)
			.name("김철수")
			.email(new Email("chulsu@naver.com"))
			.tel("010-2314-1235")
			.lineItems(asList(aReservationLineItem().build()));
	}

	public static ReservationLineItem.ReservationLineItemBuilder aReservationLineItem() {
		return ReservationLineItem.builder()
			.productId(1l)
			.description("뮤즈 공연입니다.")
			.reserveOptions(asList(
				new ReservationOption("성인", Money.wons(23000)),
				new ReservationOption("청소년", Money.wons(19000)),
				new ReservationOption("어린이", Money.wons(17000))
			));
	}

	public static Product.ProductBuilder aProduct() {
		return Product.builder()
			.id(1l)
			.categoryId(1l)
			.description("뮤즈 공연입니다.")
			.content("뮤즈 공연은 매우 재미있습니다.")
			.event("");
	}

	public static Option.OptionBuilder anOptionAdult() {
		return Option.builder()
			.productType(ProductType.ADULT)
			.price(Money.wons(23000))
			.discountRate(Ratio.valueOf(0.2));
	}

	public static Option.OptionBuilder anOptionTeenager() {
		return Option.builder()
			.productType(ProductType.TEENAGER)
			.price(Money.wons(19000))
			.discountRate(Ratio.valueOf(0.21));
	}

	public static Option.OptionBuilder anOptionChildren() {
		return Option.builder()
			.productType(ProductType.CHILDREN)
			.price(Money.wons(17000))
			.discountRate(Ratio.valueOf(0.25));
	}
}
