package fall2019.csc207.snowmanadventure.Views.Game.GameMenu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Presenters.NewGamesPresenter;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Game.GameFive.GameFiveInsActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameFour.GameFourInsActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameOne.GameOneInsActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameThree.GameThreeInsActivity;
import fall2019.csc207.snowmanadventure.Views.Game.GameTwo.GameTwoInsActivity;
import fall2019.csc207.snowmanadventure.Views.Login.StartActivity;
import fall2019.csc207.snowmanadventure.Views.Reward.RewardActivity;
import fall2019.csc207.snowmanadventure.Views.ScoreBoard.ScoreBoardMenuActivity;

/** This is a class that describes the activity for the new games */
public class NewGamesActivity extends AppCompatActivity
    implements View.OnClickListener, NewGamesView {

  private Global global;

  private NewGamesPresenter newGamesPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_newgame);

    global = (Global) getIntent().getSerializableExtra("Global");
    newGamesPresenter = new NewGamesPresenter(this, global, getApplicationContext());

    findViewById(R.id.btn_game_one).setOnClickListener(this);
    findViewById(R.id.btn_game_two).setOnClickListener(this);
    findViewById(R.id.btn_game_three).setOnClickListener(this);
    findViewById(R.id.btn_game_four).setOnClickListener(this);
    findViewById(R.id.btn_game_five).setOnClickListener(this);
    findViewById(R.id.ibtn_score_board).setOnClickListener(this);
    findViewById(R.id.ibtn_rewards).setOnClickListener(this);
    findViewById(R.id.ibtn_reset).setOnClickListener(this);
    newGamesPresenter.checkDisplayReward();
  }

  public void showRewardDialog() {
    AlertDialog dialog =
        new AlertDialog.Builder(this)
            .setTitle("Congratulations on your reward!")
            .setMessage("You can take a look at the new unlocked snowman in the Reward section!")
            .setPositiveButton(
                "Ok, sounds good!",
                new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(
                            NewGamesActivity.this, "You clicked the button", Toast.LENGTH_SHORT)
                        .show();
                    dialog.dismiss();
                  }
                })
            .create();
    dialog.show();
  }

  @Override
  protected void onResume() {
    super.onResume();
    newGamesPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    newGamesPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    newGamesPresenter.setContext(getApplicationContext());
    newGamesPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    newGamesPresenter.onBackPressed();
  }

  @Override
  public void onClick(View view) {
    newGamesPresenter.checkClick(view);
  }

  @Override
  public void setWelcome(String userName) {
    TextView tv_welcome = findViewById(R.id.tv_welcome);
    tv_welcome.setText(String.format("Welcome, %s", userName));
  }

  @Override
  public void updateGlobal(Global global) {
    this.global = global;
  }

  @Override
  public void goToStart() {
    Intent intent = new Intent(this, StartActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToGameOne() {
    Intent intent = new Intent(this, GameOneInsActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToGameTwo() {
    Intent intent = new Intent(this, GameTwoInsActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToGameThree() {
    Intent intent = new Intent(this, GameThreeInsActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToGameFour() {
    Intent intent = new Intent(this, GameFourInsActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToScoreBoardMenu() {
    Intent intent = new Intent(this, ScoreBoardMenuActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToGameFive() {
    Intent intent = new Intent(this, GameFiveInsActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void goToRewards() {
    Intent intent = new Intent(this, RewardActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }

  @Override
  public void changeBackGround(int id) {
    ImageButton ibtn_reset = findViewById(R.id.ibtn_reset);
    ibtn_reset.setImageDrawable(getDrawable(id));
  }
}
