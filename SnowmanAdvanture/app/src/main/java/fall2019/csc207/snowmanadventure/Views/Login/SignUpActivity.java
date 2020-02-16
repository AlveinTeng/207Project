package fall2019.csc207.snowmanadventure.Views.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Presenters.SignUpPresenter;
import fall2019.csc207.snowmanadventure.R;

/** This is a class that describes the activity for the sign up */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, SignUpView {

  private Global global;

  private SignUpPresenter signUpPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);

    global = (Global) getIntent().getSerializableExtra("Global");
    signUpPresenter = new SignUpPresenter(this, global, getApplicationContext());

    findViewById(R.id.btn_signup_confirm).setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    EditText et_user = findViewById(R.id.et_user);
    EditText et_password = findViewById(R.id.et_password);
    EditText et_confirm_password = findViewById(R.id.et_confirm_passowrd);
    String userName = et_user.getText().toString();
    String firstPassword = et_password.getText().toString();
    String secondPassword = et_confirm_password.getText().toString();
    signUpPresenter.setContext(getApplicationContext());
    signUpPresenter.checkClick(view, userName, firstPassword, secondPassword);
  }

  @Override
  protected void onResume() {
    super.onResume();
    signUpPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    signUpPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    signUpPresenter.setContext(getApplicationContext());
    signUpPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    signUpPresenter.onBackPressed();
  }

  @Override
  public void goToStart() {
    Intent intent = new Intent(this, StartActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void updateGlobal(Global global) {
    this.global = global;
  }
}
