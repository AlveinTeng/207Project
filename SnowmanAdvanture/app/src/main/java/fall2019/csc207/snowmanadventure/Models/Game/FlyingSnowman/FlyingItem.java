package fall2019.csc207.snowmanadventure.Models.Game.FlyingSnowman;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import fall2019.csc207.snowmanadventure.Models.Game.DrawItem;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

/** This is a class that describes the flying item */
public abstract class FlyingItem implements DrawItem {
  /** X coordinate of FlyingItem */
  private int x;

  /** Y coordinate of FlyingItem */
  private int y;

  /** The width of the screen */
  private int width;

  /** The height of the screen */
  private int height;

  /** The speed of the FlyingItem */
  private int speed;

  /** Boolean indicating whether a FlyingItem is exist */
  private boolean exist = true;

  /** Imager for game two */
  private ImageManager imageManagerTwo;

  private Bitmap bitmap;

  /**
   * Init the FlyingItem
   *
   * @param imageManagerTwo the image manager
   */
  public FlyingItem(ImageManager imageManagerTwo) {
    this.imageManagerTwo = imageManagerTwo;
    initBitmap();
  }

  /**
   * Setter for location
   *
   * @param x x location
   * @param y y location
   */
  public void setLocation(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Setter for bound
   *
   * @param width width of the bound
   * @param height height of the bound
   */
  public void setBound(int width, int height) {
    this.width = width;
    this.height = height - 60;
  }

  /**
   * Draw the image
   *
   * @param canvas canvas
   * @param bitmap the corresponding bitmap
   * @param x the x location
   * @param y the y location
   */
  private void drawImage(Canvas canvas, Bitmap bitmap, int x, int y) {
    canvas.drawBitmap(bitmap, x * imageManagerTwo.getUnit(), y * imageManagerTwo.getUnit(), null);
  }

  /**
   * Getter for x
   *
   * @return the x location
   */
  public int getX() {
    return x;
  }

  /**
   * Getter for Y
   *
   * @return the y location
   */
  public int getY() {
    return y;
  }

  /**
   * Setter for Y
   *
   * @param y the y location
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Getter for Bitmap
   *
   * @return the corresponding bitmap
   */
  public Bitmap getBitmap() {
    return bitmap;
  }

  /**
   * Getter for speed
   *
   * @return the speed
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * Setter for Speed
   *
   * @param speed the speed of the item
   */
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  /**
   * Getter for height
   *
   * @return the height
   */
  public int getHeight() {
    return height;
  }

  /**
   * Getter for imageManager
   *
   * @return the image manager
   */
  ImageManager getImageManagerTwo() {
    return imageManagerTwo;
  }

  /**
   * Check whether an item is exist
   *
   * @return whether the item is exist
   */
  public boolean isExist() {
    return exist;
  }

  /** Check whether the SnowMan dies */
  public void die() {
    exist = false;
  }

  /**
   * Increase the Speed
   *
   * @param offset the offset that speed need to change
   */
  void increaseSpeed(int offset) {
    speed += offset;
  }

  /** Update the item */
  public void update() {
    x -= speed;
    checkIsOutOfBound();
  }

  /** Check whether an item is out of Bound */
  public void checkIsOutOfBound() {
    if (x <= 0) {
      die();
    }
  }

  /**
   * Change the bitmap
   *
   * @param bitmap the corresponding bitmap
   */
  void changeBitmap(Bitmap bitmap) {
    this.bitmap = bitmap;
  }

  /** initialize the bitmap */
  public abstract void initBitmap();

  /** Draw the item */
  @Override
  public void draw(Canvas canvas) {
    drawImage(canvas, bitmap, x, y);
  }
}
