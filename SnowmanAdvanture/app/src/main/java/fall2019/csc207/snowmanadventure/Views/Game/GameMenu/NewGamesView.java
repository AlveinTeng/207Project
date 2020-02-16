package fall2019.csc207.snowmanadventure.Views.Game.GameMenu;

import fall2019.csc207.snowmanadventure.DataBase.Global;

/** this is an interface for the new games activity */
public interface NewGamesView {

  void setWelcome(String userName);

  void updateGlobal(Global global);

  void goToStart();

  void goToGameOne();

  void goToGameTwo();

  void goToGameThree();

  void goToGameFour();

  void goToGameFive();

  void goToScoreBoardMenu();

  void goToRewards();

  void showRewardDialog();

  void changeBackGround(int id);
}
