package eu.gluffs.samples.montyhall;

/**
 * A monty Hall game that can be played with or without changing box after the initial guess.
 */
public final class Game {

  private boolean[] boxes = {false, true, false};
  private int guess;

  /**
   * Creates a new Game and records the first guess.
   *
   * @param initialGuess the box number to guess for 0-2.
   */
  public Game(int initialGuess) {
    this.guess = initialGuess;
  }

  /**
   * Plays the game.
   *
   * @param changeBox whether to change box after the initial guess.
   * @return true for a win, false otherwise.
   */
  public boolean play(boolean changeBox) {
    if (changeBox)
      changeBox();

    return isCorrect();
  }

  /**
   * Changes the guess to another box.
   */
  public void changeBox() {
    if (guess == 1)
      guess = 0;
    else
      guess = 1;
  }

  /**
   * Checks whether the guess was correct.
   *
   * @return true for a correct guess & false for an incorrect guess.
   */
  public boolean isCorrect() {
    return boxes[guess];
  }
}
