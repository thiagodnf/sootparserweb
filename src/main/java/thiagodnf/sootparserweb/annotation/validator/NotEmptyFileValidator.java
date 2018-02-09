package thiagodnf.sootparserweb.annotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

import thiagodnf.sootparserweb.annotation.NotEmptyFile;

public class NotEmptyFileValidator implements ConstraintValidator<NotEmptyFile, MultipartFile> {

	@Override
	public void initialize(NotEmptyFile contraint) {

	}

	@Override
	public boolean isValid(MultipartFile file, ConstraintValidatorContext cxt) {
		return !file.isEmpty();
	}
}
