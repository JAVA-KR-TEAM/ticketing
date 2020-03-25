package kr.team.ticketing.service.admin.product.display;

import kr.team.ticketing.domain.object.Email;
import kr.team.ticketing.domain.product.display.Address;
import kr.team.ticketing.domain.product.display.Display;
import kr.team.ticketing.domain.product.display.DisplayRepository;
import kr.team.ticketing.domain.product.exception.DisplayNotFoundException;
import kr.team.ticketing.web.admin.product.request.DisplayParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DisplayService {
    private final DisplayRepository displayRepository;

    @Transactional
    public Display save(DisplayParam displayParam) {
        Display display = convert(displayParam);
        displayRepository.save(display);
        return display;
    }

    @Transactional
    public Page<Display> find(Pageable pageable) {
        return displayRepository.findAll(pageable);
    }

    @Transactional
    public Display find(Long displayId) {
        return displayRepository.findById(displayId)
                .orElseThrow(() -> new DisplayNotFoundException("해당 전시 정보가 존재하지 않습니다."));
    }

    @Transactional
    public void update(Long displayId, DisplayParam displayParam) {
        Display display = displayRepository.getOne(displayId);
        display.update(convert(displayParam));
        displayRepository.save(display);
    }

    @Transactional
    public void delete(Long displayId) {
        displayRepository.deleteById(displayId);
    }

    private Display convert(DisplayParam displayParam) {
        return Display.builder()
                .productId(displayParam.getProductId())
                .openingHours(displayParam.getOpeningHours())
                .address(Address.builder()
                        .place(displayParam.getPlace())
                        .placeLot(displayParam.getPlaceLot())
                        .placeStreet(displayParam.getPlaceStreet())
                        .build())
                .homePage(displayParam.getHomePage())
                .tel(displayParam.getTel())
                .email(new Email(displayParam.getEmail()))
                .build();
    }
}
