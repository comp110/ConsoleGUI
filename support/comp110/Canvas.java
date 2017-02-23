package comp110;

import comp110.controls.FXCanvas;
import comp110.controls.Shell;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class Canvas {

	private boolean _showing = false;
	private FXCanvas _impl;
	private String _title;
	private double _speed = 1.0;

	public Canvas() {
		this("COMP110 Canvas");
	}

	public Canvas(String title) {
		_title = title;
		_speed = 1.0;
	}

	public void speed(double speed) {
		_speed = speed;
	}

	public void draw(Node n) {
		run(() -> {
			_impl.draw(n);
		});
	}

	public void clear() {
		run(() -> {
			_impl.clear();
		}, false);
	}

	public void setBackground(Color c) {
		run(() -> {
			_impl.setBackground(c);
		});
	}

	public void setGridColor(Color c) {
		run(() -> {
			_impl.setGridColor(c);
		});
	}

	private void run(VoidMethod m) {
		run(m, true);
	}

	private void run(VoidMethod m, boolean pause) {
		if (!_showing) {
			_impl = AppBase.instance().loadWindow(_title, FXCanvas.class, "FXCanvas.fxml");
			_showing = true;
		}

		AppBase.instance().runFX(() -> {
			m.run();
			return null;
		});

		if (pause && _speed <= 1.0 && _speed > 0.0) {
			try {
				Thread.sleep((long) (1000L - (1000.0 * _speed)));
			} catch (InterruptedException e) {
			}
		}
	}

}