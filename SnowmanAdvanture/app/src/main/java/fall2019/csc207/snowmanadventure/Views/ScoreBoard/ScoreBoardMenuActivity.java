package fall2019.csc207.snowmanadventure.Views.ScoreBoard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Presenters.ScoreBoardMenuPresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameMenu.NewGamesActivity;

/** This class describes the score board menu activity */
public class ScoreBoardMenuActivity extends AppCompatActivity
    implements View.OnClickListener, ScoreBoardMenuView {

  private Global global;

  private ScoreBoardMenuPresenter scoreBoardMenuPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_scoreboard_menu);

    global = (Global) getIntent().getSerializableExtra("Global");
    scoreBoardMenuPresenter = new ScoreBoardMenuPresenter(this, global, getApplicationContext());

    findViewById(R.id.game_one_btn).setOnClickListener(this);
    findViewById(R.id.game_two_btn).setOnClickListener(this);
    findViewById(R.id.game_three_btn).setOnClickListener(this);
    findViewById(R.id.game_four_btn).setOnClickListener(this);
    findViewById(R.id.game_five_btn).setOnClickListener(this);
    findViewById(R.id.game_total_btn).setOnClickListener(this);
    findViewById(R.id.display_name_switch).setOnClickListener(this);
    findViewById(R.id.display_scores_switch).setOnClickListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    scoreBoardMenuPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    scoreBoardMenuPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    scoreBoardMenuPresenter.setContext(getApplicationContext());
    scoreBoardMenuPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    scoreBoardMenuPresenter.onBackPressed();
  }

  @Override
  public void onClick(View view) {
    scoreBoardMenuPresenter.checkClick(view);
  }

  @Override
  public void updateGlobal(Global global) {
    this.global = global;
  }

  @Override
  public void goToGameOne() {
    Intent intent = new Intent(this, ScoreBoardActivity.class);
    intent.putExtra("Global", global);
    intent.putExtra("ScoreBoardType", Games.GAME_ONE);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToGameTwo() {
    Intent intent = new Intent(this, ScoreBoardActivity.class);
    intent.putExtra("Global", global);
    intent.putExtra("ScoreBoardType", Games.GAME_TWO);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToGameThree() {
    Intent intent = new Intent(this, ScoreBoardActivity.class);
    intent.putExtra("Global", global);
    intent.putExtra("ScoreBoardType", Games.GAME_THREE);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToGameFour() {
    Intent intent = new Intent(this, ScoreBoardActivity.class);
    intent.putExtra("Global", global);
    intent.putExtra("ScoreBoardType", Games.GAME_FOUR);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToGameFive() {
    Intent intent = new Intent(this, ScoreBoardActivity.class);
    intent.putExtra("Global", global);
    intent.putExtra("ScoreBoardType", Games.GAME_FIVE);
    startActivity(intent);
    finish();
  }

  @Override
  public void setNameSwitch(boolean saveName) {
    Switch nameSwitch = findViewById(R.id.display_name_switch);
    nameSwitch.setChecked(saveName);
  }

  @Override
  public void setScoreSwitch(boolean saveScore) {
    Switch scoreSwitch = findViewById(R.id.display_scores_switch);
    scoreSwitch.setChecked(saveScore);
  }

  @Override
  public void goToTotalBoard() {
    Intent intent = new Intent(this, ScoreBoardActivity.class);
    intent.putExtra("Global", global);
    intent.putExtra("ScoreBoardType", Games.TOTAL_GAMES);
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
