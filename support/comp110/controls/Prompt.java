package comp110.controls;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Prompt extends BorderPane {

  @FXML
  private Label _prompt;

  @FXML
  private TextField _input;

  @FXML
  private Button _send;

  @FXML
  private Label _type;

  private CountDownLatch _latch;

  public Prompt(String prompt, String type, CountDownLatch l) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Prompt.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    try {
      loader.load();
      BorderPane bp = loader.getRoot();
      _prompt.setText(prompt);
      _type.setText(type);
      _input.setOnAction(this::handleInput);
      _send.setOnAction(this::handleInput);
      _latch = l;
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public String getInput() {
    return _input.getText();
  }

  public void handleInput(ActionEvent e) {
    _input.setDisable(true);
    _send.setDisable(true);
    _latch.countDown();
  }

}