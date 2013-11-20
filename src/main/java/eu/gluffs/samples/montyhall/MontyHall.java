package eu.gluffs.samples.montyhall;

public final class MontyHall {
  private static final int GAMES_PER_TEST = 1000;

  private GameRunner change;
  private GameRunner noChange;

  private MontyHall() {
    change = new GameRunner(GAMES_PER_TEST);
    noChange = new GameRunner(GAMES_PER_TEST);
  }

  public static void main(String[] args) {
    MontyHall montyHall = new MontyHall();
    montyHall.play();
    montyHall.printResult();
  }

  private void play() {
    change.play(true);
    noChange.play(false);
  }

  private void printResult() {
    StringBuilder sb = new StringBuilder();
    sb.append("Changing box is ");

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
}
