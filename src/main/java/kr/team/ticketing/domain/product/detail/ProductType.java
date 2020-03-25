package kr.team.ticketing.domain.product.detail;

public enum ProductType {
	ADULT("성인"),
	TEENAGER("청소년"),
	CHILDREN("어린이"),
	PACKAGE("패키지"),
	EARLYBIRD("얼리버드"),
	DAY("평일"),
	WEEKEND("주말");

	private String name;

	ProductType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
