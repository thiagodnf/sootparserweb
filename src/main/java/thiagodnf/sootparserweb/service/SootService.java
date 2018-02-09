package thiagodnf.sootparserweb.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.jimple.toolkits.callgraph.CHATransformer;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.options.Options;

@Service
public class SootService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SootService.class);

	public List<String> getClasspaths(String jarFile) {

		List<String> classpaths = new ArrayList<>();

		String dir = System.getProperty("user.dir") + File.separator + "lib" + File.separator + "java-1.7";

		classpaths.add(dir + File.separator + "javaws.jar");
		classpaths.add(dir + File.separator + "jce.jar");
		classpaths.add(dir + File.separator + "jsse.jar");
		classpaths.add(dir + File.separator + "rt.jar");

		classpaths.add(jarFile);

		return classpaths;
	}

	public void defineTheClassPath(List<String> classpath) {

		LOGGER.info("Defining the Classpaths");

		Scene.v().setSootClassPath(String.join(";", classpath));

		classpath.stream().forEach(LOGGER::info);
	}

	public void defineTheMainClass(String cls) {

		LOGGER.info("Defining the Main Class");

		SootClass mainClass = Scene.v().loadClassAndSupport(cls);
		System.out.println(mainClass);
		mainClass.setApplicationClass();
		Scene.v().setMainClass(mainClass);

		LOGGER.info("\t Name: " + mainClass.getName());
		LOGGER.info("\t Short Name: " + mainClass.getShortName());

		List<SootMethod> ep = Scene.v().getEntryPoints();

		System.out.println(ep);

		ep.add(Scene.v().getMainMethod());
		Scene.v().setEntryPoints(ep);
	}

	public CallGraph buildCallGraph() {

		LOGGER.info("Building the CallGraph");

		Scene.v().loadNecessaryClasses();

		CHATransformer.v().transform();

		return Scene.v().getCallGraph();
	}

	public void defineTheParameterSettings() {

		LOGGER.info("Defining the Parameter Settings");

		Options.v().set_whole_program(true);
		Options.v().set_allow_phantom_refs(true);
		Options.v().setPhaseOption("cg", "verbose:true");
	}
}
