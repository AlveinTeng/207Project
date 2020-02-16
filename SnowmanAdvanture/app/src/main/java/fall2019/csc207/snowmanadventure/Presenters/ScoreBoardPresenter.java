package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Models.GameMenu.Games;
import fall2019.csc207.snowmanadventure.Models.ScoreBoard.ScoreBoard;
import fall2019.csc207.snowmanadventure.Models.ScoreBoard.TotalScoreBoard;
import fall2019.csc207.snowmanadventure.Views.ScoreBoard.ScoreBoardView;

/** This class describes the score board presenter */
public class ScoreBoardPresenter extends BasePresenter {

  private ScoreBoardView scoreBoardView;

  private ScoreBoard scoreBoard;

  private LinearLayout canvas;

  private boolean isTotal = false;

  public ScoreBoardPresenter(ScoreBoardView scoreBoardView, Global global, Context context) {
    super(global, context);
    this.scoreBoardView = scoreBoardView;
  }

  public void setBoardType(Games boardType) {
    switch (boardType) {
      case TOTAL_GAMES:
        isTotal = true;
        scoreBoard = new TotalScoreBoard(getGlobal(), 0);
        break;
      case GAME_ONE:
        scoreBoard = new ScoreBoard(getGlobal(), 1);
        break;
      case GAME_TWO:
        scoreBoard = new ScoreBoard(getGlobal(), 2);
        break;
      case GAME_THREE:
        scoreBoard = new ScoreBoard(getGlobal(), 3);
        break;
      case GAME_FOUR:
        scoreBoard = new ScoreBoard(getGlobal(), 4);
        break;
      case GAME_FIVE:
        scoreBoard = new ScoreBoard(getGlobal(), 5);
        break;
      default:
        break;
    }
  }

  public void setTitle(TextView textView) {
    if (isTotal) {
      textView.setText(String.format("%s score ranking", "Total"));
    }
  }

  public void setCanvas(LinearLayout canvas) {
    this.canvas = canvas;
    displayPlayerScore();
  }

  private void displayPlayerScore() {
    for (int i = 0; i < scoreBoard.getPlayers().size(); i++) {
      scoreColumnView(i);
    }
  }

  private void scoreColumnView(int i) {
    LinearLayout ll = new LinearLayout(getContext());
    ll.setOrientation(LinearLayout.HORIZONTAL);

    TextView rank = new TextView(getContext());
    TextView name = new TextView(getContext());
    TextView score = new TextView(getContext());

    generateTextView(rank, Integer.toString(i + 1));
    generateTextView(name, scoreBoard.getPlayers().get(i));
    generateTextView(score, Integer.toString(scoreBoard.getScores().get(i)));

    ll.addView(rank);
    ll.addView(name);
    ll.addView(score);

    canvas.addView(ll);
  }

  private void generateTextView(TextView view, String text) {
    LinearLayout.LayoutParams param =
        new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
    param.setMargins(0, 20, 0, 0);
    view.setText(text);
    view.setTextSize(24);
    view.setTextColor(Color.WHITE);
    view.setGravity(Gravity.CENTER);
    view.setLayoutParams(param);
  }

  @Override
  public void onResume() {}

  @Override
  public void onDestroy() {
    scoreBoardView = null;
  }

  @Override
  public void onPause() {
    if (scoreBoardView != null) {
      getGlobal().saveAll(getContext());
    }
  }

  @Override
  public void onBackPressed() {
    scoreBoardView.updateGlobal(getGlobal());
    scoreBoardView.goToScoreBoardMenu();
  }
}
