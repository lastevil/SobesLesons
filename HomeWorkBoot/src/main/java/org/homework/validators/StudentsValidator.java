package org.homework.validators;

import org.homework.dto.StudentDto;
import org.homework.exceptions.ValidateException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class StudentsValidator {
    List<String> errors = new ArrayList<>();
    public void validate(StudentDto studentDto){
        if(studentDto.getName().isBlank()){
            errors.add("Имя не может быть пустым!");
        }
        if(studentDto.getMark()<1 || studentDto.getMark()>5){
            errors.add("Оценка не может быть больше 0 или меньше 1");
        }
        if (!errors.isEmpty()){
            throw new ValidateException(errors);
        }
    }
}
