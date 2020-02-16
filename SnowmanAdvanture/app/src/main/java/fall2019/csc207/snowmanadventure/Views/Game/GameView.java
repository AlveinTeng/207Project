package fall2019.csc207.snowmanadventure.Views.Game;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.DataBase.Global;

/** This is an interface for the game view */
public interface GameView {
  void updateGlobal(Global global);

  void goToNewGames();

  void autoSave(GameData gameData);

  void goToGameOver();
}
