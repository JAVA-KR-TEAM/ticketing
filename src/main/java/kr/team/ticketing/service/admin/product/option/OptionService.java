package kr.team.ticketing.service.admin.product.option;

import kr.team.ticketing.domain.object.generic.money.Money;
import kr.team.ticketing.domain.object.generic.money.Ratio;
import kr.team.ticketing.domain.product.Product;
import kr.team.ticketing.domain.product.ProductRepository;
import kr.team.ticketing.domain.product.detail.Option;
import kr.team.ticketing.domain.product.detail.OptionRepository;
import kr.team.ticketing.domain.product.detail.ProductType;
import kr.team.ticketing.web.admin.product.request.OptionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;
    private final ProductRepository productRepository;

    @Transactional
    public List<Option> save(Long productId, List<OptionRequest> params) {
        Product product = productRepository.getOne(productId);
        List<Option> options = params.stream()
                .map(OptionService::convert)
                .collect(toList());
        options.forEach(o -> o.setProduct(product));
        optionRepository.saveAll(options);
        return options;
    }

    @Transactional
    public void update(Long productId, Long optionId, OptionRequest param) {
        Product product = productRepository.getOne(productId);
        Option option = optionRepository.getOne(optionId);
        option.update(param);
        option.setProduct(product);
        optionRepository.save(option);
    }

    @Transactional
    public Page<Option> find(Long productId, Pageable pageable) {
        return optionRepository.findAllByProductId(productId, pageable);
    }

    @Transactional
    public Option find(Long productId, Long optionId) {
        return optionRepository.findByProductIdAndId(productId, optionId);
    }

    @Transactional
    public void delete(Long productId, Long optionId) {
        Product product = productRepository.getOne(productId);
        product.getOptions().removeIf(option -> option.getId().equals(optionId));
    }

    private static Option convert(OptionRequest param) {
        return Option.builder()
                .price(Money.wons(param.getPrice()))
                .discountRate(Ratio.valueOf(param.getDiscountRate()))
                .productType(ProductType.valueOf(param.getProductType()))
                .build();
    }
}
