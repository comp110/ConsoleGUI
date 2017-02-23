package comp110;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CanvasSaveTest {

	public static void main(String[] args) {

		Canvas c = new Canvas();
		c.draw(new Circle(0, 0, 10.0, Color.GREEN));
		c.draw(new Circle(0, 20.0, 10.0, Color.YELLOW));
		c.draw(new Circle(0, 40.0, 10.0, Color.YELLOW));

	}

}
