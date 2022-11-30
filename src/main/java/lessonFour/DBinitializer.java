package lessonFour;

import lessonFour.entites.Duration;
import lessonFour.entites.Movie;
import lessonFour.querys.InitQuery;

import java.sql.*;
import java.time.LocalDateTime;

public class DBinitializer {
    private Connection conn;

    private DBinitializer(Connection connection) {
        this.conn = connection;
    }

    public static DBinitializer create(Connection connection) {
        return new DBinitializer(connection);
    }

    public void createMovies() throws SQLException {
        addMovie(new Movie("Фильм1", Duration.MINUTES_60));
        addMovie(new Movie("Фильм2", Duration.MINUTES_80));
        addMovie(new Movie("Фильм3", Duration.MINUTES_120));
        addMovie(new Movie("Фильм4", Duration.MINUTES_80));
        addMovie(new Movie("Фильм5", Duration.MINUTES_120));
    }

    public void createTimetable() throws SQLException {
        addToTimetableById(1l, 250, 20, LocalDateTime.of(2022, 11, 20, 10, 00));
        addToTimetableById(2l, 250, 20, LocalDateTime.of(2022, 11, 20, 11, 00));
        addToTimetableById(3l, 250, 20, LocalDateTime.of(2022, 11, 20, 12, 10));
        addToTimetableById(4l, 250, 20, LocalDateTime.of(2022, 11, 20, 13, 00));
        addToTimetableById(2l, 350, 20, LocalDateTime.of(2022, 11, 20, 14, 20));
        addToTimetableById(5l, 400, 20, LocalDateTime.of(2022, 11, 20, 17, 00));
        addToTimetableById(1l, 300, 20, LocalDateTime.of(2022, 11, 20, 20, 00));
        addToTimetableById(3l, 300, 20, LocalDateTime.of(2022, 11, 20, 21, 15));
    }

    public void createUsers() throws SQLException {
        addUser("user1", "test@mail.ru", "123455");
        addUser("user2", "test@yandex.ru", "231451");
        addUser("user3", "test@rambler.ru", "123142");
        addUser("user4", "test2@rambler.ru", "1123112");
    }

    public void buyTickets() throws SQLException {
        buyTicket(1l, 2l, 4);
        buyTicket(2l, 4l, 2);
        buyTicket(3l, 1l, 1);
        buyTicket(4l, 7l, 6);
    }

    private void addMovie(Movie movie) throws SQLException {
        PreparedStatement ps = this.conn.prepareStatement(InitQuery.INSERT_MOVIES.getQuery());
        ps.setString(1, movie.getTitle());
        ps.setInt(2, movie.getDuration().getMinutes());
        ps.execute();
    }

    private void addToTimetableById(Long moviesId, int price, int placeCount, LocalDateTime startDate) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(InitQuery.INSERT_TIMETABLE.getQuery());
        ps.setLong(1, moviesId);
        ps.setInt(2, price);
        ps.setInt(3, placeCount);
        ps.setTimestamp(4, Timestamp.valueOf(startDate));
        ps.execute();
    }

    public void addUser(String name, String email, String phone) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(InitQuery.INSERT_USERS.getQuery());
        ps.setString(1, name);
        ps.setString(2, email);
        ps.setString(3, phone);
        ps.execute();
    }

    public void buyTicket(Long user_id, Long timetable_id, int ticketsCount) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(InitQuery.SELECT_PLACES.getQuery());
        ps.setInt(1, Math.toIntExact(timetable_id));
        ResultSet resultSet = ps.executeQuery();
        int places = 0;
        if (resultSet.next()) {
            places = resultSet.getInt("place_count");
        }
        resultSet.close();
        ps.execute();
        if (places > 0 && places > ticketsCount) {
            ps = conn.prepareStatement(InitQuery.INSERT_TICKETS.getQuery());
            ps.setLong(1, user_id);
            ps.setLong(2, timetable_id);
            ps.setInt(3, ticketsCount);
            ps.execute();
            ps = conn.prepareStatement(InitQuery.UPDATE_PLACES.getQuery());
            ps.setInt(1, places - ticketsCount);
            ps.setLong(2, timetable_id);
            ps.execute();
        } else throw new IllegalArgumentException("Отсуствует достаточное колличество свободных мест");


    }
}
