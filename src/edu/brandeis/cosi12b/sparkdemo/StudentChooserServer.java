package edu.brandeis.cosi12b.sparkdemo;
import static spark.Spark.*;

import java.util.Random;

import j2html.tags.Tag;
import spark.Request;
import spark.Response;

import static j2html.TagCreator.*;

public class StudentChooserServer {
  private StudentDirectory studentDir;

  public StudentChooserServer(StudentDirectory sd) {
    this.studentDir = sd;
  }

  public void launch() {
    get("/choose/", (res, req) -> welcomePage());
    get("/chosen/", (res, req) -> winnerPage(res, req));
  }

  private String winnerPage(Request res, Response req) {
    if (!studentDir.goodFile) {
      return body().with(h1("Student file was not found...")).render();
    }
    Random r = new Random();
    int studNum = r.nextInt(studentDir.count());
    String name = studentDir.get(studNum);
    return body().with(h1("You are chosen!"),
                p("Based on a random selection, the COMPUTER has selected the following student "+
                  "to be called on!"), 
                h4(name),
                form().withMethod("get").withAction("/chosen/").with(submitButton("Choose Me!!"))).render();
  }

  private String welcomePage() {
    return body().with(
        h1("Welcome!"),
        img().withSrc("https://www.brandeis.edu/communications/creative/downloads/4-inch-seal-pms-294.jpg"),

        p("Welcome to the Student Chooser Application. It is used in the classroom to fairly choose students to " +
        "call on. We don't want to always be calling thesa person, do we?"),
        p("By the way, this is a demo program to show how easy it is to create a simple web application. " + 
          "In this case we are using a simple server called Java Spark. This approach is good for a simple example, " +
            "but it wouldn't really make sense for a more complicated application!"),
        form().withMethod("get").withAction("/chosen/").with(submitButton("Choose Me!!"))).render();
  }

  public static Tag submitButton(String text) {
    return button().withType("submit").withText(text);
  }
}
