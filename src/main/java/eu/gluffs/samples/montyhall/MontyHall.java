package eu.gluffs.samples.montyhall;

public final class MontyHall {

  private MontyHall() {}

  public static void main(String[] args) {
    int total = 1000;

    GameRunner change = new GameRunner(total);
    GameRunner noChange = new GameRunner(total);

    change.play(true);
    noChange.play(false);

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
