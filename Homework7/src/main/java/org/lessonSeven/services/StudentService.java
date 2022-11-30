package org.lessonSeven.services;

import lombok.RequiredArgsConstructor;
import org.lessonSeven.converter.StudentsMapper;
import org.lessonSeven.dto.StudentDto;
import org.lessonSeven.entitys.Student;
import org.lessonSeven.exceptions.ResourceNotFoundException;
import org.lessonSeven.exceptions.ValidateException;
import org.lessonSeven.repositorys.StudentsRepository;
import org.lessonSeven.validators.StudentValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentsRepository repository;
    private final StudentValidator validator;

    public StudentDto getStudentById(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Студент не найден"));
        return StudentsMapper.MAPPER.toDTO(student);
    }

    public List<StudentDto> getAllStudents() {
        return repository.findAll().stream()
                .map( StudentsMapper.MAPPER::toDTO).collect(Collectors.toList());
    }

    public void updateStudent(StudentDto studentDto) {
        validator.validate(studentDto);
        Student student = repository.findById(studentDto.getId())
                .orElseThrow(() -> new ValidateException(List.of("Студентне с id:" + studentDto.getId() + " найден")));
        student.setAge(studentDto.getAge());
        student.setName(studentDto.getName());
        repository.save(student);
    }

    public void removeStudentById(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Студентне с id:" + id + " найден"));
        repository.delete(student);
    }

    public void addStudent(StudentDto studentDto) {
        validator.validate(studentDto);
        Student student =  StudentsMapper.MAPPER.toEntity(studentDto);
        repository.save(student);
    }
}
