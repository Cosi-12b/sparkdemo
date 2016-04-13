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
    get("/", (res, req) -> welcomePage());
    post("/", (res, req) -> winnerPage(res, req));
  }

  private String winnerPage(Request res, Response req) {
    Random r = new Random();
    int studNum = r.nextInt(studentDir.count());
    System.out.println(studNum);
    String name = studentDir.get(studNum);
    return body().with(h1("You Are Chosen!"), h1(name)).render();
  }

  private String welcomePage() {
    return body().with(
        h1("Welcome!"),
        h2("Are you ready to be called on?"),
        p("This page is displayed using Java Spark. The idea is that whenver I want to call " +
            "on someone at random, I will just click that big button below"),
        form().withMethod("post").with(submitButton("Choose Me!!"))).render();
  }

  public static Tag submitButton(String text) {
    return button().withType("submit").withText(text);
  }
}
