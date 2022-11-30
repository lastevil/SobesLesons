package lessonFour.querys;

public enum InitQuery {
    CREATE_MOVIES("CREATE TABLE if not exists movies " +
            "(id IDENTITY," +
            "title VARCHAR(255) NOT NULL," +
            "duration INT NOT NULL," +
            "PRIMARY KEY(id)" +
            ");"),
    CREATE_TIMETABLE("CREATE TABLE if not exists timetable" +
            "(id IDENTITY," +
            "movies_id INTEGER NOT NULL," +
            "price INTEGER NOT NULL," +
            "place_count INT NOT NULL," +
            "startDate Timestamp NOT NULL," +
            "PRIMARY KEY(id)," +
            "FOREIGN KEY (movies_id) references movies (id)" +
            ");"),
    CREATE_USERS("CREATE TABLE if not exists users " +
            "(id IDENTITY," +
            "name VARCHAR(255) NOT NULL," +
            "phone VARCHAR(255) NOT NULL," +
            "email VARCHAR(255) NOT NULL," +
            "PRIMARY KEY(id)" +
            ");"),
    CREATE_TICKETS("CREATE TABLE if not exists tickets " +
            "(id IDENTITY," +
            "user_id INT NOT NULL," +
            "timetable_id INT NOT NULL," +
            "ticket_count INT NOT NULL," +
            "PRIMARY KEY(id)," +
            "FOREIGN KEY (user_id) references users (id)," +
            "FOREIGN KEY (timetable_id) references timetable (id)" +
            ");"),
    INSERT_MOVIES("INSERT INTO movies (title, duration) values (?,?)"),
    INSERT_TIMETABLE("INSERT INTO timetable (movies_id, price, place_count, startDate) " +
            "values (?,?,?,?)"),
    INSERT_USERS("INSERT INTO users (name, email, phone) values(?,?,?)"),
    SELECT_PLACES("SELECT place_count FROM timetable WHERE id=?;"),
    INSERT_TICKETS("INSERT INTO tickets (user_id, timetable_id, ticket_count) " +
            "values (?,?,?);"),
    UPDATE_PLACES("UPDATE timetable SET place_count = ? Where id = ?;");

    private final String query;

    InitQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
