package fall2019.csc207.snowmanadventure.Models.GameMenu;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import fall2019.csc207.snowmanadventure.Views.CustomizedGameViews.CustomizedGameView;

/** All the customize game view use this thread */
public class GameThread extends Thread {

  /** the corresponding game view */
  private CustomizedGameView view;

  /** the surface holder */
  private SurfaceHolder holder;

  /** whether the thread is running */
  private boolean isRunning = true;

  public GameThread(CustomizedGameView customizedGameView) {
    this.view = customizedGameView;
    holder = view.getHolder();
  }

  /** run the thread */
  @Override
  public void run() {

    Canvas canvas;

    while (isRunning) {
      canvas = null;
      try {
        canvas = holder.lockCanvas();
        synchronized (holder) {
          view.update();
          view.draw(canvas);
        }
      } finally {
        if (canvas != null) {
          holder.unlockCanvasAndPost(canvas);
        }
      }
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /** end the thread */
  public void end() {
    isRunning = false;
  }
}
