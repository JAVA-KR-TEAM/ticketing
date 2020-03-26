package kr.team.ticketing.domain.product.display;

import lombok.Getter;

@Getter
public enum LocationCode {
    SE("서울"),
    GG("경기도"),
    BS("부산"),
    DH("대학로"),
    ET("기타지역");

    private String name;

    LocationCode (String name) {
        this.name = name;
    }
}
