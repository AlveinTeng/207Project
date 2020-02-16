package fall2019.csc207.snowmanadventure.Views.Game.GameFive;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Presenters.GameFivePresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.CustomizedGameViews.MemoryCardView;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameOver.GameOverMenuActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameView;

/** This is a class that describes the activity for the game five */
public class GameFiveActivity extends AppCompatActivity implements GameView {

  /** the global object */
  private Global global;

  /** the game five presenter */
  private GameFivePresenter gameFivePresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_five);

    global = (Global) getIntent().getSerializableExtra("Global");

    gameFivePresenter = new GameFivePresenter(this, global, getApplicationContext());

    gameFivePresenter.setGameView((MemoryCardView) findViewById(R.id.memoryCardView));
  }

  @Override
  protected void onResume() {
    super.onResume();
    gameFivePresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    gameFivePresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    gameFivePresenter.setContext(getApplicationContext());
    gameFivePresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    gameFivePresenter.onBackPressed();
  }

  @Override
  public void updateGlobal(Global global) {
    this.global = global;
  }

  @Override
  public void goToGameOver() {
    Intent intent = new Intent(this, GameOverMenuActivity.class);
    intent.putExtra("Global", global);
    intent.putExtra("Game", Games.GAME_FIVE);
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

  @Override
  public void autoSave(GameData gameData) {
    gameFivePresenter.saveGame(gameData);
  }
}
