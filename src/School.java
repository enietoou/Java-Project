import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Пример данных
//2,
//"61499",
//"Manzanita Elementary",
//"Butte",
//"KK-08",
//240,
//11.1499996185303,
//15.4167003631592,
//47.9166984558105,
//101,5099.380859375,
//9.82400035858154,
//4.58333349227905,
//660.5,
//661.900024414062


public class School {
    private int id;
    private String district;
    private String name;
    private String county;
    private String grades;
    private int students;
    private double teachers;
    private double calworks;
    private double lunch;
    private int computer;
    private double expenditure;
    private double income;
    private double english;
    private double read;
    private double math;



    public School(int id, String district, String name, String county, String grades, int students, double teachers,
                  double calworks, double lunch, int computer, double expenditure, double income, double english,
                  double read, double math) {
        this.id = id;
        this.district = district;
        this.name = name;
        this.county = county;
        this.grades = grades;
        this.students = students;
        this.teachers = teachers;
        this.calworks = calworks;
        this.lunch = lunch;
        this.computer = computer;
        this.expenditure = expenditure;
        this.income = income;
        this.english = english;
        this.read = read;
        this.math = math;
    }




    public static List<School> parseCSV(Path path) {
        try (Stream<String> csvLines = Files.lines(path)) {
            return csvLines.skip(1)
                    .map(School::parseLine)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    private static School parseLine(String line) {
        String[] values = line.split(",");

        int id = Integer.parseInt(values[0].replaceAll("\"", ""));
        String district = values[1].replaceAll("\"", "");
        String name = values[2].replaceAll("\"", "");
        String county = values[3].replaceAll("\"", "");
        String grades = values[4].replaceAll("\"", "");
        int students = Integer.parseInt(values[5].replaceAll("\"", ""));
        double teachers = Double.parseDouble(values[6].replaceAll("\"", ""));
        double calworks = Double.parseDouble(values[7].replaceAll("\"", ""));
        double lunch = Double.parseDouble(values[8].replaceAll("\"", ""));
        int computer = Integer.parseInt(values[9].replaceAll("\"", ""));
        double expenditure = Double.parseDouble(values[10].replaceAll("\"", ""));
        double income = Double.parseDouble(values[11].replaceAll("\"", ""));
        double english = Double.parseDouble(values[12].replaceAll("\"", ""));
        double read = Double.parseDouble(values[13].replaceAll("\"", ""));
        double math = Double.parseDouble(values[14].replaceAll("\"", ""));

        return new School(id, district, name, county, grades, students, teachers, calworks, lunch, computer,
                expenditure, income, english, read, math);
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }



    public String getGrades() {
        return grades;
    }
    public void setGrades(String grades) {
        this.grades = grades;
    }



    public int getStudents() {
        return students;
    }
    public void setStudents(int students) {
        this.students = students;
    }



    public double getTeachers() {
        return teachers;
    }
    public void setTeachers(double teachers) {
        this.teachers = teachers;
    }



    public double getCalworks() {
        return calworks;
    }
    public void setCalworks(double calworks) {
        this.calworks = calworks;
    }



    public double getLunch() {
        return lunch;
    }
    public void setLunch(double lunch) {
        this.lunch = lunch;
    }



    public int getComputer() {
        return computer;
    }
    public void setComputer(int computer) {
        this.computer = computer;
    }



    public double getExpenditure() {
        return expenditure;
    }
    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }



    public double getIncome() {
        return income;
    }
    public void setIncome(double income) {
        this.income = income;
    }



    public double getEnglish() {
        return english;
    }
    public void setEnglish(double english) {
        this.english = english;
    }



    public double getRead() {
        return read;
    }
    public void setRead(double read) {
        this.read = read;
    }



    public double getMath() {
        return math;
    }
    public void setMath(double math) {
        this.math = math;
    }



    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", name='" + name + '\'' +
                ", county='" + county + '\'' +
                ", grades='" + grades + '\'' +
                ", students=" + students +
                ", teachers=" + teachers +
                ", calworks=" + calworks +
                ", lunch=" + lunch +
                ", computer=" + computer +
                ", expenditure=" + expenditure +
                ", income=" + income +
                ", english=" + english +
                ", read=" + read +
                ", math=" + math +
                '}';
    }
}