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
import javafx.scene.paint.Paint;

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

	//Styling instance variables
	private String _borderHintColor;
	private String _inputBackgroundColor;
	private String _inputTextFill;
	private String _sendBackgroundColor;
	private String _handleInputBorderHintColor;
	private String _handleChangeFalseFutureTestColor;
	private String _handleChangeTrueFutureTestColor;
	
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
			this._borderHintColor = "#FFF";
		    this._inputTextFill = "#FFF";
		    this._inputBackgroundColor = "#FFF";
		    this._sendBackgroundColor = "#FFF";
		    this._handleInputBorderHintColor = "#FFF";
		    this._handleChangeFalseFutureTestColor = "#FFF";
		    this._handleChangeTrueFutureTestColor = "#FFF";
		    setBorderHint("#FFF");
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void handleClose() {
		_future.exit();
	}

	public String getInput() {
		return _input.getText();
	}

	public void handleChange(ObservableValue<? extends String> value, String oldValue, String newValue) {
		if (_future.test(_input.getText())) {
			setBorderHint(this._handleChangeTrueFutureTestColor);
		} else {
			setBorderHint(this._handleChangeFalseFutureTestColor);
		}
	}

	public void handleInput(ActionEvent e) {
		if (_future.set(_input.getText())) {
			setBorderHint(this._handleInputBorderHintColor);
			_input.setDisable(true);
			_send.setDisable(true);
		}
	}

	//====================Styling methods====================//
	
	public void setBorderHint(String color) {
		this._borderHintColor = color;
	    _input.setStyle("-fx-background-color: " + this._inputBackgroundColor + "; -fx-border-color: " + color + "; -fx-border-radius: 8; -fx-text-fill: " + this._inputTextFill + ";");
	    _send.setStyle("-fx-background-color: " + this._sendBackgroundColor + "; -fx-border-color: " + color + ";");
	  }

	  public void setBackgroundColor(String color) {
		  this.setStyle("-fx-background-color: "+ color + ";");
	  }
	  
	  public void setTypeTextColor(String color) {
		  _type.setTextFill(Paint.valueOf(color));
	  }
	  
	  public void setInputBackgroundColor(String color) {
		  this._inputBackgroundColor = color;
		  _input.setStyle("-fx-background-color: " + color + "; -fx-border-color: " + this._borderHintColor + ";");
	  }
	  
	  public void setInputTextFill(String color) {
		  this._inputTextFill = color;
		  _input.setStyle("-fx-background-color: " + this._inputBackgroundColor + "; -fx-border-color: " + this._borderHintColor + "; -fx-border-radius: 8; -fx-text-fill: " + color + ";");
	  }
	  
	  public void setPromptTextFill(String color) {
		  _prompt.setTextFill(Paint.valueOf(color));
	  }
	  
	  public void setSendBackgroundColor(String color) {
		  this._sendBackgroundColor = color;
		  _send.setStyle("-fx-background-color: " + color + "; -fx-border-color: " + this._borderHintColor + ";");
	  }
	  
	  public void setSendTextFill(String color) {
		  _send.setTextFill(Paint.valueOf(color));
	  }
	  
	  public void setInputtingBorderHint(String color) {
		  this._handleInputBorderHintColor = color;
	  }
		
	  public void setCorrectInputBorderHint(String color) {
		  this._handleChangeTrueFutureTestColor = color;
	  }
		
	  public void setIncorrectInputBorderHint(String color) {
		  this._handleChangeFalseFutureTestColor = color;
	  }

}