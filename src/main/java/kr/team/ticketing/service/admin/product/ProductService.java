package kr.team.ticketing.service.admin.product;

import kr.team.ticketing.domain.product.Product;
import kr.team.ticketing.domain.product.ProductRepository;
import kr.team.ticketing.domain.product.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public Product find(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("해당 상품이 존재하지 않습니다."));
    }

    @Transactional
    public Page<Product> find(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void delete(Long productId) {
        Product product = productRepository.getOne(productId);
        product.getOptions().clear();
        productRepository.delete(product);
    }

    @Transactional
    public Product update(Long productId, Product product) {
        Product updateProduct = productRepository.getOne(productId);
        updateProduct.update(product);
        return productRepository.save(updateProduct);
    }
}
