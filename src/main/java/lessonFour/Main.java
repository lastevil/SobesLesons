package lessonFour;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DBConnectorHandler handler = DBConnectorHandler.createHandler();
            handler.connectDB();
            handler.createDB();
            System.out.println();
            handler.getTimeErrors();
            System.out.println();
            handler.getBigInterval();
            System.out.println();
            handler.getMoviesWithPrices();
            System.out.println();
            handler.getVisitorsWithPricesBetweenHour(9,15);
            handler.getVisitorsWithPricesBetweenHour(15,18);
            handler.getVisitorsWithPricesBetweenHour(18,21);
            handler.getVisitorsWithPricesBetweenHour(21,0);
            System.out.println();
            handler.deleteDB();
            handler.closeDB();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
