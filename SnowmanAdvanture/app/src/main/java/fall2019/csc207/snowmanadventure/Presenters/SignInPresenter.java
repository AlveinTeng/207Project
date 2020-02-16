package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Login.SignInView;

/** This class describes the sign in presenter */
public class SignInPresenter extends BasePresenter {

  private SignInView signInView;

  public SignInPresenter(SignInView signInView, Global global, Context context) {
    super(global, context);
    this.signInView = signInView;
  }

  @Override
  public void onResume() {}

  @Override
  public void onDestroy() {
    signInView = null;
  }

  @Override
  public void onPause() {
    if (signInView != null) {
      getGlobal().saveAll(getContext());
    }
  }

  @Override
  public void onBackPressed() {
    signInView.updateGlobal(getGlobal());
    signInView.goToStart();
  }

  public void checkClick(View view, String userName, String password) {
    if (view.getId() == R.id.btn_signin_confirm) {
      checkFormat(userName, password);
    }
  }

  private void checkFormat(String userName, String password) {
    if (userName.equals("") || !getGlobal().checkUserExist(userName)) {
      Toast.makeText(getContext(), "Your username is incorrect", Toast.LENGTH_SHORT).show();
    } else if (password.equals("") || !getGlobal().signIn(userName, password)) {
      Toast.makeText(getContext(), "Your password is incorrect", Toast.LENGTH_SHORT).show();
    } else {
      signInSuccess();
    }
  }

  private void signInSuccess() {
    Toast.makeText(getContext(), "sign in success", Toast.LENGTH_SHORT).show();
    signInView.updateGlobal(getGlobal());
    signInView.goToNewGames();
  }
}
