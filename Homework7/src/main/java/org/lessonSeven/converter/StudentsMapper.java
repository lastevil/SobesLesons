package org.lessonSeven.converter;

import org.lessonSeven.dto.StudentDto;
import org.lessonSeven.entitys.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentsMapper {
    StudentsMapper MAPPER = Mappers.getMapper(StudentsMapper.class);

    Student toEntity(StudentDto studentDto);

    StudentDto toDTO(Student student);
}
