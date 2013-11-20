package eu.gluffs.samples.montyhall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Plays a number of games to figure out the win percentage.
 */
public final class GameRunner {
  private List<Game> games;
  private int wins = 0;

  /**
   * Creates a new game runner with the supplied number of games
   *
   * @param numberOfGames number of games to run.
   */
  public GameRunner(int numberOfGames) {
    games = new ArrayList<Game>(numberOfGames);

    for (int i = 0; i < numberOfGames; i++) {
      games.add(new Game(new Random().nextInt(3)));
    }
  }

  /**
   * Plays all the games in the runner.
   *
   * @param changeBox true to change box after the initial guess, false to keep the initial guess.
   */
  public void play(boolean changeBox) {
    for (Game game : games) {
      if(game.play(changeBox))
        wins++;
    }
  }

  /**
   * Return the number of wins for this runner.
   *
   * @return the number of wins.
   */
  public int getWins() {
    return this.wins;
  }

  /**
   * Returns the win percentage for the games in this runner.
   *
   * @return the percentage of wins.
   */
  public int winPercent() {
    return (int) ((float) wins / (float) games.size() * 100);
  }

  /**
   * @see Object#toString()
   */
  @Override
  public String toString() {
    return winPercent() + "% wins";
  }
}
