package org.lessonSeven.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDto {
    //уникальность по имени и возрасту не гарантируется
    Long id;
    String name;
    Integer age;
}
