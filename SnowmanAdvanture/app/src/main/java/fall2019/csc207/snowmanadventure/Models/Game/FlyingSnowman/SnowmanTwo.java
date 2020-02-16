package fall2019.csc207.snowmanadventure.Models.Game.FlyingSnowman;

import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

/** This is a class that describes the snowman for the game two, extends the flying item */
public class SnowmanTwo extends FlyingItem {

  /**
   * Constructor for SnowmanTwo
   *
   * @param imageManagerTwo the image
   */
  public SnowmanTwo(ImageManager imageManagerTwo) {
    super(imageManagerTwo);
  }

  /**
   * Update the snowman
   *
   * @param isTouch True if the screen is touched
   */
  public void update(boolean isTouch) {
    setY(Math.min(Math.max(getY() + getSpeed(), 0), getHeight()));
    increaseSpeed(1);
    if (isTouch) {
      setSpeed(-5);
      changeBitmap(getImageManagerTwo().getBitmapSnowman()[1]);
    } else {
      changeBitmap(getImageManagerTwo().getBitmapSnowman()[0]);
    }
  }

  /** Initialize the Bitmap */
  @Override
  public void initBitmap() {
    changeBitmap(getImageManagerTwo().getBitmapSnowman()[0]);
  }
}
