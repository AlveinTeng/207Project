package fall2019.csc207.snowmanadventure.Views.Game.GameOver;

import fall2019.csc207.snowmanadventure.DataBase.Global;

public interface GameOverView {
  void goToNewGames();

  void goToGameOne();

  void goToGameTwo();

  void goToGameThree();

  void goToGameFour();

  void goToGameFive();

  void updateGlobal(Global global);

  void setBackground(int id);

  void setSnowman(int id);
}
