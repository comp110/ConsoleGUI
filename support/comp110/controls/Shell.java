package comp110.controls;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class Shell {

  @FXML
  private VBox _output;

  @FXML
  private ScrollPane _scroll;

  public void initialize() {
    _output.heightProperty().addListener(this::handleAutoScroll);
  }

  public void print(String s) {
    addNode(new Output(s, "String"));
  }

  public void print(int i) {
    addNode(new Output("" + i, "int"));
  }

  public void print(double d) {
    addNode(new Output("" + d, "double"));
  }

  public void print(boolean b) {
    addNode(new Output("" + b, "boolean"));
  }

  public void print(char c) {
    addNode(new Output("" + c, "char"));
  }

//  public void promptString(String s, CountDownLatch cdl) {
//    Prompt p = new Prompt(s, "String", cdl);
//    addNode(p);
//  }
  
  public void promptString2(String s, FutureValue<?> future) {
    Prompt p = new Prompt(s, "String", future);
  }

  public String readString() {
    Node n = _output.getChildren().get(_output.getChildren().size() - 1);
    Prompt p = (Prompt) n;
    return p.getInput();
  }

  private void handleAutoScroll(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
    _scroll.layout();
    _scroll.setVvalue(1.0);
  }

  private void addNode(Node n) {
    _output.getChildren().add(n);
    VBox.setMargin(n, new Insets(0, 0, 1, 0));
  }

}