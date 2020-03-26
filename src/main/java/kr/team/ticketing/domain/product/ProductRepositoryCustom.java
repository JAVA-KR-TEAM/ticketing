package kr.team.ticketing.domain.product;

import kr.team.ticketing.web.admin.product.request.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductRepositoryCustom {

    Page<Product> searchAll(Pageable pageable);//장르상관없이 전부
    Page<Product> searchByDynamicCondition(SearchCondition searchCondition, Pageable pageable);

}
