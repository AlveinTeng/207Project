package fall2019.csc207.snowmanadventure.Models.Game.FlyingSnowman;

import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

/** This class is a Ladder that extends the FlyingItem */
public class Ladder extends FlyingItem {
  /**
   * The constructor for the image manager
   *
   * @param imageManagerTwo the image manager
   */
  public Ladder(ImageManager imageManagerTwo) {
    super(imageManagerTwo);
  }

  /** Check whether an item is out of bound */
  @Override
  public void checkIsOutOfBound() {
    if (getX() + getBitmap().getWidth() <= 0) {
      die();
    }
  }

  /** Intialize the Bitmap */
  @Override
  public void initBitmap() {
    changeBitmap(getImageManagerTwo().getBitmapLadder());
  }
}
