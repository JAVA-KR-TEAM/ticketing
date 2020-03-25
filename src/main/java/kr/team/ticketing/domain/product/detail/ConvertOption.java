package kr.team.ticketing.domain.product.detail;

import kr.team.ticketing.domain.object.generic.money.Money;
import lombok.Builder;
import lombok.Data;

@Data
public class ConvertOption {
	private String name;
	private Money price;

	@Builder
	public ConvertOption(String name, Money price) {
		this.name = name;
		this.price = price;
	}
}
