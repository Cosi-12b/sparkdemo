package edu.brandeis.cosi12b.sparkdemo;

public class StudentInfo {
  private String firstName;
  private String lastName;
  private String email;
  
  StudentInfo(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }
  
  String fullName() {
    return firstName + " " + lastName;
  }
}
