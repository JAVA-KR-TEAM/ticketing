package kr.team.ticketing.web.index;

import kr.team.ticketing.domain.product.Product;
import kr.team.ticketing.domain.product.ProductRepository;
import kr.team.ticketing.web.admin.product.request.SearchCondition;
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

	@GetMapping("/api")
	public String index() {
		return "Hello Ticket";
	}

	@GetMapping("/search")
	public Page<Product> searchByCondition(@RequestBody SearchCondition searchCondition,
	   @PageableDefault(size = 10, sort = "endDate", direction = Sort.Direction.DESC) Pageable pageable) {
		return productRepository.searchByDynamicCondition(searchCondition, pageable);
	}
}
