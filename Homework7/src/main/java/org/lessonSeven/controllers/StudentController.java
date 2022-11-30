package org.lessonSeven.controllers;

import lombok.RequiredArgsConstructor;
import org.lessonSeven.dto.StudentDto;
import org.lessonSeven.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @GetMapping("")
    public List<StudentDto> getAllStudents() {
        return service.getAllStudents();
    }

    @PutMapping("/add")
    public void addStudent(@RequestBody StudentDto studentDto){
        service.addStudent(studentDto);
    }

    @PostMapping("/update")
    public void updateStudent(@RequestBody StudentDto studentDto){
        service.updateStudent(studentDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudentById(@PathVariable Long id){
        service.removeStudentById(id);
    }
}
