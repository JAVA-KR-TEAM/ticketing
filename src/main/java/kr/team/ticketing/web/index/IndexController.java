package kr.team.ticketing.web.index;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	@GetMapping("/api")
	public String index() {
		return "Hello Ticket";
	}
}
