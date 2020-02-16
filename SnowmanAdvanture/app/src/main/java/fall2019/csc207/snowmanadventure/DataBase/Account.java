package fall2019.csc207.snowmanadventure.DataBase;

import java.io.Serializable;

/** The account for an user */
class Account implements Serializable {

  /** SerialVersion Id */
  private static final long serialVersionUID = -8724854026653960566L;

  /** Password of player */
  private String password;

  /**
   * Constructer an Account
   *
   * @param password the password
   */
  Account(String password) {
    this.password = password;
  }

  /**
   * @param password the password
   * @return whether the input password is correct
   */
  boolean signIn(String password) {
    return this.password.equals(password);
  }
}
