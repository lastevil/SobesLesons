package org.lessonSeven.validators;

import org.lessonSeven.dto.StudentDto;
import org.lessonSeven.exceptions.ValidateException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentValidator {
    List<String> errors = new ArrayList<>();

    public void validate(StudentDto studentDto) {
        if (studentDto.getName().isBlank()) {
            errors.add("Имя не может быть пустым!");
        }
        if (studentDto.getAge() < 1 || studentDto.getAge() > 120) {
            errors.add("Не верный возраст");
        }
        if (!errors.isEmpty()) {
            throw new ValidateException(errors);
        }
    }
}
