package fall2019.csc207.snowmanadventure.Models.ImageManagers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;

import fall2019.csc207.snowmanadventure.R;

/** This class describes the image manager four */
public class ImageManagerFour implements ImageManager {

  /** The array of bitmaps of cards */
  private Bitmap[] bitmapCards = new Bitmap[14];

  /** The background bitmap */
  private Bitmap background;

  /** The changeable background bitmap */
  private Bitmap changeableBackground;

  /** The choice for changing the background */
  private boolean choice;

  /** The height of the screen */
  private int height;

  /** The width of the screen */
  private int width;

  /** The unit of this image manager */
  private int unit;

  public ImageManagerFour(SurfaceView view, int height, int width, int unit) {
    this.height = height;
    this.width = width;
    this.unit = unit;
    loadBitmaps(view);
  }

  public void loadBitmaps(SurfaceView view) {
    Bitmap[] bitmapCards1 = new Bitmap[14];
    bitmapCards1[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.blue_back);
    bitmapCards1[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.a);
    bitmapCards1[2] = BitmapFactory.decodeResource(view.getResources(), R.drawable.two);
    bitmapCards1[3] = BitmapFactory.decodeResource(view.getResources(), R.drawable.three);
    bitmapCards1[4] = BitmapFactory.decodeResource(view.getResources(), R.drawable.four);
    bitmapCards1[5] = BitmapFactory.decodeResource(view.getResources(), R.drawable.five);
    bitmapCards1[6] = BitmapFactory.decodeResource(view.getResources(), R.drawable.six);
    bitmapCards1[7] = BitmapFactory.decodeResource(view.getResources(), R.drawable.seven);
    bitmapCards1[8] = BitmapFactory.decodeResource(view.getResources(), R.drawable.eight);
    bitmapCards1[9] = BitmapFactory.decodeResource(view.getResources(), R.drawable.nine);
    bitmapCards1[10] = BitmapFactory.decodeResource(view.getResources(), R.drawable.ten);
    bitmapCards1[11] = BitmapFactory.decodeResource(view.getResources(), R.drawable.j);
    bitmapCards1[12] = BitmapFactory.decodeResource(view.getResources(), R.drawable.q);
    bitmapCards1[13] = BitmapFactory.decodeResource(view.getResources(), R.drawable.k);

    Bitmap background1 =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.card_background);
    Bitmap changeableBackground1 =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.cathingballback2);

    loadBackGround(background1);
    loadBitmapsChangeableBackground(changeableBackground1);
    loadAll(bitmapCards1);
  }

  /**
   * load the background bitmap into this image manager
   *
   * @param changeableBackground1 the background bitmap
   */
  private void loadBitmapsChangeableBackground(Bitmap changeableBackground1) {
    this.changeableBackground =
        Bitmap.createScaledBitmap(changeableBackground1, width, height, false);
  }

  private void loadBackGround(Bitmap bitmapCardBackGround) {
    this.background = Bitmap.createScaledBitmap(bitmapCardBackGround, width, height, false);
  }

  private void loadAll(Bitmap[] allCards) {
    for (int i = 0; i < allCards.length; i++) {
      bitmapCards[i] = Bitmap.createScaledBitmap(allCards[i], 30 * unit, 42 * unit, false);
    }
  }

  @Override
  public Bitmap[] getBitmapSnowman() {
    return null;
  }

  @Override
  public Bitmap getBitmapLadder() {
    return null;
  }

  @Override
  public Bitmap getBackground() {
    return (choice) ? changeableBackground : background;
  }

  @Override
  public Bitmap getBitmapRope() {
    return null;
  }

  @Override
  public Bitmap[] getBitmapRopeSwing() {
    return null;
  }

  @Override
  public Bitmap[] getCards() {
    return bitmapCards;
  }

  @Override
  public void changeSnowman(boolean useThug) {}

  @Override
  public void changeBackground(boolean choice) {
    this.choice = choice;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getUnit() {
    return unit;
  }
}
