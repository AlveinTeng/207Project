package fall2019.csc207.snowmanadventure.Views.Login;

import fall2019.csc207.snowmanadventure.DataBase.Global;

public interface SignInView {

  void updateGlobal(Global global);

  void goToNewGames();

  void goToStart();
}
