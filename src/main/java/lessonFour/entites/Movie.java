package lessonFour.entites;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private Long id;

    public Movie(String title, Duration duration) {
        this.title = title;
        this.duration = duration;
    }

    private String title;
    private Duration duration;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration.getMinutes() +
                '}';
    }
}
