package fall2019.csc207.snowmanadventure.Models.Factories;

/** This is a class that produce the abstract factory */
public class GameFactoryProducer {

  /**
   * Return the corresponding factory
   *
   * @param manager factory name
   * @return the corresponding factory
   */
  public static AbstractGameFactory getGameFactory(Factories manager) {
    if (manager.equals(Factories.IMAGE)) {
      return new ImageManagerFactory();
    } else if (manager.equals(Factories.GAME)) {
      return new GameManagerFactory();
    }
    return null;
  }
}
