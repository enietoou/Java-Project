import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.HashMap;
import java.util.Map;

import java.sql.SQLException;

public class StudentGraph {
    public static void main() throws SQLException {
        // Создаем набор данных для графика
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Заполняем данные о среднем количестве студентов в различных странах
        Map<String, Double> averageStudentsByCounty = getAverageStudentsByCounty();
        for (String county : averageStudentsByCounty.keySet()) {
            double averageStudents = averageStudentsByCounty.get(county);
            dataset.addValue(averageStudents, "Студенты", county);
        }

        // Создаем график
        JFreeChart chart = ChartFactory.createBarChart(
                "Среднее количество студентов в различных странах", // Заголовок графика
                "Страна", // Название оси X
                "Среднее количество студентов", // Название оси Y
                dataset // Набор данных для графика
        );

        // Отображаем график в отдельном окне
        ChartFrame frame = new ChartFrame("График студентов", chart);
        frame.pack();
        frame.setVisible(true);
    }

    private static Map<String, Double> getAverageStudentsByCounty() throws SQLException {
        SqlLiteDB db = new SqlLiteDB();
        db.connect();
        Map<String, Double> averageStudentsByCountry = new HashMap<>();
        averageStudentsByCountry.put("Los Angeles", db.getAverageStudents("Los Angeles"));
        averageStudentsByCountry.put("Kings", db.getAverageStudents("Kings"));
        averageStudentsByCountry.put("Merced", db.getAverageStudents("Merced"));
        averageStudentsByCountry.put("Fresno", db.getAverageStudents("Fresno"));
        averageStudentsByCountry.put("Tulare", db.getAverageStudents("Tulare"));
        averageStudentsByCountry.put("Imperial", db.getAverageStudents("Imperial"));
        averageStudentsByCountry.put("Mendocino", db.getAverageStudents("Mendocino"));
        averageStudentsByCountry.put("Orange", db.getAverageStudents("Orange"));
        averageStudentsByCountry.put("San Mateo", db.getAverageStudents("San Mateo"));
        averageStudentsByCountry.put("Humboldt", db.getAverageStudents("Humboldt"));
        db.disconnect();
        return averageStudentsByCountry;
    }
}