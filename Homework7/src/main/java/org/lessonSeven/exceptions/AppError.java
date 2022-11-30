package org.lessonSeven.exceptions;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class AppError {
    private int statusCode;
    private String message;
}
