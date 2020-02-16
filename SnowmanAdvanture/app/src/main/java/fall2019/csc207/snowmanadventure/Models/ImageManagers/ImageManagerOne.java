package fall2019.csc207.snowmanadventure.Models.ImageManagers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;

import fall2019.csc207.snowmanadventure.R;

public class ImageManagerOne implements ImageManager {

  /** the bitmaps for rope spinning */
  private Bitmap[] bitmapRopeSwing = new Bitmap[8];

  /** whether the user use thug */
  private boolean useThug;

  /** The choice for the background */
  private boolean choice;

  /** the bitmaps for snowmen */
  private Bitmap[] bitmapSnowman = new Bitmap[2];

  /** the bitmaps for snowthug */
  private Bitmap[] bitmapSnowthug = new Bitmap[2];

  /** the bitmaps for a straight rope */
  private Bitmap bitmapRope;

  /** the bitmaps for the side green background */
  private Bitmap background;

  /** the bitmap for the changed background */
  private Bitmap changeableBackground;

  /** the bitmap for ladder */
  private Bitmap ladder;

  /** The height of the screen */
  private int height;

  /** The width of the screen */
  private int width;

  /** The unit of this image manager */
  private int unit;

  /**
   * constructs a new game one image manager
   *
   * @param view the game view for game one
   */
  public ImageManagerOne(SurfaceView view, int height, int width, int unit) {
    this.height = height;
    this.width = width;
    this.unit = unit;
    loadBitmaps(view);
  }

  /**
   * loads all bitmaps for each item in game one
   *
   * @param view the game view for game one
   */
  private void loadBitmaps(SurfaceView view) {
    Bitmap[] bitmapRopeSwing1 = new Bitmap[8];
    bitmapRopeSwing1[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.rope1);
    bitmapRopeSwing1[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.rope2);
    bitmapRopeSwing1[2] = BitmapFactory.decodeResource(view.getResources(), R.drawable.rope3);
    bitmapRopeSwing1[3] = BitmapFactory.decodeResource(view.getResources(), R.drawable.rope4);
    bitmapRopeSwing1[4] = BitmapFactory.decodeResource(view.getResources(), R.drawable.rope5);
    bitmapRopeSwing1[5] = BitmapFactory.decodeResource(view.getResources(), R.drawable.rope6);
    bitmapRopeSwing1[6] = BitmapFactory.decodeResource(view.getResources(), R.drawable.rope7);
    bitmapRopeSwing1[7] = BitmapFactory.decodeResource(view.getResources(), R.drawable.rope8);
    Bitmap[] bitmapSnowman1 = new Bitmap[2];
    bitmapSnowman1[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.snowman_stand);
    bitmapSnowman1[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.snowman_kneel);
    Bitmap bitmapRope1 = BitmapFactory.decodeResource(view.getResources(), R.drawable.rope);

    Bitmap Ladder = BitmapFactory.decodeResource(view.getResources(), R.drawable.ladderstep);

    Bitmap background1 = BitmapFactory.decodeResource(view.getResources(), R.drawable.gameoneback1);
    Bitmap background2 = BitmapFactory.decodeResource(view.getResources(), R.drawable.gameoneback2);

    Bitmap[] snowthug1 = new Bitmap[2];
    snowthug1[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.snowthug1);
    snowthug1[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.snowthug2);
    loadBitmapsSnowthug(snowthug1);
    loadBitmapsBackground(background1);
    loadBitmapsChangeableBackground(background2);
    loadBitmapsRopeSwing(bitmapRopeSwing1);
    loadBitmapLadder(Ladder);
    loadBitmapsSnowman(bitmapSnowman1);
    loadBitmapRope(bitmapRope1);
  }

  private void loadBitmapsSnowthug(Bitmap[] bitmapSnowthug) {
    for (int i = 0; i < bitmapSnowthug.length; i++) {
      this.bitmapSnowthug[i] =
          Bitmap.createScaledBitmap(bitmapSnowthug[i], 35 * unit, 40 * unit, false);
    }
  }
  /**
   * loads the changed background bitmap
   *
   * @param changeableBackground1 the bitmap for the changed background
   */
  private void loadBitmapsChangeableBackground(Bitmap changeableBackground1) {
    this.changeableBackground =
        Bitmap.createScaledBitmap(changeableBackground1, width, height, false);
  }

  /**
   * loads the rope bitmap
   *
   * @param bitmapRope the bitmap for rope
   */
  private void loadBitmapRope(Bitmap bitmapRope) {
    this.bitmapRope = Bitmap.createScaledBitmap(bitmapRope, 30 * unit, 15 * unit, false);
  }

  /**
   * loads the snowman bitmap
   *
   * @param bitmapSnowman the bitmap for snowman
   */
  private void loadBitmapsSnowman(Bitmap[] bitmapSnowman) {
    for (int i = 0; i < bitmapSnowman.length; i++) {
      this.bitmapSnowman[i] =
          Bitmap.createScaledBitmap(bitmapSnowman[i], 30 * unit, 40 * unit, false);
    }
  }

  /**
   * loads the rope spinning bitmap
   *
   * @param bitmapRopeSwing the bitmap for rope spinning
   */
  private void loadBitmapsRopeSwing(Bitmap[] bitmapRopeSwing) {
    for (int i = 0; i < bitmapRopeSwing.length; i++) {
      this.bitmapRopeSwing[i] =
          Bitmap.createScaledBitmap(bitmapRopeSwing[i], 30 * unit, 30 * unit, false);
    }
  }

  /**
   * loads the white bitmap background
   *
   * @param background the white background
   */
  private void loadBitmapsBackground(Bitmap background) {
    this.background = Bitmap.createScaledBitmap(background, width, height, false);
  }

  /**
   * loads the bitmap ladder
   *
   * @param Ladder the ladder bitmap
   */
  private void loadBitmapLadder(Bitmap Ladder) {
    this.ladder = Bitmap.createScaledBitmap(Ladder, 56 * unit, 8 * unit, false);
  }

  /**
   * returns the bitmap snowman
   *
   * @return returns the snowman
   */
  @Override
  public Bitmap[] getBitmapSnowman() {
    return (useThug) ? bitmapSnowthug : bitmapSnowman;
  }

  /**
   * returns the bitmap ladder
   *
   * @return the bitmap ladder
   */
  @Override
  public Bitmap getBitmapLadder() {
    return ladder;
  }

  /**
   * returns the white background
   *
   * @return the white background
   */
  @Override
  public Bitmap getBackground() {
    return (choice) ? changeableBackground : background;
  }

  /**
   * returns the rope bitmap
   *
   * @return returns the bitmap rope
   */
  @Override
  public Bitmap getBitmapRope() {
    return bitmapRope;
  }

  /**
   * returns the rope swinging bitmaps
   *
   * @return returns the rope swinging bitmaps
   */
  @Override
  public Bitmap[] getBitmapRopeSwing() {
    return bitmapRopeSwing;
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
