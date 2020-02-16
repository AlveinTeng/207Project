package fall2019.csc207.snowmanadventure.Views.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Presenters.SignInPresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesActivity;

/** This is a class that describes the activity for the sign in */
public class SignInActivity extends AppCompatActivity implements View.OnClickListener, SignInView {

  private Global global;

  private SignInPresenter signInPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signin);

    global = (Global) getIntent().getSerializableExtra("Global");
    signInPresenter = new SignInPresenter(this, global, getApplicationContext());
    findViewById(R.id.btn_signin_confirm).setOnClickListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    signInPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    signInPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    signInPresenter.setContext(getApplicationContext());
    signInPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    signInPresenter.onBackPressed();
  }

  @Override
  public void onClick(View view) {
    EditText et_user_login = findViewById(R.id.et_user_login);
    EditText et_password_login = findViewById(R.id.et_password_login);
    String userName = et_user_login.getText().toString();
    String password = et_password_login.getText().toString();
    signInPresenter.setContext(getApplicationContext());
    signInPresenter.checkClick(view, userName, password);
  }

  @Override
  public void updateGlobal(Global global) {
    this.global = global;
  }

  @Override
  public void goToNewGames() {
    Intent intent = new Intent(this, NewGamesActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToStart() {
    Intent intent = new Intent(this, StartActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }
}
