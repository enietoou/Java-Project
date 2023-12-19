import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.PreparedStatement;
import org.sqlite.JDBC;

public class SqlLiteDB {
    private Connection connection;
    private final String DB = "database.db";

    public void connect() throws SQLException {
        String url = "jdbc:sqlite:" + this.DB;
        connection = DriverManager.getConnection(url);
        System.out.println("Connected!");
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
            System.out.println("Disconnected!");
        }
    }

    public void createSchoolTable() {
        try {
            Statement statement = connection.createStatement();
            String createSchoolTableQuery = "CREATE TABLE IF NOT EXISTS School (" +
                    "id INTEGER PRIMARY KEY, " +
                    "district TEXT, " +
                    "name TEXT, " +
                    "county TEXT, " +
                    "grades TEXT, " +
                    "students INTEGER, " +
                    "teachers DOUBLE, " +
                    "calworks DOUBLE, " +
                    "lunch DOUBLE, " +
                    "computer INTEGER, " +
                    "expenditure DOUBLE, " +
                    "income DOUBLE, " +
                    "english DOUBLE, " +
                    "read DOUBLE, " +
                    "math DOUBLE)";
            statement.execute(createSchoolTableQuery);
            System.out.println("Таблица создана");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertData(List<School> schools) {
        String insertDataQuery = "INSERT INTO School (id, district, name, county, grades, students, teachers, calworks, lunch, computer, expenditure, income, english, read, math) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertDataQuery);
            for (School sc : schools) {
                preparedStatement.setInt(1, sc.getId());
                preparedStatement.setString(2, sc.getDistrict());
                preparedStatement.setString(3, sc.getName());
                preparedStatement.setString(4, sc.getCounty());
                preparedStatement.setString(5, sc.getGrades());
                preparedStatement.setInt(6, sc.getStudents());
                preparedStatement.setDouble(7, sc.getTeachers());
                preparedStatement.setDouble(8, sc.getCalworks());
                preparedStatement.setDouble(9, sc.getLunch());
                preparedStatement.setInt(10, sc.getComputer());
                preparedStatement.setDouble(11, sc.getExpenditure());
                preparedStatement.setDouble(12, sc.getIncome());
                preparedStatement.setDouble(13, sc.getEnglish());
                preparedStatement.setDouble(14, sc.getRead());
                preparedStatement.setDouble(15, sc.getMath());
                preparedStatement.execute();
            }
            System.out.println("Поля добавлены");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public void printResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Вывод заголовков столбцов
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnName(i) + "\t");
        }
        System.out.println();

        // Вывод данных строк
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(resultSet.getString(i) + "\t");
            }
            System.out.println();
        }
    }

    public double getAverageStudents(String county) throws SQLException {
        String query = "SELECT AVG(students) FROM School WHERE county = '" + county + "'";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        double averageStudents = 0.0;
        if (resultSet.next()) {
            averageStudents = resultSet.getDouble(1);
        }

        resultSet.close();
        statement.close();

        return averageStudents;
    }

    public String getQueryOfAvgExpend() {
        return "SELECT county AS 'Страна', AVG(expenditure) AS 'Ср. кол-во расходов'  FROM School WHERE county == 'Fresno' AND expenditure > 10\n" +
                "UNION\n" +
                "SELECT county AS 'Страна', AVG(expenditure) AS 'Ср. кол-во расходов'  FROM School WHERE county == 'Contra Costa' AND expenditure > 10\n" +
                "UNION\n" +
                "SELECT county AS 'Страна', AVG(expenditure) AS 'Ср. кол-во расходов'  FROM School WHERE county == 'El Dorado' AND expenditure > 10\n" +
                "UNION\n" +
                "SELECT county AS 'Страна', AVG(expenditure) AS 'Ср. кол-во расходов'  FROM School WHERE county == 'Glenn' AND expenditure > 10";
    }

    public String getQueryOfTopMathBetween() {
        return "SELECT * FROM School WHERE students BETWEEN 5000 AND 7500 OR students BETWEEN 10000 AND 11000\n" +
                "ORDER BY math DESC\n" +
                "LIMIT 1";
    }
}
