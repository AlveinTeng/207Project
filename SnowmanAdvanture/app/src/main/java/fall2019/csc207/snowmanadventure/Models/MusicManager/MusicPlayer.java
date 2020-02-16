package fall2019.csc207.snowmanadventure.Models.MusicManager;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This class describes the music player */
class MusicPlayer {

  /**
   * The hash map for the musics, the key is the music's res id, and the value is the MediaPlayer
   */
  private Map<Integer, MediaPlayer> musics = new HashMap<>();

  /** The SoundPool for the sound */
  private SoundPool soundPool;

  /** The sounds */
  private SparseIntArray sounds = new SparseIntArray();

  /** The current context */
  private Context context;

  MusicPlayer(Context context) {
    this.context = context;
    initMusic();
  }

  private void initMusic() {
    createSoundPool();
  }

  /** create the SoundPool */
  private void createSoundPool() {
    AudioAttributes attr =
        new AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build();
    soundPool = new SoundPool.Builder().setAudioAttributes(attr).build();
  }

  /**
   * load the sound
   *
   * @param id the res id for the sound
   */
  void loadSound(int id) {
    sounds.put(id, soundPool.load(context, id, 1));
  }

  /**
   * play the sound
   *
   * @param soundId the res id for the sound
   */
  void playSound(int soundId) {
    soundPool.play(sounds.get(soundId), 1, 1, 0, 0, 1);
  }

  /**
   * load the music
   *
   * @param id the res id for the music
   */
  void loadMusic(int id) {
    if (!musics.containsKey(id)) {
      musics.put(id, MediaPlayer.create(context, id));
    }
  }

  /**
   * play the music
   *
   * @param musicId the res id for the music
   */
  void playMusic(int musicId) {
    MediaPlayer mediaPlayer = musics.get(musicId);

    if (mediaPlayer == null) {
      return;
    }
    mediaPlayer.start();
  }

  /**
   * pause the music
   *
   * @param musicId the res id for the music
   */
  void pauseMusic(int musicId) {
    MediaPlayer mediaPlayer = musics.get(musicId);
    if (mediaPlayer == null) {
      return;
    }
    mediaPlayer.pause();
  }

  /**
   * release the music
   *
   * @param musicId the res id for the music
   */
  void releaseMusic(int musicId) {
    MediaPlayer mediaPlayer = musics.get(musicId);
    if (mediaPlayer == null) {
      return;
    }
    mediaPlayer.reset();
    mediaPlayer.release();
  }

  /** release all the music */
  void releaseAllMusic() {
    List<Integer> musicList = new ArrayList<>(musics.keySet());
    for (Integer index : musicList) {
      releaseMusic(index);
    }
  }

  /**
   * loop the music
   *
   * @param musicId the res id for the music
   */
  void loopMusic(int musicId) {
    MediaPlayer mediaPlayer = musics.get(musicId);
    if (mediaPlayer == null) {
      return;
    }
    mediaPlayer.setLooping(true);
  }
}
