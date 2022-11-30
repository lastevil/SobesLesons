package org.homework.converters;

import org.homework.dto.StudentDto;
import org.homework.entitys.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentsConverter {
    public Student fromDto(StudentDto studentDto) {
        return new Student(studentDto.getName(),studentDto.getMark());
    }

    public StudentDto fromEntity(Student student) {
        return new StudentDto(student.getId(),student.getName(),student.getMark());
    }
}
