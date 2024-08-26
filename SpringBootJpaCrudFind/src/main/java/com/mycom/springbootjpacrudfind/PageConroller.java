package com.mycom.springbootjpacrudfind;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageConroller {
	// view resolver가 static에서 찾아줌
	@GetMapping("/mypage")
	public String mypage() {
		System.out.println("/mypage");
		return "/mypage.html";
	}
}
