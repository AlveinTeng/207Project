package fall2019.csc207.snowmanadventure.Models.Game.SkyLadder;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

/** This is an abstract class that represents all items in the first game, SkyLadder. */
public abstract class SkyLadderItem {
  /** This items's speed for move */
  private final int speed = 1;
  /** This item's horizontal coordinate on top-left. */
  private int x;
  /** This item's vertical coordinate on the top-left. */
  private int y;
  private Bitmap bitmap;
  private ImageManager imageManagerOne;

  /**
   * Constructor for SkyLadderItem.
   *
   * @param x the horizontal coordinate.
   * @param y the vertical coordinate.
   * @param imageManagerOne the imageManagerOne.
   */
  SkyLadderItem(int x, int y, ImageManager imageManagerOne) {
    this.imageManagerOne = imageManagerOne;
    setLocation(x, y);
    initBitmap();
  }

  /**
   * Move the items up.
   *
   * <p>decrease the y coordinate by speed.
   */
  void movingUp() {
    y -= speed;
  }

  /**
   * Move the items down.
   *
   * <p>increase the y coordinate by speed.
   */
  void movingDown() {
    y += speed;
  }

  /** Getter for the Y coordinate of item */
  public int getY() {
    return y;
  }

  /**
   * Setter for the location of item.
   *
   * @param x the x coordinate
   * @param y the y coordinate
   */
  private void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /** Draw items */
  public void draw(Canvas canvas) {
    drawImage(canvas, bitmap, x, y);
  }

  /** Draw Image */
  void drawImage(Canvas canvas, Bitmap bitmap, int x, int y) {
    canvas.drawBitmap(bitmap, x * imageManagerOne.getUnit(), y * imageManagerOne.getUnit(), null);
  }

  /**
   * Update the item.
   *
   * <p>This method has been calls every frame. The items' update behavior according to the status
   * now. This method just simply assign the correct sub-update method by the status.
   *
   * <p>Note: the method upUpdate and downUpdate does not mean the items are going up or down, it
   * indicates the status is *UP* or *DOWN*
   *
   * @param status the GameStatus now.
   * @return if the status need to be changed.
   */
  public boolean update(GameStatus status) {
    switch (status) {
      case STAND:
        return false;
      case HOLD:
        return holdUpdate();
      case THROW:
        return throwUpdate();
      case UP:
        return upUpdate();
      case DOWN:
        return downUpdate();
      case FAILED:
        return failedUpdate();
      default: // case STAND
        return false;
    }
  }

  /** Getter for ImageManagerOne */
  ImageManager getImageManagerOne() {
    return imageManagerOne;
  }

  /** Getter for the X coordinate */
  int getX() {
    return x;
  }

  /** Getter for BitMap */
  Bitmap getBitmap() {
    return bitmap;
  }

  /** Setter for BitMap */
  void changeBitmap(Bitmap bitmap) {
    this.bitmap = bitmap;
  }

  /** Initializer of BitMap */
  abstract void initBitmap();

  /**
   * A sub-update method which called when update called and the status is *HOLD*.
   *
   * @return True if status need to change
   */
  abstract boolean holdUpdate();

  /**
   * A sub-update method which called when update called and the status is *THROW*.
   *
   * @return True if status need to change
   */
  abstract boolean throwUpdate();

  /**
   * A sub-update method which called when update called and the status is *UP*.
   *
   * @return True if status need to change
   */
  abstract boolean upUpdate();

  /**
   * A sub-update method which called when update called and the status is *DOWN*.
   *
   * @return True if status need to change
   */
  abstract boolean downUpdate();

  /**
   * A sub-update method which called when update called and the status is *FAILED*.
   *
   * @return True if status need to change
   */
  abstract boolean failedUpdate();
}
