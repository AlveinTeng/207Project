package fall2019.csc207.snowmanadventure.Models.ImageManagers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.SurfaceView;

import fall2019.csc207.snowmanadventure.R;

public class ImageManagerTwo implements ImageManager {

  /** the bitmaps for snowmen */
  private Bitmap[] bitmapSnowman = new Bitmap[2];

  /** the bitmap for ladder */
  private Bitmap bitmapLadder;

  /** the bitmaps for background */
  private Bitmap background;

  /** the bitmap for change background */
  private Bitmap changeableBackground;

  /** the bitmaps for snowthug */
  private Bitmap[] snowthug = new Bitmap[2];

  /** The choice for using the thug */
  private boolean useThug;

  /** The choice for changing the background */
  private boolean choice;

  /** The height of the screen */
  private int height;

  /** The width of the screen */
  private int width;

  /** The unit of this image manager */
  private int unit;

  /**
   * Construct a new instance of ImageManagerTwo.
   *
   * @param view A GameTwoView.
   */
  public ImageManagerTwo(SurfaceView view, int height, int width, int unit) {
    this.height = height;
    this.width = width;
    this.unit = unit;
    loadBitmaps(view);
  }

  /**
   * Create the Bitmaps according to the GameTwoView <view> given.
   *
   * @param view A GameTwoView.
   */
  private void loadBitmaps(SurfaceView view) {
    Bitmap[] bitmapSnowman1 = new Bitmap[2];
    Bitmap snowmanStand =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.snowman_stand);
    Bitmap snowmanKneel =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.snowman_kneel);
    Matrix matrix = new Matrix();
    matrix.setScale(-1, 1);
    Bitmap inverseSnowmanStand =
        Bitmap.createBitmap(
            snowmanStand, 0, 0, snowmanStand.getWidth(), snowmanStand.getHeight(), matrix, true);
    Bitmap inverseSnowmanKneel =
        Bitmap.createBitmap(
            snowmanKneel, 0, 0, snowmanKneel.getWidth(), snowmanKneel.getHeight(), matrix, true);
    bitmapSnowman1[0] = inverseSnowmanStand;
    bitmapSnowman1[1] = inverseSnowmanKneel;

    Bitmap bitmapLadder1 = BitmapFactory.decodeResource(view.getResources(), R.drawable.ladderstep);
    Bitmap background1 =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.flyingsnowmanback1);
    Bitmap background2 =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.flyingsnowmanback2);

    Bitmap[] snowthug1 = new Bitmap[2];
    snowthug1[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.snowthug4);
    snowthug1[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.snowthug4);

    loadBitmapsBackground(background1);
    loadBitmapsChangeableBackground(background2);
    loadBitmapLadder(bitmapLadder1);
    loadBitmapsSnowman(bitmapSnowman1);
    loadBitmapSnowthug(snowthug1);
  }

  public void loadBitmapSnowthug(Bitmap[] snowthug) {
    this.snowthug[0] = Bitmap.createScaledBitmap(snowthug[0], 48 * unit, 56 * unit, false);
    this.snowthug[1] = Bitmap.createScaledBitmap(snowthug[1], 48 * unit, 56 * unit, false);
  }

  /**
   * Create a Bitmap for background of this ImageManagerTwo.
   *
   * @param changeableBackground1 The Bitmap of background.
   */
  private void loadBitmapsChangeableBackground(Bitmap changeableBackground1) {
    this.changeableBackground =
        Bitmap.createScaledBitmap(changeableBackground1, width, height, false);
  }

  /**
   * Create a Bitmap for background of this ImageManagerTwo.
   *
   * @param background The Bitmap of background.
   */
  private void loadBitmapsBackground(Bitmap background) {
    this.background = Bitmap.createScaledBitmap(background, width, height, false);
  }

  /**
   * Create list of Bitmaps for the snowmen of this ImageManagerTwo.
   *
   * @param bitmapSnowman List of Bitmaps of snowmen.
   */
  private void loadBitmapsSnowman(Bitmap[] bitmapSnowman) {
    for (int i = 0; i < bitmapSnowman.length; i++) {
      this.bitmapSnowman[i] =
          Bitmap.createScaledBitmap(bitmapSnowman[i], 32 * unit, 44 * unit, false);
    }
  }

  /**
   * Create a Bitmap for the ladder of this ImageManagerTwo.
   *
   * @param Ladder The Bitmap of ladder.
   */
  private void loadBitmapLadder(Bitmap Ladder) {
    this.bitmapLadder = Bitmap.createScaledBitmap(Ladder, 40 * unit, 4 * unit, false);
  }

  /**
   * Getter of the bitmap of snowmen.
   *
   * @return the bitmap of snowmen.
   */
  @Override
  public Bitmap[] getBitmapSnowman() {
    return (useThug) ? snowthug : bitmapSnowman;
  }

  /**
   * Getter of the bitmap of ladder.
   *
   * @return the bitmap of ladder.
   */
  @Override
  public Bitmap getBitmapLadder() {
    return bitmapLadder;
  }

  /**
   * Getter of the bitmap of background.
   *
   * @return the bitmap of background.
   */
  @Override
  public Bitmap getBackground() {
    return (choice) ? changeableBackground : background;
  }

  /**
   * Getter of the bitmap of rope.
   *
   * @return the bitmap of rope.
   */
  @Override
  public Bitmap getBitmapRope() {
    return null;
  }

  /**
   * Getter of the bitmap of rope swing.
   *
   * @return the bitmap of rope swing.
   */
  @Override
  public Bitmap[] getBitmapRopeSwing() {
    return null;
  }

  @Override
  public Bitmap[] getCards() {
    return null;
  }

  @Override
  public void changeSnowman(boolean useThug) {
    this.useThug = useThug;
  }

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
