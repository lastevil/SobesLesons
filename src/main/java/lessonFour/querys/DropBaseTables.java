package lessonFour.querys;

public enum DropBaseTables {
    DROP_TICKETS("DROP TABLE TICKETS"),
    DROP_USERS("DROP TABLE USERS"),
    DROP_TIMETABLE("DROP TABLE TIMETABLE"),
    DROP_MOVIES("DROP TABLE MOVIES");
    private final String query;

    DropBaseTables(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
