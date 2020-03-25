package kr.team.ticketing.web.admin.product;

import kr.team.ticketing.domain.product.Product;
import kr.team.ticketing.domain.product.detail.Option;
import kr.team.ticketing.service.admin.product.ProductService;
import kr.team.ticketing.service.admin.product.option.OptionService;
import kr.team.ticketing.web.admin.product.request.OptionRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/admin/v1/products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	private final OptionService optionService;

	@PostMapping
	public ResponseEntity saveProduct(@RequestBody Product product) {
		Product save = productService.save(product);
		URI uri = linkTo(ProductController.class).slash(save.getId()).toUri();
		return ResponseEntity.created(uri).body(save);
	}

	@PutMapping("/{productId}")
	public ResponseEntity updateProduct(@PathVariable Long productId,
		@RequestBody Product product) {
		Product update = productService.update(productId, product);
		return ResponseEntity.ok(update);
	}

	@GetMapping
	public ResponseEntity findProducts(Pageable pageable) {
		Page<Product> products = productService.find(pageable);
		return ResponseEntity.ok(products);
	}

	@GetMapping("/{productId}")
	public ResponseEntity findProduct(@PathVariable Long productId) {
		Product product = productService.find(productId);
		return ResponseEntity.ok(product);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity deleteProduct(@PathVariable Long productId) {
		productService.delete(productId);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{productId}/options")
	public ResponseEntity saveOptions(@PathVariable Long productId,
		@RequestBody List<OptionRequest> optionRequests) {
		List<Option> saveOptions = optionService.save(productId, optionRequests);
		URI uri = linkTo(ProductController.class).slash(productId).slash("options").toUri();
		return ResponseEntity.created(uri).body(saveOptions);
	}

	@PutMapping("/{productId}/options/{optionId}")
	public ResponseEntity updateOption(@PathVariable Long productId,
		@PathVariable Long optionId,
		@RequestBody OptionRequest request) {
		optionService.update(productId, optionId, request);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{productId}/options")
	public ResponseEntity findOptions(@PathVariable Long productId,
		Pageable pageable) {
		Page<Option> options = optionService.find(productId, pageable);
		return ResponseEntity.ok(options);
	}

	@GetMapping("/{productId}/options/{optionId}")
	public ResponseEntity findOption(@PathVariable Long productId,
		@PathVariable Long optionId) {
		Option option = optionService.find(productId, optionId);
		return ResponseEntity.ok(option);
	}

	@DeleteMapping("/{productId}/options/{optionId}")
	public ResponseEntity deleteOption(@PathVariable Long productId,
		@PathVariable Long optionId) {
		optionService.delete(productId, optionId);
		return ResponseEntity.noContent().build();
	}
}
