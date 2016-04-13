package edu.brandeis.cosi12b.sparkdemo;
import static spark.Spark.*;

// https://sparktutorials.github.io/2015/08/24/spark-heroku.html
// https://sparktutorials.github.io/2015/06/01/spark-freemarker.html
// https://commons.apache.org/proper/commons-csv/

public class Main {

  public static void main(String[] args) {
    port(getHerokuAssignedPort());
    StudentDirectory sd = new StudentDirectory();
    sd.readStudentInfoFile();
    StudentChooserServer sc = new StudentChooserServer(sd);
    sc.launch();
  }

  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
      return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567; // return default port if heroku-port isn't set (i.e. on
                 // localhost)
  }

}
