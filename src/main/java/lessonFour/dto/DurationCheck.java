package lessonFour.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class DurationCheck {
    String title;
    LocalDateTime startDate;
    int duration;
}
