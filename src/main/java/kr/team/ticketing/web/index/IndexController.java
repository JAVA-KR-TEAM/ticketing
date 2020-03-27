package kr.team.ticketing.web.index;

import kr.team.ticketing.domain.product.ProductRepository;
import kr.team.ticketing.web.index.request.SearchCondition;
import kr.team.ticketing.web.index.response.ProductSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IndexController {

	private final ProductRepository productRepository;

	@GetMapping("/search")
	public Page<ProductSearchResponse> searchByCondition(@RequestBody SearchCondition searchCondition,
														 @PageableDefault(size = 10, sort = "endDisplayDate", direction = Sort.Direction.DESC) Pageable pageable) {
		return productRepository.searchByDynamicCondition(searchCondition, pageable);
	}
}
