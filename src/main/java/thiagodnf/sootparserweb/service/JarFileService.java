package thiagodnf.sootparserweb.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JarFileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JarFileService.class);

	public List<String> getClasses(String jarFile) throws IOException{
		
		List<String> classNames = new ArrayList<String>();
		
		ZipInputStream zip = new ZipInputStream(new FileInputStream(jarFile));
		
		for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
		    if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
		        // This ZipEntry represents a class. Now, what class does it represent?
		        String className = entry.getName().replace('/', '.'); // including ".class"
		        classNames.add(className.substring(0, className.length() - ".class".length()));
		    }
		}
		
		return classNames;
	}
	
	public File getTemporaryFile(byte[] data) throws IOException {

		File tmp = File.createTempFile("sootparser", ".jar");

		tmp.deleteOnExit();

		FileUtils.writeByteArrayToFile(tmp, data);

		return tmp;
	}
}
