package comp110.controls;

import java.io.File;

import javax.imageio.ImageIO;

import comp110.Controller;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Transform;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FXCanvas implements Controller {

	private static final double MAX_HEIGHT = 512.0;
	private static final double MAX_WIDTH = 512.0;

	@FXML
	private Pane _canvas;

	@FXML
	private Button _saveButton;

	@FXML
	private Rectangle _background;

	private Group _shapes;
	private Group _grid;

	private Color _gridColor;

	public void initialize() {
		_gridColor = Color.WHITE;

		_shapes = new Group();

		_grid = new Group();
		_grid.setOpacity(0.5);
		_canvas.getChildren().add(_grid);

		_canvas.getChildren().add(_shapes);
	}

	@Override
	public void handleClose() {

	}

	public void save() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Photo File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png"));
		File file = fileChooser.showSaveDialog(null);
		if (file != null) {
			try {
				SnapshotParameters sp = new SnapshotParameters();
				sp.setFill(javafx.scene.paint.Color.TRANSPARENT);
				this.drawGrid(MAX_WIDTH, MAX_HEIGHT);
				_canvas.layout();
				sp.setViewport(new Rectangle2D(0, 0, MAX_WIDTH, MAX_HEIGHT));
				this.drawGrid(_canvas.getPrefWidth(), _canvas.getPrefHeight());
				javafx.scene.image.Image image = _shapes.snapshot(sp, null);
				ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
			} catch (Exception e) {
				System.out.println("Error saving file to disk.");
			}
		}
	}

	public void clear() {
		_shapes.getChildren().clear();
	}

	public void draw(Node n) {
		_shapes.getChildren().add(n);
		this.drawGrid(_canvas.getPrefWidth(), _canvas.getPrefHeight());
	}

	public void drawGrid(double width, double height) {
		Bounds shapes = _shapes.getLayoutBounds();
		double canvasHeight = height - 32.0;
		double canvasWidth = width - 32.0;

		double heightConstraint = canvasHeight / shapes.getHeight();
		double widthConstraint = canvasWidth / shapes.getWidth();

		double scale = heightConstraint < widthConstraint ? heightConstraint : widthConstraint;

		double originalHeight = shapes.getHeight();
		double originalWidth = shapes.getWidth();
		double scaledHeight = originalHeight * scale;
		double scaledWidth = originalWidth * scale;

		_shapes.setScaleX(scale);
		_shapes.setScaleY(scale);
		_shapes.setTranslateX(-1.0 * shapes.getMinX() - originalWidth / 2.0 + scaledWidth / 2.0);
		_shapes.setTranslateY(-1.0 * shapes.getMinY() - originalHeight / 2.0 + scaledHeight / 2.0);
		_shapes.setTranslateY(_shapes.getTranslateY() + (canvasHeight - scaledHeight) / 2.0 + 16.0);
		_shapes.setTranslateX(_shapes.getTranslateX() + (canvasWidth - scaledWidth) / 2.0 + 16.0);

		try {
			_grid.getChildren().clear();
			Transform toCanvas = _shapes.getLocalToParentTransform();

			double start, end, margin;
			start = shapes.getMinX();
			end = shapes.getMaxX();
			margin = (end - start) / 4.0;
			for (int i = 0; i < 5; i++) {
				addVerticalGridLine(toCanvas, start + i * margin, shapes.getMaxY());
			}

			start = shapes.getMinY();
			end = shapes.getMaxY();
			margin = (end - start) / 4.0;
			for (int i = 0; i < 5; i++) {
				addHorizontalGridLine(toCanvas, start + i * margin, shapes.getMaxX());
			}

		} catch (Exception e) {

		}
	}

	private void addVerticalGridLine(Transform toCanvas, double x, double maxY) {
		Point2D point = new Point2D(x, maxY);
		Point2D pointCanvas = toCanvas.transform(point);
		Label label = new Label(String.format("%.0f", x));
		label.setAlignment(Pos.TOP_CENTER);
		label.setTextFill(_gridColor);
		label.setMinWidth(64.0);
		label.setLayoutX(pointCanvas.getX() - (label.getMinWidth() / 2.0));
		label.setLayoutY(-label.getFont().getSize());
		Line line = new Line(pointCanvas.getX(), 8.0, pointCanvas.getX(), pointCanvas.getY());
		line.setStroke(_gridColor);
		line.setStrokeWidth(1.0);
		_grid.getChildren().add(label);
		_grid.getChildren().add(line);
	}

	private void addHorizontalGridLine(Transform toCanvas, double y, double maxX) {
		Point2D point = new Point2D(maxX, y);
		Point2D pointCanvas = toCanvas.transform(point);
		Label label = new Label(String.format("%.0f", y));
		label.setAlignment(Pos.BOTTOM_RIGHT);
		label.setTextFill(_gridColor);
		label.setMinHeight(label.getFont().getSize());
		label.setMinWidth(32.0);
		label.setLayoutX(-28.0);
		label.setLayoutY(pointCanvas.getY() - label.getFont().getSize() / 2.0 - 4.0);
		Line line = new Line(8.0, pointCanvas.getY(), pointCanvas.getX(), pointCanvas.getY());
		line.setStroke(_gridColor);
		line.setStrokeWidth(1.0);
		_grid.getChildren().add(label);
		_grid.getChildren().add(line);
	}

	public void setGridColor(Color c) {
		_gridColor = c;
		this.drawGrid(_canvas.getPrefWidth(), _canvas.getPrefHeight());
	}

	public void setBackground(Color fill) {
		_background.setFill(fill);
	}

}