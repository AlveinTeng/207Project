package fall2019.csc207.snowmanadventure.Views.ScoreBoard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Presenters.ScoreBoardPresenter;
import fall2019.csc207.snowmanadventure.R;

/** This class describes the score board activity */
public class ScoreBoardActivity extends AppCompatActivity implements ScoreBoardView {
  private Global global;

  private ScoreBoardPresenter scoreBoardPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_scoreboard);
    global = (Global) getIntent().getSerializableExtra("Global");
    Games boardType = (Games) getIntent().getSerializableExtra("ScoreBoardType");
    scoreBoardPresenter = new ScoreBoardPresenter(this, global, getApplicationContext());
    assert boardType != null;
    scoreBoardPresenter.setBoardType(boardType);
    scoreBoardPresenter.setTitle((TextView) findViewById(R.id.score_board_title));
    scoreBoardPresenter.setCanvas((LinearLayout) findViewById(R.id.score_board_canvas));
  }

  @Override
  protected void onResume() {
    super.onResume();
    scoreBoardPresenter.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    scoreBoardPresenter.onDestroy();
  }

  @Override
  protected void onPause() {
    super.onPause();
    scoreBoardPresenter.setContext(getApplicationContext());
    scoreBoardPresenter.onPause();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    scoreBoardPresenter.onBackPressed();
  }

  @Override
  public void updateGlobal(Global global) {
    this.global = global;
  }

  @Override
  public void goToScoreBoardMenu() {
    Intent intent = new Intent(this, ScoreBoardMenuActivity.class);
    intent.putExtra("Global", global);
    startActivity(intent);
    finish();
  }
}
