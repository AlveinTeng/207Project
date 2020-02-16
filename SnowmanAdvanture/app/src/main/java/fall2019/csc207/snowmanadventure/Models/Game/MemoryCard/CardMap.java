package fall2019.csc207.snowmanadventure.Models.Game.MemoryCard;

import java.util.HashMap;

/** Use the singleton design pattern for the card map */
public class CardMap {

  private static CardMap cardMap = new CardMap();

  private HashMap<Integer, Integer> map;

  private CardMap() {
    map = new HashMap<>();
    map.put(1, 1);
    map.put(2, 2);
    map.put(3, 3);
    map.put(4, 4);
    map.put(5, 5);
    map.put(6, 6);
    map.put(7, 5);
    map.put(8, 4);
    map.put(9, 6);
    map.put(10, 3);
    map.put(11, 1);
    map.put(12, 2);
  }

  public static CardMap getInstance() {
    return cardMap;
  }

  public HashMap<Integer, Integer> initMap() {
    return map;
  }
}
