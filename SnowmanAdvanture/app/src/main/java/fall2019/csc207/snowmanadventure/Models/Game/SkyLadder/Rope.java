package fall2019.csc207.snowmanadventure.Models.Game.SkyLadder;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

public class Rope extends SkyLadderItem {
  /** BitMap */
  private final Bitmap NEW_BITMAP = getImageManagerOne().getBitmapRope();
  /** Power of rope */
  private int power = 0;
  /** Current BitMapIndex */
  private int currBitmapIndex = 0;
  /** Length of Rope */
  private int length = 0;
  /**
   * The next Ladder.
   *
   * <p>This is used to compare its distant from current ladder with the power to determine if this
   * throwing is succeeded.
   */
  private SkyLadder target;

  /** Constructor of Rope */
  public Rope(int x, int y, ImageManager imageManagerOne) {
    super(x, y, imageManagerOne);
  }

  /** Setter for target */
  public void setTarget(SkyLadder target) {
    this.target = target;
  }

  /** Initialize Bitmap */
  @Override
  void initBitmap() {
    power = 0;
    changeBitmap(getImageManagerOne().getBitmapRope());
  }

  /**
   * Update when the status is HOLD
   *
   * @return True if status need to change
   */
  @Override
  boolean holdUpdate() {
    charge();
    if (currBitmapIndex == 7) currBitmapIndex = 0;
    else currBitmapIndex += 1;
    changeBitmap(getImageManagerOne().getBitmapRopeSwing()[currBitmapIndex]);
    return false;
  }

  /**
   * Power the length.
   *
   * <p>Call this when the status is *HOLD*.
   */
  private void charge() {
    power = Math.min(100, power + 1);
  }

  /**
   * Update when the status is THROW
   *
   * @return True if status need to change
   */
  @Override
  boolean throwUpdate() {
    changeBitmap(getImageManagerOne().getBitmapRope());
    length++;
    return length == power;
  }

  /** @return length of the Rope */
  public int getLength() {
    return length;
  }

  /**
   * Update when the status is UP
   *
   * @return True if status need to change
   */
  @Override
  boolean upUpdate() {
    power = 0;
    initBitmap();
    length--;
    movingUp();
    return getY() + 42 == target.getY();
  }

  /**
   * Update when the status is DOWN
   *
   * @return True if status need to change
   */
  @Override
  boolean downUpdate() {
    length = 0;
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
    power = 0;
    length--;
    return getLength() == 0;
  }

  /** Draw the items */
  @Override
  public void draw(Canvas canvas) {
    if (getLength() > 0) {
      for (int i = 0; i < getLength(); i++) {
        drawImage(canvas, NEW_BITMAP, getX(), -i + getY());
      }
    } else {
      drawImage(canvas, getBitmap(), getX(), getY());
    }
  }
}
