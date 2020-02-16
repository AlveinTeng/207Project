package fall2019.csc207.snowmanadventure.Models.Factories;

import android.view.SurfaceView;

import fall2019.csc207.snowmanadventure.Models.GameManagers.GameManager;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManagerFive;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManagerFour;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManagerOne;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManagerThree;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManagerTwo;

/** this is a class that produce the image manager */
public class ImageManagerFactory extends AbstractGameFactory {
  /**
   * Create an instance of ImageManager for the game depends on string <imageManager>.
   *
   * @param imageManager the name of the game.
   * @param customizedGameView the customizedGameView of the game.
   * @return the ImageManager for this game.
   */
  @Override
  public ImageManager getImageManager(
      Games imageManager, SurfaceView customizedGameView, int height, int width, int unit) {
    if (imageManager.equals(Games.GAME_ONE)) {
      return new ImageManagerOne(customizedGameView, height, width, unit);
    } else if (imageManager.equals(Games.GAME_TWO)) {
      return new ImageManagerTwo(customizedGameView, height, width, unit);
    } else if (imageManager.equals(Games.GAME_THREE)) {
      return new ImageManagerThree(customizedGameView, height, width, unit);
    } else if (imageManager.equals(Games.GAME_FOUR)) {
      return new ImageManagerFour(customizedGameView, height, width, unit);
    } else if (imageManager.equals(Games.GAME_FIVE)) {
      return new ImageManagerFive(customizedGameView, height, width, unit);
    }
    return null;
  }

  /**
   * returns the game manager inside this game manager factory
   *
   * @param gameManager the name for the gameManager we need
   * @param imageManager the image manager containing all the bitmaps
   * @return returns the correct gameManager we need
   */
  @Override
  public GameManager getGameManager(Games gameManager, ImageManager imageManager) {
    return null;
  }
}
