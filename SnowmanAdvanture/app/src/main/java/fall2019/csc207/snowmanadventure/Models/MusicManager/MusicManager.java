package fall2019.csc207.snowmanadventure.Models.MusicManager;

import android.content.Context;
import android.util.SparseIntArray;

import fall2019.csc207.snowmanadventure.R;

/** This class describes the music manager */
public class MusicManager {

  /** the music player */
  private MusicPlayer musicPlayer;

  /** The list for the music of the games */
  private SparseIntArray gameMusicList = new SparseIntArray();

  /** The list for the music of the menu */
  private SparseIntArray menuMusicList = new SparseIntArray();

  /** Style one music index */
  private Integer indexStyleOne = 0;

  /** Style two music index */
  private Integer indexStyleTwo = 0;

  public MusicManager(Context context) {
    musicPlayer = new MusicPlayer(context);
    loadMusicAndSound();
  }

  private void loadMusicAndSound() {
    musicPlayer.loadSound(R.raw.raindrop);

    loadGameMusicStyleOne(R.raw.jingle_bell, false);
    loadGameMusicStyleOne(R.raw.bamboo, false);
    loadGameMusicStyleTwo(R.raw.menu_one_thug, false);
    loadGameMusicStyleTwo(R.raw.gameonemusic, false);
    indexStyleOne -= indexStyleOne;
    indexStyleTwo -= indexStyleTwo;
    loadGameMusicStyleOne(R.raw.gameonemusic, true);
    loadGameMusicStyleOne(R.raw.gametwomusic, true);
    loadGameMusicStyleOne(R.raw.gamethreemusic, true);
    loadGameMusicStyleOne(R.raw.gamefourmusic, true);
    loadGameMusicStyleOne(R.raw.gamefivemusic, true);
    loadGameMusicStyleTwo(R.raw.gameonethug, true);
    loadGameMusicStyleTwo(R.raw.gametwothug, true);
    loadGameMusicStyleTwo(R.raw.gamethreethug, true);
    loadGameMusicStyleTwo(R.raw.gamefourthug, true);
    loadGameMusicStyleTwo(R.raw.choice2, true);
  }

  /**
   * load the style one music to the media player
   *
   * @param music music res id
   * @param isGame whether the music is for game
   */
  private void loadGameMusicStyleOne(Integer music, boolean isGame) {
    indexStyleOne += 1;
    musicPlayer.loadMusic(music);
    musicPlayer.loopMusic(music);
    if (isGame) {
      gameMusicList.put(indexStyleOne, music);
    } else {
      menuMusicList.put(indexStyleOne, music);
    }
  }

  /**
   * load the style two music to the media player
   *
   * @param music music res id
   * @param isGame whether the music is for game
   */
  private void loadGameMusicStyleTwo(Integer music, boolean isGame) {
    indexStyleTwo -= 1;
    musicPlayer.loadMusic(music);
    musicPlayer.loopMusic(music);
    if (isGame) {
      gameMusicList.put(indexStyleTwo, music);
    } else {
      menuMusicList.put(indexStyleTwo, music);
    }
  }

  /** play the sound */
  public void playSoundClick() {
    musicPlayer.playSound(R.raw.raindrop);
  }

  /**
   * Play the music
   *
   * @param index music index
   * @param isGame whether is for game
   * @param isStyleTwo whether the game is style two
   */
  public void playGameMusic(Integer index, boolean isGame, boolean isStyleTwo) {
    Integer currIndex = checkStyle(index, isStyleTwo);
    if (isGame) {
      musicPlayer.playMusic(gameMusicList.get(currIndex));
    } else {
      musicPlayer.playMusic(menuMusicList.get(currIndex));
    }
  }

  /**
   * Return the negative number if need the style two music, otherwise return the positive number
   *
   * @param index the music index
   * @param isStyleTwo whether the game is style two
   * @return Return the negative number if need the style two music, otherwise return the positive
   *     number
   */
  private Integer checkStyle(Integer index, boolean isStyleTwo) {
    return (isStyleTwo) ? -index : index;
  }

  /**
   * Pause the music
   *
   * @param index music index
   * @param isGame whether is for game
   * @param isStyleTwo whether the game is style two
   */
  public void pauseGameMusic(Integer index, boolean isGame, boolean isStyleTwo) {
    Integer currIndex = checkStyle(index, isStyleTwo);
    if (isGame) {
      musicPlayer.pauseMusic(gameMusicList.get(currIndex));
    } else {
      musicPlayer.pauseMusic(menuMusicList.get(currIndex));
    }
  }

  /** Stop and release all the music */
  public void stop() {
    musicPlayer.releaseAllMusic();
    musicPlayer = null;
  }
}
