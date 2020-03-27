package kr.team.ticketing.web.index.response;

import com.querydsl.core.annotations.QueryProjection;
import kr.team.ticketing.domain.product.display.LocationCode;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProductSearchResponse {

    private Long productId;
    private String description;
    private String content;
    private String event;
    private Long categoryId;
    private LocalDateTime startDisplayDate;
    private LocalDateTime endDisplayDate;
    private LocationCode locationCode;

    @Builder
    @QueryProjection
    public ProductSearchResponse(Long productId,  String description, String content, String event, Long categoryId, LocalDateTime startDisplayDate, LocalDateTime endDisplayDate, LocationCode locationCode) {
        this.productId = productId;
        this.description = description;
        this.content = content;
        this.event = event;
        this.categoryId = categoryId;
        this.startDisplayDate = startDisplayDate;
        this.endDisplayDate = endDisplayDate;
        this.locationCode = locationCode;
    }
}
