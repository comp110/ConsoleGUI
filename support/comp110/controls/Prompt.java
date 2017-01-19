package comp110.controls;

import java.io.IOException;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class Prompt<T> extends BorderPane {

  @FXML
  private Label _prompt;

  @FXML
  private TextField _input;

  @FXML
  private Button _send;

  @FXML
  private Label _type;

  private ParsedFutureValue<T> _future;

  private static final String _inputStyle = "-fx-background-color: #222; -fx-border-radius: 8; -fx-text-fill: white;";
  private static final String _buttonStyle = "-fx-background-color: #222; -fx-text-fill: white;";

  public Prompt(String prompt, String type, ParsedFutureValue<T> future) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Prompt.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    try {
      loader.load();
      _prompt.setText(prompt);
      _type.setText(type);
      _input.setOnAction(this::handleInput);
      _input.textProperty().addListener(this::handleChange);
      _send.setOnAction(this::handleInput);
      _future = future;
      setBorderHint("#FFF");
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public String getInput() {
    return _input.getText();
  }

  public void handleChange(ObservableValue<? extends String> value, String oldValue, String newValue) {
    if (_future.test(_input.getText())) {
      setBorderHint("#0F0");
    } else {
      setBorderHint("#F00");
    }
  }

  public void handleInput(ActionEvent e) {
    if (_future.set(_input.getText())) {
      setBorderHint("#FFF");
      _input.setDisable(true);
      _send.setDisable(true);
    }
  }

  private void setBorderHint(String color) {
    _input.setStyle(_inputStyle + " -fx-border-color: " + color + ";");
    _send.setStyle(_buttonStyle + " -fx-border-color: " + color + ";");
  }

}