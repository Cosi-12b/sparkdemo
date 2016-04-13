package edu.brandeis.cosi12b.sparkdemo;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import spark.Spark;

public class StudentDirectory {
  ArrayList<StudentInfo> students = new ArrayList<>();

  public ArrayList<StudentInfo> readStudentInfoFile() {
    Reader in;
    Path currentRelativePath = Paths.get("");
    String s = currentRelativePath.toAbsolutePath().toString();
  //  Spark.staticFileLocation("");
    System.out.println("Current relative path is: " + s);
    try {
      in = new FileReader("target/classes/main/resources/studentnames.csv");
      Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
      for (CSVRecord r : records) {
        students.add(new StudentInfo(r.get("First name"), r.get("Surname"), r.get("Email address")));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public int count() {
    System.out.println(students.size());
    return students.size();
  }

  public String get(int index) {
    StudentInfo aStudent = students.get(index);
    return aStudent.fullName();
  }


}
