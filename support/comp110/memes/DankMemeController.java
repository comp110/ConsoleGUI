package comp110.memes;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;

import comp110.AppBase;
import comp110.Controller;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class DankMemeController implements Observer, Controller {

	/*
	 * These static methods are what kick-off the stage and hold reference to the
	 * instance we'll need once the JavaFX framework actually constructs the
	 * instance of the controller when loading FXML.
	 */
	private static MemePhoto _staticMeme;

	// This flag is used for testing. Headless means "without any UI".
	private static boolean _headless = false;

	public static void setHeadless(boolean headless) {
		_headless = headless;
	}

	public static void show(MemePhoto meme) {
		_staticMeme = meme;

		if (!_headless) {
			AppBase.instance().loadWindow("Meme", DankMemeController.class, "DankMemeUI.fxml");
		}
	}

	public static MemePhoto getMeme() {
		return _staticMeme;
	}

	/*
	 * All of the following become relevant once JFX has instantiated a
	 * controller. From here on we are *mostly* in a sane world with the exception
	 * of updates from the MemePhoto originating outside of the JFX thread.
	 */

	// Here we'll maintain a reference to the MemePhoto the student is interacting
	// with in the main method.
	private MemePhoto _meme;

	@FXML
	private Button _saveButton;

	@FXML
	private ImageView _imageView;

	/*
	 * JavaFX will automatically call this 0-parameter constructor when loading
	 * the FXML.
	 */
	public DankMemeController() {
		_meme = _staticMeme;
		_meme.addObserver(this);
	}

	public void initialize() {
	}

	public void saveMeme() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Photo File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png"));
		File file = fileChooser.showSaveDialog(null);
		if (file != null) {
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(getMemedImage(), null), "png", file);
			} catch (Exception e) {
				System.out.println("Error saving file to disk.");
			}
		}
	}

	public void rerender() {
		_imageView.setImage(getMemedImage());

		Image img = _imageView.getImage();
		if (img != null) {
			double w = 0;
			double h = 0;

			double ratioX = _imageView.getFitWidth() / img.getWidth();
			double ratioY = _imageView.getFitHeight() / img.getHeight();

			double reducCoeff = 0;
			if (ratioX >= ratioY) {
				reducCoeff = ratioY;
			} else {
				reducCoeff = ratioX;
			}

			w = img.getWidth() * reducCoeff;
			h = img.getHeight() * reducCoeff;

			_imageView.setX((_imageView.getFitWidth() - w) / 2);
			_imageView.setY((_imageView.getFitHeight() - h) / 2);
		}
	}

	private String _cacheUrl = "";
	private ImageView _cacheImageView;

	public Image getMemedImage() {
		StackPane pane = new StackPane();

		String url;
		if (_meme.getImageUrl().equals("")) {
			url = DankMemeController.class.getResource("square.png").toString();
		} else {
			url = _meme.getImageUrl();
		}

		ImageView imageView;

		if (_cacheUrl.equals(url)) {
			imageView = _cacheImageView;
		} else {
			_cacheUrl = url;
			imageView = _cacheImageView = new ImageView(url);
		}

		double width = imageView.getImage().getWidth();
		double height = imageView.getImage().getHeight();

		Group topText = getMemeText(_meme.getTopText(), width, height);
		Group bottomText = getMemeText(_meme.getBottomText(), width, height);

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(topText);
		borderPane.setBottom(bottomText);
		borderPane.setMinHeight(imageView.getImage().getHeight());
		borderPane.setMinWidth(imageView.getImage().getWidth());
		borderPane.setStyle("-fx-background-color: #000");

		pane.getChildren().addAll(imageView, borderPane);

		SnapshotParameters parameters = new SnapshotParameters();
		parameters.setFill(Paint.valueOf("#000"));
		WritableImage image = pane.snapshot(parameters, null);
		return image;
	}

	protected Group getMemeText(String string, double fitWidth, double height) {
		Group g = new Group();

		double minDimension = fitWidth < height ? fitWidth : height;

		double fontSize = minDimension / 5.0;
		double strokeWidth = fontSize / 20;

		Text text = new Text(string.toUpperCase());
		text.setFont(Font.font("Impact", FontWeight.BLACK, fontSize));
		text.setFill(Color.WHITE);
		text.setStroke(Color.BLACK);
		text.setStrokeWidth(strokeWidth);
		text.setStrokeLineJoin(StrokeLineJoin.ROUND);
		text.setFontSmoothingType(FontSmoothingType.LCD);
		text.setTextAlignment(TextAlignment.CENTER);

		text.setWrappingWidth(fitWidth * 1.25);

		double ratio = (fitWidth) / text.getLayoutBounds().getWidth();
		if (ratio < 1.0) {
			text.setScaleX(ratio);
			text.setScaleY(ratio);
		}

		g.getChildren().add(text);

		return g;
	}

	/*
	 * When our MemePhoto updates this method is called. It is *not* on the JFX
	 * thread so we immediately just run it on the JFX thread.
	 */
	public void update(Observable arg0, Object arg1) {
		if (!_headless) {
			AppBase.instance().runFX(() -> {
				this.rerender();
				return null;
			});
		}
	}

	@Override
	public void handleClose() {

	}

}