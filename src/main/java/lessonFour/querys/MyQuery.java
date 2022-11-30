package lessonFour.querys;

public enum MyQuery {
    TIME_ERRORS_QUERY("SELECT " +
            "MOVIES.TITLE, " +
            "TIMETABLE.STARTDATE, " +
            "MOVIES.DURATION " +
            "FROM TIMETABLE " +
            "JOIN MOVIES ON TIMETABLE.MOVIES_ID=MOVIES.ID " +
            "ORDER BY STARTDATE ASC"),
    BIG_INTERVAL_QUERY("SELECT " +
            "MOVIES.TITLE, " +
            "TIMETABLE.STARTDATE, " +
            "MOVIES.DURATION " +
            "FROM TIMETABLE " +
            "JOIN MOVIES ON TIMETABLE.MOVIES_ID=MOVIES.ID " +
            "ORDER BY STARTDATE ASC"),
    MOVIES_WITH_PRICE_QUERY("SELECT " +
            "MOVIES.TITLE," +
            "SUM(TICKETS.TICKET_COUNT) AS TICKETS," +
            "AVG(TICKETS.TICKET_COUNT) AS AVG_VISITORS, " +
            "SUM(TICKETS.TICKET_COUNT*TIMETABLE.PRICE) AS PRICE " +
            "FROM TIMETABLE " +
            "INNER JOIN MOVIES ON TIMETABLE.MOVIES_ID=MOVIES.ID " +
            "LEFT JOIN TICKETS ON TICKETS.TIMETABLE_ID =TIMETABLE.ID " +
            "GROUP BY MOVIES.TITLE " +
            "ORDER BY PRICE DESC;"),
    VISITORS_WITH_SUM_QUERY("SELECT TICKETS.TICKET_COUNT AS VISITORS, " +
            "SUM(TICKETS.TICKET_COUNT*TIMETABLE.PRICE) AS SUM, " +
            "TIMETABLE.STARTDATE FROM TIMETABLE " +
            "JOIN TICKETS ON TICKETS.TIMETABLE_ID =TIMETABLE.ID " +
            "WHERE EXTRACT(HOUR FROM TIMETABLE.STARTDATE) BETWEEN ? and ? " +
            "GROUP BY TIMETABLE.STARTDATE;");
    private final String query;

    MyQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
