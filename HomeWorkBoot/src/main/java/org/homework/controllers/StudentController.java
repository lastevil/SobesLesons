package org.homework.controllers;


import lombok.RequiredArgsConstructor;
import org.homework.dto.StudentDto;
import org.homework.services.StudentsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentsService service;

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable Long id) {
        return service.getStudentById(id);
    }

    @PostMapping("")
    public void addStudent(@RequestBody StudentDto studentDto) {
        service.addStudent(studentDto);
    }

    @DeleteMapping("/{id}")
    public void removeStudentById(@PathVariable Long id) {
        service.removeStudentById(id);
    }

    @PostMapping("/update")
    public void updateStudent(@RequestBody StudentDto studentDto) {
        service.updateStudent(studentDto);
    }

    @PostMapping("/insertData")
    public void insertThousandStudents() {
        for (int i = 0; i < 1000; i++) {
            service.addStudent(new StudentDto(("Student" + (i + 1)),(1+(int)(Math.random()*5))));
        }
    }
}
