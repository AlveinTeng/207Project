package fall2019.csc207.snowmanadventure.Models.ImageManagers;

import android.graphics.Bitmap;

public interface ImageManager {

  /**
   * returns all the snowman bitmaps
   *
   * @return returns array of all snowman bitmaps
   */
  Bitmap[] getBitmapSnowman();

  /**
   * return the ladder bitmap
   *
   * @return the ladder bitmap object
   */
  Bitmap getBitmapLadder();

  /**
   * return the background bitmap
   *
   * @return the background bitmap object
   */
  Bitmap getBackground();

  /**
   * return the rope bitmap
   *
   * @return returns the rope bitmap
   */
  Bitmap getBitmapRope();

  /**
   * return the rope swinging bitmaps
   *
   * @return returns the rope swinging bitmaps
   */
  Bitmap[] getBitmapRopeSwing();

  Bitmap[] getCards();

  void changeSnowman(boolean useThug);

  void changeBackground(boolean choice);

  int getHeight();

  int getWidth();

  int getUnit();
}
