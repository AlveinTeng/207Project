package fall2019.csc207.snowmanadventure.Views.Game.GameOne;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Presenters.GameOnePresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.CustomizedGameViews.SkyLadderView;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameOver.GameOverMenuActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameView;

/** This is a class that describes the activity for the game one */
public class GameOneActivity extends AppCompatActivity implements GameView {

  private Global global;

  private GameOnePresenter gameOnePresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_one);

    global = (Global) getIntent().getSerializableExtra("Global");

    gameOnePresenter = new GameOnePresenter(this, global, getApplicationContext());

    gameOnePresenter.setGameView((SkyLadderView) findViewById(R.id.skyLadderView));
  }

  @Override
  protected void onResume() {
    super.onResume();
    gameOnePresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    gameOnePresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    gameOnePresenter.setContext(getApplicationContext());
    gameOnePresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    gameOnePresenter.onBackPressed();
  }

  @Override
  public void autoSave(GameData gameData) {
    gameOnePresenter.saveGame(gameData);
  }

  @Override
  public void goToGameOver() {
    Intent intent = new Intent(this, GameOverMenuActivity.class);
    intent.putExtra("Global", global);
    intent.putExtra("Game", Games.GAME_ONE);
    startActivity(intent);
    finish();
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
}
