package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.Models.MusicManager.MusicManager;

/** This class describes the base presenter */
public abstract class BasePresenter {

  private Context context;

  private Global global;

  private MusicManager musicManager;

  public BasePresenter(Global global, Context context) {
    this.global = global;
    this.context = context;
  }

  /** initialize the music player */
  public void initMusic() {
    musicManager = new MusicManager(context);
  }

  public Global getGlobal() {
    return global;
  }

  public void setGlobal(Global global) {
    this.global = global;
  }

  public Context getContext() {
    return context;
  }

  public void setContext(Context context) {
    this.context = context;
  }

  MusicManager getMusicManager() {
    return musicManager;
  }

  public abstract void onResume();

  /** stop and release the media player */
  public void onDestroy() {
    musicManager.stop();
    musicManager = null;
  }

  public abstract void onPause();

  public abstract void onBackPressed();

  /** play the click sound */
  public void checkClick() {
    musicManager.playSoundClick();
  }
}
