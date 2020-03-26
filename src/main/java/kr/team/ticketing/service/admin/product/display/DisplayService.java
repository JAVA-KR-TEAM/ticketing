package kr.team.ticketing.service.admin.product.display;

import kr.team.ticketing.domain.object.Email;
import kr.team.ticketing.domain.product.display.Address;
import kr.team.ticketing.domain.product.display.Display;
import kr.team.ticketing.domain.product.display.DisplayRepository;
import kr.team.ticketing.domain.product.exception.DisplayNotFoundException;
import kr.team.ticketing.web.admin.product.request.DisplayRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kr.team.ticketing.domain.object.utils.DateTimeUtils.createDateTime;

@Service
@RequiredArgsConstructor
public class DisplayService {
	private final DisplayRepository displayRepository;

	@Transactional
	public Display save(DisplayRequest displayRequest) {
		Display display = convert(displayRequest);
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
	public void update(Long displayId, DisplayRequest displayRequest) {
		Display display = displayRepository.getOne(displayId);
		display.update(convert(displayRequest));
		displayRepository.save(display);
	}

	@Transactional
	public void delete(Long displayId) {
		displayRepository.deleteById(displayId);
	}

	private Display convert(DisplayRequest displayRequest) {
		return Display.builder()
			.productId(displayRequest.getProductId())
			.openingHours(displayRequest.getOpeningHours())
			.startDate(createDateTime(displayRequest.getStartDate()))
			.endDate(createDateTime(displayRequest.getEndDate()))
			.address(Address.builder()
				.place(displayRequest.getPlace())
				.placeLot(displayRequest.getPlaceLot())
				.placeStreet(displayRequest.getPlaceStreet())
				.locationCode(displayRequest.getLocationCode())
				.build())
			.homePage(displayRequest.getHomePage())
			.tel(displayRequest.getTel())
			.email(new Email(displayRequest.getEmail()))
			.build();
	}
}
