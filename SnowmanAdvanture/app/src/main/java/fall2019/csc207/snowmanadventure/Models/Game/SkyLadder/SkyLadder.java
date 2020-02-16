package fall2019.csc207.snowmanadventure.Models.Game.SkyLadder;

import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

public class SkyLadder extends SkyLadderItem {
  /** Constructor for SkyLadder */
  public SkyLadder(int x, int y, ImageManager imageManagerOne) {
    super(x, y, imageManagerOne);
  }

  /** Initialize the Bitmap */
  @Override
  void initBitmap() {
    changeBitmap(getImageManagerOne().getBitmapLadder());
  }

  /**
   * Update when the status is HOLD
   *
   * @return True if status need to change
   */
  @Override
  boolean holdUpdate() {
    return false;
  }

  /**
   * Update when the status is THROW
   *
   * @return True if status need to change
   */
  @Override
  boolean throwUpdate() {
    return false;
  }

  /**
   * Update when the status is UP
   *
   * @return True if status need to change
   */
  @Override
  boolean upUpdate() {
    return false;
  }

  /**
   * Update when the status is DOWN
   *
   * @return True if status need to change
   */
  @Override
  boolean downUpdate() {
    movingDown();
    return false;
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
