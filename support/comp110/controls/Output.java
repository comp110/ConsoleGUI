package comp110.controls;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Output extends BorderPane {

  @FXML
  private Label _value;

  @FXML
  private Label _type;

  public Output(String value, String type) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Output.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    try {
      loader.load();
      BorderPane bp = loader.getRoot();
      _value.setText(value);
      _type.setText(type);
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

}