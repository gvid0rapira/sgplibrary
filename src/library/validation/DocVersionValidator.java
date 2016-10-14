package library.validation;

import library.model.DocVersion;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DocVersionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return DocVersion.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errs) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errs, "approDate", 
				"doc.version.appro_date.empty",
				"Поле 'Дата введения' должно быть заполнено.");
	}

}
