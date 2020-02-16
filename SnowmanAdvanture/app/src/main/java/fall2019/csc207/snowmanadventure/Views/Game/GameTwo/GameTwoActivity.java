package fall2019.csc207.snowmanadventure.Views.Game.GameTwo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Presenters.GameTwoPresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.CustomizedGameViews.FlyingSnowmanView;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameOver.GameOverMenuActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameView;

/** This is a class that describes the activity for the game two */
public class GameTwoActivity extends AppCompatActivity implements GameView {

  private Global global;

  private GameTwoPresenter gameTwoPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_two);

    global = (Global) getIntent().getSerializableExtra("Global");
    gameTwoPresenter = new GameTwoPresenter(this, global, getApplicationContext());

    gameTwoPresenter.setGameView((FlyingSnowmanView) findViewById(R.id.flyingSnowmanView));
  }

  @Override
  protected void onResume() {
    super.onResume();
    gameTwoPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    gameTwoPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    gameTwoPresenter.setContext(getApplicationContext());
    gameTwoPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    gameTwoPresenter.onBackPressed();
  }

  public void autoSave(GameData gameData) {
    gameTwoPresenter.saveGame(gameData);
  }

  @Override
  public void updateGlobal(Global global) {
    this.global = global;
  }

  @Override
  public void goToGameOver() {
    Intent intent = new Intent(this, GameOverMenuActivity.class);
    intent.putExtra("Global", global);
    intent.putExtra("Game", Games.GAME_TWO);
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
