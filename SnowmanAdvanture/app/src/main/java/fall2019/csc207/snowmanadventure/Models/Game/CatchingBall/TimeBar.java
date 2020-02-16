package fall2019.csc207.snowmanadventure.Models.Game.CatchingBall;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import fall2019.csc207.snowmanadventure.Models.Game.DrawItem;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

public class TimeBar implements DrawItem {

  /** The x coordinates of the class object */
  private int x;

  /** The y coordinates of the class object */
  private int y;

  /** The time left for a round */
  private int timeCount;

  /** The Paint of the class object */
  private Paint paint;

  /** The ImageManager for this class object */
  private ImageManager imageManager;

  /**
   * The constructor to initialize the class object set the coordinates, appearance and the time
   * left.
   *
   * @param x the int representing the x coordinates
   * @param y the int representing the y coordinates
   */
  public TimeBar(int x, int y, ImageManager imageManager) {
    this.imageManager = imageManager;
    this.x = x;
    this.y = y;
    paint = new Paint();
    paint.setColor(Color.RED);
    paint.setAntiAlias(false);
    resetTime();
  }

  /** Reset the time back to 300 */
  public void resetTime() {
    timeCount = 300;
  }

  /**
   * The getter of timeCount attribute
   *
   * @return a positive integer represent how many time left, or 0 if no time left
   */
  public int getTimeCount() {
    return timeCount;
  }

  protected void setTimeCount(int timeCount) {
    this.timeCount = timeCount;
  }

  /** Update the time for a single round */
  public void update() {
    timeCount = Math.max(timeCount - 1, 0);
  }

  /**
   * Draw the image of TimeBar object on the screen by the current Canvas
   *
   * @param canvas the current Canvas
   */
  @Override
  public void draw(Canvas canvas) {
    canvas.drawRect(
        x * imageManager.getUnit(),
        (y - timeCount) * imageManager.getUnit(),
        (x + 5) * imageManager.getUnit(),
        y * imageManager.getUnit(),
        paint);
  }
}
