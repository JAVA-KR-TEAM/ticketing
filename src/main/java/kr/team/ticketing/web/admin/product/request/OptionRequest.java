package kr.team.ticketing.web.admin.product.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OptionRequest {
    private String productType;
    private double discountRate;
    private int price;

    @Builder
    public OptionRequest(String productType, double discountRate, int price) {
        this.productType = productType;
        this.discountRate = discountRate;
        this.price = price;
    }
}
