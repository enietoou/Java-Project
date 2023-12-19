import java.nio.file.Paths;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.nio.file.Path;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws SQLException {
        SqlLiteDB db = new SqlLiteDB();
        db.connect();
        ResultSet resset = db.executeQuery(db.getQueryOfTopMathBetween());
        db.printResultSet(resset);

        //Вывод среднего количества расходов(expenditure) в Fresno, Contra Costa,
        //El Dorado и Glenn, у которых расход больше 10:
//        ResultSet resset = db.executeQuery(db.getQueryOfAvgExpend());
//        db.printResultSet(resset);
        //запуск построения графика по среднему количеству студентов в различных странах
//        StudentGraph.main();

//        String query = "SELECT AVG(students) FROM School WHERE county == \"Butte\"";
//        ResultSet resset = db.executeQuery(query);

//        db.printResultSet(resset);
//        Path filepath = Paths.get("src/Школы.csv");
//        List<School> ls = School.parseCSV(filepath);
//        db.createSchoolTable();
//        System.out.println(ls);
//        db.insertData(ls);
        db.disconnect();
    }
}