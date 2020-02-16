package fall2019.csc207.snowmanadventure.Views.ScoreBoard;

import fall2019.csc207.snowmanadventure.DataBase.Global;

public interface ScoreBoardMenuView {

  void updateGlobal(Global global);

  void goToNewGames();

  void goToGameOne();

  void goToGameTwo();

  void goToGameThree();

  void goToTotalBoard();

  void goToGameFour();

  void goToGameFive();

  void setNameSwitch(boolean saveName);

  void setScoreSwitch(boolean saveScore);
}
