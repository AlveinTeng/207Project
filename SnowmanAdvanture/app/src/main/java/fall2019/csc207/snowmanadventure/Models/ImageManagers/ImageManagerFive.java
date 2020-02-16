package fall2019.csc207.snowmanadventure.Models.ImageManagers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.SurfaceView;

import fall2019.csc207.snowmanadventure.R;

/** This class describes the image manager five */
public class ImageManagerFive implements ImageManager {

  /** The array of bitmaps of cards */
  private Bitmap[] bitmapCuteCharacters = new Bitmap[7];

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

  public ImageManagerFive(SurfaceView view, int height, int width, int unit) {
    this.height = height;
    this.width = width;
    this.unit = unit;
    loadBitmaps(view);
  }

  private void loadBitmaps(SurfaceView view) {
    Bitmap[] bitmapCuteCharacters1 = new Bitmap[7];
    bitmapCuteCharacters1[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.back);
    bitmapCuteCharacters1[1] =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.starfish);
    bitmapCuteCharacters1[2] = BitmapFactory.decodeResource(view.getResources(), R.drawable.rabbit);
    bitmapCuteCharacters1[3] =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.snowman);
    bitmapCuteCharacters1[4] =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.dinosaurone);
    bitmapCuteCharacters1[5] =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.dinosaurtwo);
    bitmapCuteCharacters1[6] =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.dinosaurthree);

    Bitmap background1 =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.gamefivebackgroundtwo);
    Bitmap changeableBackground1 =
        BitmapFactory.decodeResource(view.getResources(), R.drawable.gamefivebackgroundone);

    loadAllCharacters(bitmapCuteCharacters1);
    loadBackgrounds(background1, changeableBackground1);
  }

  private void loadAllCharacters(Bitmap[] bitmapCuteCharacters1) {
    this.bitmapCuteCharacters[0] =
        Bitmap.createScaledBitmap(bitmapCuteCharacters1[0], 40 * unit, 40 * unit, false);
    this.bitmapCuteCharacters[1] =
        Bitmap.createScaledBitmap(bitmapCuteCharacters1[1], 40 * unit, 40 * unit, false);
    this.bitmapCuteCharacters[2] =
        Bitmap.createScaledBitmap(bitmapCuteCharacters1[2], 40 * unit, 40 * unit, false);
    this.bitmapCuteCharacters[3] =
        Bitmap.createScaledBitmap(bitmapCuteCharacters1[3], 40 * unit, 40 * unit, false);
    this.bitmapCuteCharacters[4] =
        Bitmap.createScaledBitmap(bitmapCuteCharacters1[4], 40 * unit, 40 * unit, false);
    this.bitmapCuteCharacters[5] =
        Bitmap.createScaledBitmap(bitmapCuteCharacters1[5], 40 * unit, 40 * unit, false);
    this.bitmapCuteCharacters[6] =
        Bitmap.createScaledBitmap(bitmapCuteCharacters1[6], 40 * unit, 40 * unit, false);
  }

  private void loadBackgrounds(Bitmap background1, Bitmap changeableBackground1) {
    this.background = Bitmap.createScaledBitmap(background1, width, height, false);
    this.changeableBackground =
        Bitmap.createScaledBitmap(changeableBackground1, width, height, false);
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
    return bitmapCuteCharacters;
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
