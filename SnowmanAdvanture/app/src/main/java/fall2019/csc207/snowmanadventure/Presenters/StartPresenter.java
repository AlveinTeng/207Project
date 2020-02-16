package fall2019.csc207.snowmanadventure.Presenters;

import android.content.Context;
import android.util.Log;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import fall2019.csc207.snowmanadventure.DataBase.Global;
import fall2019.csc207.snowmanadventure.R;
import fall2019.csc207.snowmanadventure.Views.Login.StartView;

/** This class describes the start presenter */
public class StartPresenter extends BasePresenter {

  private StartView startView;

  public StartPresenter(StartView startView, Global global, Context context) {
    super(global, context);
    this.startView = startView;
    if (global == null) loadGlobal();
    initMusic();
  }

  /** load all data in global */
  private void loadGlobal() {
    ObjectInputStream in = null;
    String TAG_GLOBAL = "setup global";
    try {
      FileInputStream fileIn = getContext().openFileInput("database_8390.ser");
      in = new ObjectInputStream(fileIn);
      setGlobal((Global) in.readObject());
    } catch (FileNotFoundException e) {
      setGlobal(new Global());
      getGlobal().saveAll(getContext());
    } catch (ClassNotFoundException e) {
      Log.e(TAG_GLOBAL, "Class not found: " + e.toString());
    } catch (IOException e) {
      Log.e(TAG_GLOBAL, "IOException: " + e.toString());
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          Log.e(TAG_GLOBAL, "IOException: " + e.toString());
        }
      }
    }
  }

  @Override
  public void onResume() {
    getMusicManager().playGameMusic(1, false, getGlobal().getWantThug());
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    startView = null;
  }

  @Override
  public void onPause() {
    if (startView != null) {
      getGlobal().saveAll(getContext());
      getMusicManager().pauseGameMusic(1, false, getGlobal().getWantThug());
    }
  }

  @Override
  public void onBackPressed() {
    startView = null;
  }

  public void checkClick(View view) {
    super.checkClick();
    if (view.getId() == R.id.btn_signin) {
      startView.updateGlobal(getGlobal());
      startView.goToSignIn();
    } else if (view.getId() == R.id.btn_signup) {
      startView.updateGlobal(getGlobal());
      startView.goToSignUp();
    }
  }
}
