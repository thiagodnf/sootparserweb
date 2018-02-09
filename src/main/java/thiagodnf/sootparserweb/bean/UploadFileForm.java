package thiagodnf.sootparserweb.bean;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import thiagodnf.sootparserweb.annotation.NotEmptyFile;

public class UploadFileForm {

	@NotNull
	@NotEmptyFile
	private MultipartFile file;
	
	private String mainClass;
	
	private Boolean verbose;
	
	private Boolean allowPhantomRefs;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

	public Boolean getVerbose() {
		return verbose;
	}

	public void setVerbose(Boolean verbose) {
		this.verbose = verbose;
	}

	public Boolean getAllowPhantomRefs() {
		return allowPhantomRefs;
	}

	public void setAllowPhantomRefs(Boolean allowPhantomRefs) {
		this.allowPhantomRefs = allowPhantomRefs;
	}
}
