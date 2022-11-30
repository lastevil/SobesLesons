package org.homework.services;

import lombok.RequiredArgsConstructor;
import org.homework.converters.StudentsConverter;
import org.homework.dto.StudentDto;
import org.homework.entitys.Student;
import org.homework.exceptions.ResourceNotFoundException;
import org.homework.exceptions.ValidateException;
import org.homework.repositorys.StudentsRepository;
import org.homework.validators.StudentsValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentsService {
    private final StudentsRepository repository;
    private final StudentsConverter converter;
    private final StudentsValidator validator;

    public void addStudent(StudentDto studentDto) {
        validator.validate(studentDto);
        repository.save(converter.fromDto(studentDto));
    }

    public StudentDto getStudentById(Long id) {
        Student student = repository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Студент не найден"));
        return converter.fromEntity(student);
    }

    public void removeStudentById(Long id) {
        Student student = repository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Студент не найден"));
        repository.delete(student);
    }
    @Transactional
    public void updateStudent(StudentDto studentDto) {
        Student student = repository.
                findById(studentDto.getId()).orElseThrow(() -> new ValidateException(List.of("Студент не найден")));
        validator.validate(studentDto);
        student.setName(studentDto.getName());
        student.setMark(studentDto.getMark());
        repository.save(student);
    }
}
