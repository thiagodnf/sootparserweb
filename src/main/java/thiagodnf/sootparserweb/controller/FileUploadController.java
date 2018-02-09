package thiagodnf.sootparserweb.controller;


import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import soot.Scene;
import soot.SootClass;
import soot.jimple.toolkits.callgraph.CallGraph;
import thiagodnf.sootparserweb.bean.UploadFileForm;
import thiagodnf.sootparserweb.service.JarFileService;
import thiagodnf.sootparserweb.service.SootService;

@Controller
public class FileUploadController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private SootService sootService;
	
	@Autowired
	private JarFileService jarFileService;
	
	@GetMapping("/file-upload")
	public String fileUploadRedirect() {
		return "redirect:/";
	}
			
	@PostMapping("/file-upload")
	public String fileUpload(@Valid UploadFileForm uploadFileForm, BindingResult result, Model model) throws IOException {

		LOGGER.info("Uploading file...");

		if (result.hasErrors()) {
			return "index";
		}
		
		LOGGER.info("==========================");
		LOGGER.info("Starting SootParser Parser");
		LOGGER.info("==========================");
		
		File tmp = jarFileService.getTemporaryFile(uploadFileForm.getFile().getBytes());
		
		List<String> classesInsideJarFile = jarFileService.getClasses(tmp.getAbsolutePath());
		
		List<String> classpaths = sootService.getClasspaths(tmp.getAbsolutePath());
		
		sootService.defineTheClassPath(classpaths);
		sootService.defineTheParameterSettings();
		sootService.defineTheMainClass(uploadFileForm.getMainClass());
		
		CallGraph cg = sootService.buildCallGraph();
		
		for (SootClass c : Scene.v().getClasses()) {
			if (classesInsideJarFile.contains(c.toString())) {
				System.out.println(c);
			}
		}
		
		System.out.println("oi");
		
		return "result";
	}
	
	
}
