package library.validation;

import library.model.StandartCard;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StandartCardValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return StandartCard.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "docCode", 
				"card.docCode.empty", 
				"Поле 'Код документа' должно быть заполнено.");
		ValidationUtils.rejectIfEmpty(errors, "name", 
				"card.name.empty", 
				"Поле 'Название' должно быть заполнено.");
	}

}
