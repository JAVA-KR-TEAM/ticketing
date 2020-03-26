package kr.team.ticketing.web.admin.product.request;

import kr.team.ticketing.domain.product.display.LocationCode;
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
