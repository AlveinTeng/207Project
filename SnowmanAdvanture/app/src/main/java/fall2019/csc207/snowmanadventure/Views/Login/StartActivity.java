package fall2019.csc207.snowmanadventure.Views.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Presenters.StartPresenter;
import fall2019.csc207.snowmanadventure.R;

/** This is a class that describes the activity for the start */
public class StartActivity extends AppCompatActivity implements View.OnClickListener, StartView {

  private Global global;

  private StartPresenter startPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    global = (Global) getIntent().getSerializableExtra("Global");
    startPresenter = new StartPresenter(this, global, getApplicationContext());

    findViewById(R.id.btn_signin).setOnClickListener(this);
    findViewById(R.id.btn_signup).setOnClickListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    startPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    startPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    startPresenter.setContext(getApplicationContext());
    startPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    startPresenter.onBackPressed();
  }

  @Override
  public void goToSignIn() {
    Intent intent = new Intent(this, SignInActivity.class);
    intent = intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToSignUp() {
    Intent intent = new Intent(this, SignUpActivity.class);
    intent = intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void updateGlobal(Global global) {
    this.global = global;
  }

  @Override
  public void onClick(View view) {
    startPresenter.checkClick(view);
  }
}
