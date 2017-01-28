package comp110.controls;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Alert extends BorderPane {

	@FXML
	private Label _prompt;

	@FXML
	private Button _send;

	private ParsedFutureValue<Void> _future;

	public Alert(String prompt, ParsedFutureValue<Void> future) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
			_prompt.setText(prompt);
			_send.setDefaultButton(true);
			_send.setOnAction(this::handleInput);
			_future = future;
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void handleClose() {
		_future.exit();
	}

	public void handleInput(ActionEvent e) {
		_future.set("");
		_send.setDisable(true);
	}

}