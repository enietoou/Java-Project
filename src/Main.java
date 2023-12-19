import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.nio.file.Path;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws SQLException {
        SqlLiteDB db = new SqlLiteDB();
        db.connect();
//        operationsWithDatabase(db);
//        buildChart();
//        outAvgExpenditure(db);
//        outTopMathBetween(db);
        db.disconnect();
    }


    public static void operationsWithDatabase(SqlLiteDB datab) throws SQLException {
        Path filepath = Paths.get("src/Школы.csv");
        List<School> ls = School.parseCSV(filepath);
        datab.createSchoolTable();
        datab.insertData(ls);
    }


    public static void buildChart() throws SQLException {
        StudentGraph.main();
    }


    public static void outAvgExpenditure(SqlLiteDB datab) throws SQLException {
        ResultSet resset = datab.executeQuery(datab.getQueryOfAvgExpend());
        datab.printResultSet(resset);
    }


    public static void outTopMathBetween(SqlLiteDB datab) throws SQLException {
        ResultSet resset = datab.executeQuery(datab.getQueryOfTopMathBetween());
        datab.printResultSet(resset);
    }
}