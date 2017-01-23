package comp110.controls;

import comp110.Controller;
import comp110.Theme;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class Shell implements Controller {

	@FXML
	private VBox _output;

	@FXML
	private ScrollPane _scroll;

	private String[] _theme;
	
	public void initialize() {
		_output.heightProperty().addListener(this::handleAutoScroll);
		this.setTheme(Theme.COMP110); //Default them is set when shell is initialized
	}

	public void handleClose() {
		Node last = _output.getChildren().get(_output.getChildren().size() - 1);
		if (last instanceof Prompt) {
			((Prompt<?>) last).handleClose();
		}
	}

	public void print() {
		Output output = new Output("", "");
		themeOutput(output);
		addNode(output);
	}

	public void print(String s) {
		Output output = new Output(s, "String");
		themeOutput(output);
		addNode(output);
	}

	public void print(int i) {
		Output output = new Output("" + i, "int");
		themeOutput(output);
		addNode(output);
	}

	public void print(double d) {
		Output output = new Output("" + d, "double");
		themeOutput(output);
		addNode(output);
	}

	public void print(boolean b) {
		Output output = new Output("" + b, "boolean");
		themeOutput(output);
		addNode(output);
	}

	public void print(char c) {
		Output output = new Output("" + c, "char");
		themeOutput(output);
		addNode(output);
	}

	public void print(Object o) {
		if (o == this) {
			Output output = new Output("I'm printing myself. That makes me self-aware. Strange.", "Console");
			themeOutput(output);
			addNode(output);
		} else {
			String fqcn = o.getClass().getTypeName();
			System.out.println(fqcn);
			int lastDot = fqcn.lastIndexOf('.');
			Output output = new Output(o.toString(), fqcn.substring(lastDot + 1));
			themeOutput(output);
			addNode(output);
		}
	}

	public void promptInt(String s, ParsedFutureValue<Integer> future) {
		Prompt<Integer> p = new Prompt<>(s, "int", future);
		themePrompt(p);
		addNode(p);
	}

	public void promptDouble(String s, ParsedFutureValue<Double> future) {
		Prompt<Double> p = new Prompt<>(s, "double", future);
		themePrompt(p);
		addNode(p);
	}

	public void promptBoolean(String s, ParsedFutureValue<Boolean> future) {
		Prompt<Boolean> p = new Prompt<>(s, "boolean", future);
		themePrompt(p);
		addNode(p);
	}

	public void promptChar(String s, ParsedFutureValue<Character> future) {
		Prompt<Character> p = new Prompt<>(s, "char", future);
		themePrompt(p);
		addNode(p);
	}

	public void promptString(String s, ParsedFutureValue<String> future) {
		Prompt<String> p = new Prompt<>(s, "String", future);
		themePrompt(p);
		addNode(p);
	}

	//============THEMING METHODS==============//
	
	public void setTheme(String[] theme) {
		_theme = theme;
		this.setShellScrollBackgroundColor(_theme[0]);
		this.setShellOutputBackgroundColor(_theme[1]);
	}
	
	private void setShellScrollBackgroundColor(String color) {
		_scroll.setStyle("-fx-background-color: " + color + ";");
	}
  
	private void setShellOutputBackgroundColor(String color) {
		_output.setStyle("-fx-background-color: " + color + ";");
	}
  
	private void themePrompt(Prompt p) {
		p.setBorderHint(_theme[2]);
		p.setInputtingBorderHint(_theme[3]);
		p.setCorrectInputBorderHint(_theme[4]);
		p.setIncorrectInputBorderHint(_theme[5]);
		p.setBackgroundColor(_theme[6]);
		p.setTypeTextColor(_theme[7]);
		p.setInputBackgroundColor(_theme[8]);
		p.setInputTextFill(_theme[9]);
		p.setPromptTextFill(_theme[10]);
		p.setSendBackgroundColor(_theme[11]);
		p.setSendTextFill(_theme[12]);
	}
  
	private void themeOutput(Output o) {
		o.setBackgroundColor(_theme[13]);
		o.setValueTextColor(_theme[14]);
		o.setTypeTextColor(_theme[15]);
	}
	
	//==========================================//
	
	private void handleAutoScroll(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		_scroll.layout();
		_scroll.setVvalue(1.0);
	}

	private void addNode(Node n) {
		_output.getChildren().add(n);
		VBox.setMargin(n, new Insets(0, 0, 1, 0));
	}

}