package fall2019.csc207.snowmanadventure.Views.Game.GameThree;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Presenters.GameThreePresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.CustomizedGameViews.CatchingBallView;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameOver.GameOverMenuActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameView;

/** This is a class that describes the activity for the game three */
public class GameThreeActivity extends AppCompatActivity implements GameView {

  private Global global;

  private GameThreePresenter gameThreePresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_three);

    global = (Global) getIntent().getSerializableExtra("Global");

    gameThreePresenter = new GameThreePresenter(this, global, getApplicationContext());

    gameThreePresenter.setGameView((CatchingBallView) findViewById(R.id.catchingBallView));
  }

  @Override
  protected void onResume() {
    super.onResume();
    gameThreePresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    gameThreePresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    gameThreePresenter.setContext(getApplicationContext());
    gameThreePresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    gameThreePresenter.onBackPressed();
  }

  @Override
  public void autoSave(GameData gameData) {
    gameThreePresenter.saveGame(gameData);
  }

  @Override
  public void updateGlobal(Global global) {
    this.global = global;
  }

  @Override
  public void goToGameOver() {
    Intent intent = new Intent(this, GameOverMenuActivity.class);
    intent.putExtra("Global", global);
    intent.putExtra("Game", Games.GAME_THREE);
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
