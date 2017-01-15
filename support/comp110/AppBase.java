package comp110;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class AppBase extends Application {

  public static final CountDownLatch _latch = new CountDownLatch(1);

  private static AppBase _app = null;

  private static double _startOffset = 0.0;

  /*
   * Singleton pattern.
   */
  public static AppBase instance() {
    if (_app == null) {
      Runnable init = () -> {
        launch(AppBase.class);
      };
      new Thread(init).start();
      try {
        _latch.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return _app;
  }

  private static void initialized(AppBase app) {
    _app = app;
    _latch.countDown();
  }

  public void start(Stage stage) throws Exception {
    initialized(this);
  }

  public void show(Stage stage) {
    stage.show();
  }

  public <T> T loadWindow(String title, Class<?> klass, String fxmlResource) {
    // We're employing a few hacks here to try and hide some pain.
    return runFX(() -> {
      try {
        FXMLLoader fxmlLoader = new FXMLLoader(klass.getResource(fxmlResource));
        Parent view = fxmlLoader.load();
        Scene scene = new Scene(view, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setX(stage.getX() + _startOffset);
        stage.setY(stage.getY() + _startOffset);
        stage.setResizable(false);
        stage.sizeToScene();
        _startOffset += 32.0;
        stage.show();
        return fxmlLoader.getController();
      } catch (Exception e) {
        e.printStackTrace();
        System.exit(1);
      }
      return null;
    });
  }

  public <T> T runFX(Callable<T> callable) {
    final FutureTask<T> query = new FutureTask<>(callable);
    Platform.runLater(query);
    try {
      return query.get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    throw new RuntimeException("A threading error occurred. This is our fault, not yours!");
  }

}