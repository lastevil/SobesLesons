package org.lessonSeven.converter;

import javax.annotation.processing.Generated;
import org.lessonSeven.dto.StudentDto;
import org.lessonSeven.entitys.Student;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-30T16:55:15+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class StudentsMapperImpl implements StudentsMapper {

    @Override
    public Student toEntity(StudentDto studentDto) {
        if ( studentDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setId( studentDto.getId() );
        student.setName( studentDto.getName() );
        student.setAge( studentDto.getAge() );

        return student;
    }

    @Override
    public StudentDto toDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDto studentDto = new StudentDto();

        studentDto.setId( student.getId() );
        studentDto.setName( student.getName() );
        studentDto.setAge( student.getAge() );

        return studentDto;
    }
}
