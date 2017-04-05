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
  boolean goodFile;

  public void readStudentInfoFile() {
    goodFile = true;
    Reader in;
    Path currentRelativePath = Paths.get("");
    String s = currentRelativePath.toAbsolutePath().toString();
    System.out.println("Current path is: " + s);
    try {
      in = new FileReader("/src/main/studentnames.csv");
      Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
      for (CSVRecord r : records) {
        students.add(new StudentInfo(r.get("First name"), r.get("Surname"), r.get("Email address")));
      }
    } catch (Exception e) {
      goodFile = false;
    }
  }

  public int count() {
    return students.size();
  }

  public String get(int index) {
    StudentInfo aStudent = students.get(index);
    return aStudent.fullName();
  }


}
