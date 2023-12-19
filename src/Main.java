import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws SQLException {
        SqlLiteDB db = new SqlLiteDB();
        db.connect();
        Path filepath = Paths.get("src/Школы.csv");
        List<School> ls = School.parseCSV(filepath);
        db.createSchoolTable();
        System.out.println(ls);
        db.insertData(ls);
        db.disconnect();
    }
}