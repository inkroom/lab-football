package com.nsu.controller.publicity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/info")
public class IndexInfoController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		return "/publicity/index";
	}
}
