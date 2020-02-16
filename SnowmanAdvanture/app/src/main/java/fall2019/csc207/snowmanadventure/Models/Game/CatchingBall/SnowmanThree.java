package fall2019.csc207.snowmanadventure.Models.Game.CatchingBall;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

public class SnowmanThree extends CatchingItem {

  /** The Bitmap image of the snowman */
  private Bitmap bitmap;

  /**
   * The constructor to initialize the class
   *
   * @param imageManagerThree the ImageManager of the game
   */
  public SnowmanThree(ImageManager imageManagerThree) {
    super(imageManagerThree);
  }

  /**
   * change the speed based on given moving state
   *
   * @param movement the current Movement
   */
  public void update(Movement movement) {
    if (movement == Movement.MOVE_LEFT) {
      moveRight(false);
      increaseSpeed(-1);
    } else if (movement == Movement.MOVE_RIGHT) {
      moveRight(true);
      increaseSpeed(1);
    } else {
      setSpeed(0);
    }
    checkSpeed(getSpeed());
  }

  /**
   * move the snowman either to the left or right based on given speed
   *
   * @param speed the int representing current speed
   */
  private void checkSpeed(int speed) {
    if (speed > 0) {
      changeBitmap(bitmap);
    } else if (speed < 0) {
      changeBitmap(bitmap);
    }
  }

  /** initialize the image of SnowmanTree class */
  @Override
  public void initImage() {
    changeBitmap(getImageManagerThree().getBitmapSnowman()[0]);
  }

  /**
   * Draw the image of SnowmanThree class on the screen based on given canvas
   *
   * @param canvas the current Canvas
   */
  @Override
  public void draw(Canvas canvas) {
    drawImage(canvas, bitmap, getX(), getY());
  }

  /**
   * Initiate the image of SnowmanThree class
   *
   * @param canvas the current Canvas
   * @param bitmap the current Bitmap stored in this class
   * @param x the int representing x coordinates of the object
   * @param y the int representing y coordinates of the object
   */
  private void drawImage(Canvas canvas, Bitmap bitmap, int x, int y) {
    canvas.drawBitmap(
        bitmap, x * getImageManagerThree().getUnit(), y * getImageManagerThree().getUnit(), null);
  }

  /**
   * The getter of SnowmanThree bitmap
   *
   * @return a Bitmap of SnowmanThree
   */
  public Bitmap getBitmap() {
    return bitmap;
  }

  /**
   * Modify the current bitmap by the given bitmap
   *
   * @param bitmap the new Bitmap to be stored
   */
  private void changeBitmap(Bitmap bitmap) {
    this.bitmap = bitmap;
  }
}
