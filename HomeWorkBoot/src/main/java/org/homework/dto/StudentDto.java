package org.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    Long id;
    String name;
    Integer mark;

    public StudentDto(String name, Integer mark) {
        this.name = name;
        this.mark = mark;
    }
}
