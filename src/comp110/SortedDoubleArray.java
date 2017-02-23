package comp110;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SortedDoubleArray {

	private Canvas _canvas;
	private double[] _values;

	public SortedDoubleArray(double[] values) {
		_values = values;

		_canvas = new Canvas();
		_canvas.speed(0.75);
	}

	public void sort() {
		int start = 0;

		while (start < _values.length) {
			int selected = start;
			this.draw(selected);

			while (this.shouldMoveBack(selected)) {
				this.moveBack(selected);
				selected--;
				this.draw(selected);
			}
			start++;
		}

	}

	public boolean shouldMoveBack(int index) {
		return index > 0 && _values[index] < _values[index - 1];
	}

	public void moveBack(int index) {
		double temp = _values[index];
		_values[index] = _values[index - 1];
		_values[index - 1] = temp;
	}

	public void draw(int selected) {
		_canvas.clear();
		Group g = new Group();
		for (int i = 0; i < _values.length; i++) {
			Rectangle r = new Rectangle(10, _values[i]);
			if (i == selected) {
				r.setFill(Color.GREEN);
			} else {
				r.setFill(new Color(0.0, 0.0, _values[i] / 100.0, 1.0));
			}
			r.setX(i * 15.0);
			r.setY(100.0 - _values[i]);
			g.getChildren().add(r);
		}
		_canvas.draw(g);
	}

}
