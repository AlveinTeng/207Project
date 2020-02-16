package fall2019.csc207.snowmanadventure.Models.Game.SkyLadder;

import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

public class Snowman extends SkyLadderItem {

  /** The initial Y coordinate */
  private int initY;

  /**
   * Constructor for SnowMan
   *
   * <p>Set the initY used to determine if this snowman reach the initial location.
   */
  public Snowman(int x, int y, ImageManager imageManagerOne) {
    super(x, y, imageManagerOne);
    initY = y;
  }

  /** Initialize the Bitmap */
  @Override
  void initBitmap() {
    changeBitmap(getImageManagerOne().getBitmapSnowman()[0]);
  }

  /**
   * Update when the status is HOLD
   *
   * @return True if status need to change
   */
  @Override
  boolean holdUpdate() {
    changeBitmap(getImageManagerOne().getBitmapSnowman()[1]);
    return false;
  }

  /**
   * Update when the status is THROW
   *
   * @return True if status need to change
   */
  @Override
  boolean throwUpdate() {
    changeBitmap(getImageManagerOne().getBitmapSnowman()[0]);
    return false;
  }

  /**
   * Update when the status is UP
   *
   * @return True if status need to change
   */
  @Override
  boolean upUpdate() {
    movingUp();
    changeBitmap(getImageManagerOne().getBitmapSnowman()[0]);
    return false;
  }

  /**
   * Update when the status is DOWN
   *
   * <p>If the snowman reaches the initial location, return True to manager it need to enter the
   * Status *STAND*.
   *
   * @return True if status need to change
   */
  @Override
  boolean downUpdate() {
    movingDown();
    return getY() == initY;
  }

  /**
   * Update when the status is FAILED
   *
   * @return True if status need to change
   */
  @Override
  boolean failedUpdate() {
    return false;
  }
}
