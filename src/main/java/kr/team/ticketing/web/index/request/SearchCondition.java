package kr.team.ticketing.web.index.request;

import lombok.*;

@Data
public class SearchCondition {

	private Long categoryId;
	private String locationCode;
	private int month;

	@Builder
	public SearchCondition(Long categoryId, String locationCode, int month) {
		this.categoryId = categoryId;
		this.locationCode = locationCode;
		this.month = month;
	}
}
