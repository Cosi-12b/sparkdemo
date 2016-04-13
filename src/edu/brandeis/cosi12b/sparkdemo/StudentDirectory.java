package edu.brandeis.cosi12b.sparkdemo;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class StudentDirectory {
  ArrayList<StudentInfo> students = new ArrayList<>();

  public ArrayList<StudentInfo> readStudentInfoFile() {
    Reader in;
    try {
      in = new FileReader("studentnames.csv");
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
