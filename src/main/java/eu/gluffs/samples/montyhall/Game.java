package eu.gluffs.samples.montyhall;

public class Game {
  private boolean[] boxes = {false, true, false};
  private int guess;

  public Game(int guess) {
    this.guess = guess;
  }

  public void changeBox() {
    if (guess == 1)
      guess = 0;
    else
      guess = 1;
  }

  public boolean isCorrect() {
    return boxes[guess];
  }
}
