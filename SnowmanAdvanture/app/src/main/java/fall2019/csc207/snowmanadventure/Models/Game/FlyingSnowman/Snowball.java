package fall2019.csc207.snowmanadventure.Models.Game.FlyingSnowman;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

/** this is a class that describes the snowball extends the flying item */
public class Snowball extends FlyingItem {
  /** the paint */
  private Paint paint;

  /**
   * Constructor for Snowball
   *
   * @param imageManagerTwo the image manager
   */
  public Snowball(ImageManager imageManagerTwo) {
    super(imageManagerTwo);
  }

  /**
   * Draw a SnowBall
   *
   * @param canvas the canvas
   */
  @Override
  public void draw(Canvas canvas) {
    canvas.drawCircle(
        getX() * getImageManagerTwo().getUnit(),
        getY() * getImageManagerTwo().getUnit(),
        2 * getImageManagerTwo().getUnit(),
        paint);
  }

  /** Initialize the Bitmap */
  @Override
  public void initBitmap() {
    paint = new Paint();
    paint.setColor(Color.GREEN);
    paint.setAntiAlias(false);
  }
}
