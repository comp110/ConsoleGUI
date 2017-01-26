package comp110.memes;

import java.util.Observable;

public class MemePhoto extends Observable {

  private String _imageUrl, _topText, _bottomText;

  public MemePhoto() {
    _imageUrl = "";
    _topText = "";
    _bottomText = "";
    DankMemeController.show(this);
  }

  public String getImageUrl() {
    return _imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this._imageUrl = imageUrl;
    setChanged();
    notifyObservers();
  }

  public String getTopText() {
    return _topText;
  }

  public void setTopText(String topText) {
    this._topText = topText;
    setChanged();
    notifyObservers();
  }

  public String getBottomText() {
    return _bottomText;
  }

  public void setBottomText(String bottomText) {
    this._bottomText = bottomText;
    setChanged();
    notifyObservers();
  }

}