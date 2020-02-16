package fall2019.csc207.snowmanadventure.Views.Game.GameFive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Presenters.GameFiveInsPresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesActivity;

/** This is a class that describes the game five instruction activity */
public class GameFiveInsActivity extends AppCompatActivity
    implements View.OnClickListener, GameFiveInsView {

  private Global global;

  private GameFiveInsPresenter gameFiveInsPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_instruction_five);

    global = (Global) getIntent().getSerializableExtra("Global");
    gameFiveInsPresenter = new GameFiveInsPresenter(this, global, getApplicationContext());

    findViewById(R.id.btn_got_it5).setOnClickListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    gameFiveInsPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    gameFiveInsPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    gameFiveInsPresenter.setContext(getApplicationContext());
    gameFiveInsPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    gameFiveInsPresenter.onBackPressed();
  }

  @Override
  public void onClick(View view) {
    gameFiveInsPresenter.checkClick(view);
  }

  @Override
  public void updateGlobal(Global global) {
    this.global = global;
  }

  @Override
  public void goToGameFive() {
    Intent intent = new Intent(this, GameFiveActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToNewGames() {
    Intent intent = new Intent(this, NewGamesActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }
}
