package comp110;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CanvasDemo {

	public static void main(String[] args) {

		double[] values = new double[20];
		for (int i = 0; i < values.length; i++) {
			values[i] = Math.random() * 100.0;
		}

		SortedDoubleArray sda = new SortedDoubleArray(values);
		sda.sort();

	}

}
