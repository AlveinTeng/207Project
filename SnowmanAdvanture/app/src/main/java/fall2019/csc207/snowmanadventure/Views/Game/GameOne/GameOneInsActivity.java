package fall2019.csc207.snowmanadventure.Views.Game.GameOne;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Presenters.GameOneInsPresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesActivity;

/** This is a class that describes the activity for the game one instruction */
public class GameOneInsActivity extends AppCompatActivity
    implements View.OnClickListener, GameOneInsView {

  private Global global;

  private GameOneInsPresenter gameOneInsPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_instruction_one);

    global = (Global) getIntent().getSerializableExtra("Global");
    gameOneInsPresenter = new GameOneInsPresenter(this, global, getApplicationContext());

    findViewById(R.id.btn_got_it).setOnClickListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    gameOneInsPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    gameOneInsPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    gameOneInsPresenter.setContext(getApplicationContext());
    gameOneInsPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    gameOneInsPresenter.onBackPressed();
  }

  @Override
  public void onClick(View view) {
    gameOneInsPresenter.checkClick(view);
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
  public void goToGameOne() {
    Intent intent = new Intent(this, GameOneActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }
}
