package eu.gluffs.samples.montyhall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameRunner {
  private List<Game> games;
  private int wins = 0;

  public GameRunner(int numberOfGames) {
    games = new ArrayList<Game>(numberOfGames);

    for (int i = 0; i < numberOfGames; i++) {
      games.add(new Game(new Random().nextInt(3)));
    }
  }

  public void play(boolean changeBox) {
    for (Game game : games) {
      if(changeBox)
        game.changeBox();

      if(game.isCorrect())
        wins++;
    }
  }

  public int getWins() {
    return this.wins;
  }

  public int winPercent() {
    return (int) ((float) wins / (float) games.size() * 100);
  }

  public String toString() {
    return winPercent() + "% wins";
  }
}
