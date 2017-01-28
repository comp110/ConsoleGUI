package comp110.controls;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Confirm extends BorderPane {

	@FXML
	private Label _prompt;

	@FXML
	private Button _yes;

	@FXML
	private Button _no;

	private ParsedFutureValue<Boolean> _future;

	public Confirm(String message, ParsedFutureValue<Boolean> future) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Confirm.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
			_prompt.setText(message);
			_yes.setOnAction(this::handleYes);
			_no.setOnAction(this::handleNo);
			_future = future;
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void handleClose() {
		_future.exit();
	}

	public void handleYes(ActionEvent e) {
		_future.set("true");
		disableButtons();
	}

	public void handleNo(ActionEvent e) {
		_future.set("false");
		disableButtons();
	}

	private void disableButtons() {
		_yes.setDisable(true);
		_no.setDisable(true);
	}

}