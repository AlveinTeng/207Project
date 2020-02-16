package fall2019.csc207.snowmanadventure.Models.Game.CatchingBall;

import android.graphics.Color;
import android.graphics.Paint;

import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

public class GreenBall extends CatchingItem {

  /**
   * The constructor to initialize the class
   *
   * @param imageManagerThree the image manager of the game
   */
  public GreenBall(ImageManager imageManagerThree) {
    super(imageManagerThree);
  }

  /** Initialize the image of GreenBall set the color radius and score GreenBall object */
  @Override
  public void initImage() {
    setPaint(new Paint());
    getPaint().setColor(Color.GREEN);
    getPaint().setAntiAlias(false);
    setPaintText(new Paint());
    getPaintText().setColor(Color.BLACK);
    int randomInt = (int) (Math.random() * 15);
    setScore(randomInt);
    setRadius(randomInt + 6 * getImageManagerThree().getUnit());
    getPaintText().setTextSize(getRadius() + 2 * getImageManagerThree().getUnit());
  }
}
