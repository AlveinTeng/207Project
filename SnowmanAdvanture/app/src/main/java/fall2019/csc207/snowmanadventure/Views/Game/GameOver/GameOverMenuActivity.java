package fall2019.csc207.snowmanadventure.Views.Game.GameOver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Presenters.GameOverPresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameFive.GameFiveActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameFour.GameFourActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameOne.GameOneActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameThree.GameThreeActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameTwo.GameTwoActivity;

/** This is a class that describes the activity for the game over menu */
public class GameOverMenuActivity extends AppCompatActivity
    implements View.OnClickListener, GameOverView {

  private Global global;

  private GameOverPresenter gameOverPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_over);
    global = (Global) getIntent().getSerializableExtra("Global");
    Games game = (Games) getIntent().getSerializableExtra("Game");
    gameOverPresenter = new GameOverPresenter(this, global, this);
    gameOverPresenter.setGame(game);
    gameOverPresenter.setBackground();
    gameOverPresenter.setSnowman();

    findViewById(R.id.btn_yes).setOnClickListener(this);
    findViewById(R.id.btn_no).setOnClickListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    gameOverPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    gameOverPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    gameOverPresenter.setContext(getApplicationContext());
    gameOverPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    gameOverPresenter.onBackPressed();
  }

  @Override
  public void onClick(View view) {
    gameOverPresenter.checkClick(view);
  }

  @Override
  public void goToGameOne() {
    Intent intent = new Intent(this, GameOneActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToGameTwo() {
    Intent intent = new Intent(this, GameTwoActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToGameThree() {
    Intent intent = new Intent(this, GameThreeActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToGameFour() {
    Intent intent = new Intent(this, GameFourActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToGameFive() {
    Intent intent = new Intent(this, GameFiveActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void updateGlobal(Global global) {
    this.global = global;
  }

  @Override
  public void setBackground(int id) {
    ImageView iv_background = findViewById(R.id.iv_background);
    iv_background.setImageResource(id);
  }

  @Override
  public void setSnowman(int id) {
    ImageView iv_snowman = findViewById(R.id.iv_snowman);
    iv_snowman.setImageResource(id);
  }

  @Override
  public void goToNewGames() {
    Intent intent = new Intent(this, NewGamesActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }
}
