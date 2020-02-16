package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Login.SignUpView;

/** This class describes the sign up presenter */
public class SignUpPresenter extends BasePresenter {

  private SignUpView signUpView;

  public SignUpPresenter(SignUpView signUpView, Global global, Context context) {
    super(global, context);
    this.signUpView = signUpView;
  }

  @Override
  public void onResume() {}

  @Override
  public void onDestroy() {
    signUpView = null;
  }

  @Override
  public void onPause() {
    if (signUpView != null) {
      getGlobal().saveAll(getContext());
    }
  }

  @Override
  public void onBackPressed() {
    signUpView.updateGlobal(getGlobal());
    signUpView.goToStart();
  }

  public void checkClick(View view, String userName, String firstPassword, String secondPassword) {
    if (view.getId() == R.id.btn_signup_confirm) {
      checkFormat(userName, firstPassword, secondPassword);
    }
  }

  private void checkFormat(String userName, String firstPassword, String secondPassword) {
    if (userName.equals("")) {
      Toast.makeText(getContext(), "Your username is empty", Toast.LENGTH_SHORT).show();
    } else if (getGlobal().checkUserExist(userName)) {
      Toast.makeText(getContext(), "Your username already exists", Toast.LENGTH_SHORT).show();
    } else if (firstPassword.equals("") || firstPassword.length() < 6) {
      Toast.makeText(
              getContext(), "Your password should be more than 5 characters", Toast.LENGTH_SHORT)
          .show();
    } else if (secondPassword.equals("") || !secondPassword.equals(firstPassword)) {
      Toast.makeText(getContext(), "Your confirm password is incorrect", Toast.LENGTH_SHORT).show();
    } else {
      signUpSuccess(userName, firstPassword);
    }
  }

  private void signUpSuccess(String userName, String firstPassword) {
    Toast.makeText(getContext(), "sign up success", Toast.LENGTH_SHORT).show();
    getGlobal().signUp(userName, firstPassword);
    signUpView.updateGlobal(getGlobal());
    signUpView.goToStart();
  }
}
