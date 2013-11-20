package eu.gluffs.samples.montyhall;

public final class MontyHall {
  /**
   * Number of games to run per game runner
   */
  private static final int GAMES_PER_RUNNER = 1000;

  private GameRunner change;
  private GameRunner noChange;

  /**
   * Sets up game runners for this test.
   */
  private MontyHall() {
    change = new GameRunner(GAMES_PER_RUNNER);
    noChange = new GameRunner(GAMES_PER_RUNNER);
  }

  /**
   * Runs the test.
   */
  private void play() {
    change.play(true);
    noChange.play(false);
  }

  /**
   * Prints the test result.
   */
  private void printResult() {
    StringBuilder sb = new StringBuilder();
    sb.append("Result: Changing box is ");

    if (change.getWins() > noChange.getWins()) {
      sb.append("better. ");
      sb.append(change.toString()).append(" over ").append(noChange.winPercent());
    }
    else {
      sb.append("worse. ");
      sb.append(noChange.toString()).append(" over ").append(change.winPercent());
    }

    sb.append("% wins.");

    System.out.println(sb.toString());
  }

  /**
   * Starts the test.
   *
   * @param args accepts no parameters
   */
  public static void main(String[] args) {
    MontyHall montyHall = new MontyHall();
    montyHall.play();
    montyHall.printResult();
  }
}
