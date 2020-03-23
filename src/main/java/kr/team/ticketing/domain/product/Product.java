package kr.team.ticketing.domain.product;

import kr.team.ticketing.domain.BaseEntity;
import kr.team.ticketing.domain.product.detail.Option;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {
    @Column
    private String description;
    @Lob
    private String content;
    @Column
    private String event;
    @Column
    private Long categoryId;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options = new ArrayList<>();

    @Builder
    public Product(Long id, String description, String content, String event, Long categoryId) {
        this.id = id;
        this.description = description;
        this.content = content;
        this.event = event;
        this.categoryId = categoryId;
    }

    public void addOption(Option option) {
        option.setProduct(this);
        this.options.add(option);
    }

    public void addOptions(List<Option> options) {
        options.forEach(o -> addOption(o));
    }

    public void update(Product product) {
        this.description = product.getDescription();
        this.content = product.getContent();
        this.event = product.getEvent();
        this.categoryId = product.getCategoryId();
    }
}
