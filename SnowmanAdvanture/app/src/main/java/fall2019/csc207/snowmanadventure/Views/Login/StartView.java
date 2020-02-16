package fall2019.csc207.snowmanadventure.Views.Login;

import fall2019.csc207.snowmanadventure.DataBase.Global;

public interface StartView {

  void goToSignIn();

  void goToSignUp();

  void updateGlobal(Global global);
}
