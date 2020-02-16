package fall2019.csc207.snowmanadventure.Models.ImageManagers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;

import fall2019.csc207.snowmanadventure.R;

public class ImageManagerThree implements ImageManager {
  /** the bitmaps for snowman */
  private Bitmap[] bitmapSnowman = new Bitmap[2];

  /** the bitmaps for background */
  private Bitmap background;

  /** the bitmaps for snowthug */
  private Bitmap[] snowthug = new Bitmap[2];

  /** the bitmap for change background */
  private Bitmap changeableBackground;

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
   * initialize a new game three image manager
   *
   * @param view the view for game three
   */
  public ImageManagerThree(SurfaceView view, int height, int width, int unit) {
    this.height = height;
    this.width = width;
    this.unit = unit;
    loadBitmaps(view);
  }

  /**
   * store each bitmap in this image manager
   *
   * @param view the view for game three
   */
  private void loadBitmaps(SurfaceView view) {
    Bitmap[] bitmapSnowman1 = new Bitmap[2];
    bitmapSnowman1[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.snowmanleft);
    bitmapSnowman1[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.snowmanright);

    Bitmap background1 =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.cathcingballback1);
    Bitmap background2 =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.cathingballback2);

    Bitmap[] snowthug1 = new Bitmap[2];
    snowthug1[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.snowthug3);
    snowthug1[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.snowthug4);

    loadBitmapsBackground(background1);
    loadBitmapsChangeableBackground(background2);
    loadBitmapsSnowman(bitmapSnowman1);
    loadBitmapSnowthug(snowthug1);
  }

  private void loadBitmapSnowthug(Bitmap[] snowthug) {
    this.snowthug[0] = Bitmap.createScaledBitmap(snowthug[0], 48 * unit, 60 * unit, false);
    this.snowthug[1] = Bitmap.createScaledBitmap(snowthug[1], 48 * unit, 60 * unit, false);
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

  /**
   * load the background
   *
   * @param background the background bitmap to be loaded
   */
  private void loadBitmapsBackground(Bitmap background) {
    this.background = Bitmap.createScaledBitmap(background, width, height, false);
  }

  /**
   * load the snowman bitmap
   *
   * @param bitmapSnowman the snowman bitmap to be loaded
   */
  private void loadBitmapsSnowman(Bitmap[] bitmapSnowman) {
    for (int i = 0; i < bitmapSnowman.length; i++) {
      this.bitmapSnowman[i] =
          Bitmap.createScaledBitmap(bitmapSnowman[i], 40 * unit, 60 * unit, false);
    }
  }

  /**
   * return the snowman bitmaps
   *
   * @return returns the snowman bitmaps
   */
  @Override
  public Bitmap[] getBitmapSnowman() {
    return (useThug) ? snowthug : bitmapSnowman;
  }

  /**
   * return the background bitmap
   *
   * @return returns the background bitmap
   */
  @Override
  public Bitmap getBackground() {
    return (choice) ? changeableBackground : background;
  }

  /**
   * return the ladder bitmap
   *
   * @return returns the ladder bitmap
   */
  @Override
  public Bitmap getBitmapLadder() {
    return null;
  }

  /**
   * return the rope bitmap
   *
   * @return returns the rope bitmap
   */
  @Override
  public Bitmap getBitmapRope() {
    return null;
  }

  /**
   * return the rope swinging bitmap
   *
   * @return returns the rope swinging bitmap
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
