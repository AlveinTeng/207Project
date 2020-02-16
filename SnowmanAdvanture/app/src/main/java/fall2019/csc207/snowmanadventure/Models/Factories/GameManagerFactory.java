package fall2019.csc207.snowmanadventure.Models.Factories;

import android.view.SurfaceView;

import fall2019.csc207.snowmanadventure.Models.GameManagers.CatchingBallManager;
import fall2019.csc207.snowmanadventure.Models.GameManagers.FlyingSnowmanManager;
import fall2019.csc207.snowmanadventure.Models.GameManagers.GameManager;
import fall2019.csc207.snowmanadventure.Models.GameManagers.MagicCardManager;
import fall2019.csc207.snowmanadventure.Models.GameManagers.MemoryCardManager;
import fall2019.csc207.snowmanadventure.Models.GameManagers.SkyLadderManager;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Models.ImageManagers.ImageManager;

/** This is a class that produce the game manager factory */
public class GameManagerFactory extends AbstractGameFactory {

  /**
   * returns the image manager inside this game manager factory
   *
   * @param imageManager the image manager containing all bitmap images
   * @param customizedGameView the game view for this game
   * @return This returns the image manager inside this game manager factory
   */
  @Override
  public ImageManager getImageManager(
      Games imageManager, SurfaceView customizedGameView, int height, int width, int unit) {
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
    if (gameManager != null) {
      if (gameManager.equals(Games.GAME_ONE)) {
        return new SkyLadderManager(imageManager);
      } else if (gameManager.equals(Games.GAME_TWO)) {
        return new FlyingSnowmanManager(imageManager);
      } else if (gameManager.equals(Games.GAME_THREE)) {
        return new CatchingBallManager(imageManager);
      } else if (gameManager.equals(Games.GAME_FOUR)) {
        return new MagicCardManager(imageManager);
      } else if (gameManager.equals(Games.GAME_FIVE)) {
        return new MemoryCardManager(imageManager);
      }
    }
    return null;
  }
}
