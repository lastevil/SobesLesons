package lessonFour;

import lessonFour.dto.DurationCheck;
import lessonFour.entites.*;
import lessonFour.querys.DropBaseTables;
import lessonFour.querys.InitQuery;
import lessonFour.querys.MyQuery;

import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DBConnectorHandler {
    private Connection conn;
    private Statement statement;
    private final String[] CON_STR = {"jdbc:h2:~/testdb", "sa", ""};

    private DBConnectorHandler() {
    }

    public static DBConnectorHandler createHandler() {
        return new DBConnectorHandler();
    }

    public void connectDB() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection(CON_STR[0], CON_STR[1], CON_STR[2]);
        conn.setAutoCommit(true);
        System.out.println("База Подключена!");
    }

    public void createDB() throws ClassNotFoundException, SQLException {
        statement = conn.createStatement();
        statement.execute(InitQuery.CREATE_MOVIES.getQuery());
        statement.execute(InitQuery.CREATE_TIMETABLE.getQuery());
        statement.execute(InitQuery.CREATE_USERS.getQuery());
        statement.execute(InitQuery.CREATE_TICKETS.getQuery());
        System.out.println("Таблица создана или уже существует.");
        initBase(conn);

    }
    public void deleteDB() throws SQLException {
        statement = conn.createStatement();
        statement.execute(DropBaseTables.DROP_TICKETS.getQuery());
        statement.execute(DropBaseTables.DROP_USERS.getQuery());
        statement.execute(DropBaseTables.DROP_TIMETABLE.getQuery());
        statement.execute(DropBaseTables.DROP_MOVIES.getQuery());
        System.out.println("Таблицы удалены.");
    }

    public void closeDB() throws ClassNotFoundException, SQLException {
        conn.close();
        System.out.println("Соединения закрыты");
        if (statement != null) {
            statement.close();
        }
    }

    public Long getMovieIdByTitle(String title) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("SELECT id FROM movies WHERE title=?");
        statement.setString(1, title);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            return resultSet.getLong("id");
        }
        resultSet.close();
        return 0l;
    }

    public Long getUserIdByName(String name) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("SELECT id FROM users WHERE name=?");
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            return resultSet.getLong("id");
        }
        resultSet.close();
        statement.close();
        return 0l;
    }

    public List<Movie> getMovies() throws SQLException {
        List<Movie> movieList = new ArrayList<>();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movies ");
        while (resultSet.next()) {
            Movie temp = new Movie(
                    resultSet.getLong("id"),
                    resultSet.getString("title"),
                    Duration.getDurationOnValue(resultSet.getInt("duration")));
            movieList.add(temp);
        }
        resultSet.close();
        return movieList;
    }

    public void getTimeErrors() throws SQLException {
        List<DurationCheck> check = new ArrayList<>();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(MyQuery.TIME_ERRORS_QUERY.getQuery());
        while (resultSet.next()) {
            DurationCheck temp = new DurationCheck(
                    resultSet.getString("TITLE"),
                    resultSet.getTimestamp("STARTDATE").toLocalDateTime(),
                    resultSet.getInt("DURATION"));
            check.add(temp);
        }
        resultSet.close();
        for (int i = 1; i < check.size() - 1; i++) {
            long diff = ChronoUnit.MINUTES.between(check.get(i - 1).getStartDate(), check.get(i).getStartDate()) - check.get(i - 1).getDuration();
            if (diff < 0) {
                System.out.println("Ошибка расписания!");
                System.out.print(check.get(i - 1).getTitle() + " начало в " + check.get(i - 1).getStartDate() + " длительность:" + check.get(i - 1).getDuration());
                System.out.println(" - " + check.get(i).getTitle() + " начало в " + check.get(i).getStartDate() + " длительность:" + check.get(i).getDuration());
            }
        }
    }

    public void getBigInterval() throws SQLException {
        List<DurationCheck> check = new ArrayList<>();
        statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(MyQuery.BIG_INTERVAL_QUERY.getQuery());
        while (resultSet.next()) {
            DurationCheck temp = new DurationCheck(
                    resultSet.getString("TITLE"),
                    resultSet.getTimestamp("STARTDATE").toLocalDateTime(),
                    resultSet.getInt("DURATION"));
            check.add(temp);
        }
        resultSet.close();
        Map<String, Long> result = new TreeMap<>();
        for (int i = 1; i < check.size() - 1; i++) {
            long diff = ChronoUnit.MINUTES.between(check.get(i - 1).getStartDate(), check.get(i).getStartDate()) - check.get(i - 1).getDuration();
            if (diff > 30) {
                StringBuilder sb = new StringBuilder();
                sb.append(check.get(i - 1).getTitle() + " начало в " + check.get(i - 1).getStartDate() + " длительность:" + check.get(i - 1).getDuration());
                sb.append(" - время начала второго фильма " + check.get(i).getStartDate() + " длительность перерыва:" + diff);
                result.put(sb.toString(), diff);
            }
        }
        System.out.println("Перерывы больше 30 минут в порядке убывания:");
        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(a -> System.out.println(a.getKey()));
    }

    public void getMoviesWithPrices() throws SQLException {
        statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(MyQuery.MOVIES_WITH_PRICE_QUERY.getQuery());
        double totalPrice = 0;
        int totalVisitors = 0;
        double count = 0;
        System.out.println("Сводная таблица:");
        while (resultSet.next()) {
            count++;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(resultSet.getString("TITLE"));
            int visitors = resultSet.getInt("TICKETS");
            totalVisitors += visitors;
            stringBuilder.append(" всего посетителей: " + visitors);
            double avgVisitors = resultSet.getDouble("AVG_VISITORS");
            stringBuilder.append(" среднее по сеансам: " + avgVisitors);
            double price = resultSet.getDouble("PRICE");
            totalPrice += price;
            stringBuilder.append(" сумма: " + price);
            System.out.println(stringBuilder);
        }
        double totalAvgVisitors = totalVisitors / count;
        System.out.println("Итого: посетителей: " + totalVisitors + ", среднее по фильмам " + totalAvgVisitors + ", сумма " + totalPrice);
    }
    public void getVisitorsWithPricesBetweenHour(int afterHour, int beforeHour) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(MyQuery.VISITORS_WITH_SUM_QUERY.getQuery());
        statement.setInt(1, afterHour);
        statement.setInt(2, beforeHour);
        ResultSet resultSet = statement.executeQuery();
        System.out.println("В помежутке между " + afterHour + " часами и " + beforeHour + " часами");
        int counter = 0;
        while (resultSet.next()) {
            counter++;
            System.out.println("Колличество посетителей: " + resultSet.getInt("VISITORS") +
                    ", сборы: " + resultSet.getDouble("SUM") +
                    ", время начала: " + resultSet.getTimestamp("STARTDATE"));
        }
        if (counter==0)System.out.println("небыло куплено биллетов");
    }

    private void initBase(Connection connection) throws SQLException {
        DBinitializer initializer = DBinitializer.create(connection);
        initializer.createMovies();
        initializer.createTimetable();
        initializer.createUsers();
        initializer.buyTickets();
        System.out.println("Данные загружены");
    }
}
