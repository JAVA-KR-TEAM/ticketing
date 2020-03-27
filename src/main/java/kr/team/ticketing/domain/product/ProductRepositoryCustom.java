package kr.team.ticketing.domain.product;


import kr.team.ticketing.web.index.request.SearchCondition;
import kr.team.ticketing.web.index.response.ProductSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductRepositoryCustom {

    Page<ProductSearchResponse> searchByDynamicCondition(SearchCondition searchCondition, Pageable pageable);

}
