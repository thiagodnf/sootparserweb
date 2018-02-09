package thiagodnf.sootparserweb.controller;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import thiagodnf.sootparserweb.bean.UploadFileForm;

@Controller
public class IndexController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	@PostConstruct
	private void initIt() {
		LOGGER.info("Temporary Folder: " + System.getProperty("java.io.tmpdir"));
	}
	
	@GetMapping("/")
	public String index(Model model) {
		
		LOGGER.info("Loading index page");

		model.addAttribute("uploadFileForm", new UploadFileForm());
		
		return "index";
	}
}
