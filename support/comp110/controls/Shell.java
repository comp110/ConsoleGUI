package comp110.controls;

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

  public void print(Object o) {
    if (o == this) {
      addNode(new Output("I'm printing myself. That makes me self-aware. Strange.", "Console"));
    } else {
      String fqcn = o.getClass().getTypeName();
      System.out.println(fqcn);
      int lastDot = fqcn.lastIndexOf('.');
      addNode(new Output(o.toString(), fqcn.substring(lastDot + 1)));
    }
  }

  public void promptInt(String s, ParsedFutureValue<Integer> future) {
    Prompt<Integer> p = new Prompt<>(s, "int", future);
    addNode(p);
  }

  public void promptDouble(String s, ParsedFutureValue<Double> future) {
    Prompt<Double> p = new Prompt<>(s, "double", future);
    addNode(p);
  }

  public void promptBoolean(String s, ParsedFutureValue<Boolean> future) {
    Prompt<Boolean> p = new Prompt<>(s, "boolean", future);
    addNode(p);
  }

  public void promptChar(String s, ParsedFutureValue<Character> future) {
    Prompt<Character> p = new Prompt<>(s, "char", future);
    addNode(p);
  }

  public void promptString(String s, ParsedFutureValue<String> future) {
    Prompt<String> p = new Prompt<>(s, "String", future);
    addNode(p);
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