package fall2019.csc207.snowmanadventure.Views.Game.GameFour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.GameData;
import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Presenters.GameFourPresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.CustomizedGameViews.MagicCardView;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameOver.GameOverMenuActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameView;

/** This is a class that describes the activity for the game five */
public class GameFourActivity extends AppCompatActivity implements View.OnClickListener, GameView {

  private Global global;

  private GameFourPresenter gameFourPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_four);

    global = (Global) getIntent().getSerializableExtra("Global");

    gameFourPresenter = new GameFourPresenter(this, global, getApplicationContext());

    gameFourPresenter.setGameView((MagicCardView) findViewById(R.id.magicCardView));

    findViewById(R.id.btn_hit).setOnClickListener(this);
    findViewById(R.id.btn_stand).setOnClickListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    gameFourPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    gameFourPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    gameFourPresenter.setContext(getApplicationContext());
    gameFourPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    gameFourPresenter.onBackPressed();
  }

  @Override
  public void onClick(View view) {
    gameFourPresenter.checkClick(view);
  }

  @Override
  public void autoSave(GameData gameData) {
    gameFourPresenter.saveGame(gameData);
  }

  @Override
  public void updateGlobal(Global global) {
    this.global = global;
  }

  @Override
  public void goToGameOver() {
    Intent intent = new Intent(this, GameOverMenuActivity.class);
    intent.putExtra("Global", global);
    intent.putExtra("Game", Games.GAME_FOUR);
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
