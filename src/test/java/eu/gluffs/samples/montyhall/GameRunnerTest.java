package eu.gluffs.samples.montyhall;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.powermock.api.easymock.PowerMock.*;

public class GameRunnerTest {

  @Test
  public void newRunnerCreatesCorrectNumberOfGames() {
    int expected = 100;
    GameRunner runner = new GameRunner(expected);

    List<Game> games = Whitebox.getInternalState(runner, "games");

    assert games.size() == expected;

    for(Game game : games)
      assert game != null;
  }

  @Test
  public void playCallsChangeBox() {
    Game game = createMock(Game.class);
    List<Game> games = new ArrayList<Game>(1);
    games.add(game);

    GameRunner runner = new GameRunner(0);
    Whitebox.setInternalState(runner, "games", games);

    game.changeBox();
    expectLastCall().once();
    expect(game.isCorrect()).andReturn(false);
    replay(game);

    runner.play(true);

    verify(game);
  }

  @Test
  public void playDontCallChangeBox() {
    Game game = createMock(Game.class);
    List<Game> games = new ArrayList<Game>(1);
    games.add(game);

    GameRunner runner = new GameRunner(0);
    Whitebox.setInternalState(runner, "games", games);

    expect(game.isCorrect()).andReturn(false);
    replay(game);

    runner.play(false);

    verify(game);
  }

  @Test
  public void playIncreasesWinIfGameIsCorrect() {
    Game game = createMock(Game.class);
    List<Game> games = new ArrayList<Game>(1);
    games.add(game);

    GameRunner runner = new GameRunner(0);
    Whitebox.setInternalState(runner, "games", games);
    Whitebox.setInternalState(runner, "wins", 0);

    expect(game.isCorrect()).andReturn(true);
    replay(game);

    runner.play(false);

    assert runner.getWins() == 1;
  }

  @Test
  public void getWinsReturnsTheNumberOfWins() {
    int expected = 3;
    GameRunner runner = new GameRunner(0);

    Whitebox.setInternalState(runner, "wins", expected);

    assert runner.getWins() == expected;
  }

  @Test
  public void winPercentCalculatesCorrectly() {
    int games = 10;
    int wins = 3;
    int expected = 30;

    GameRunner runner = new GameRunner(games);

    Whitebox.setInternalState(runner, "wins", wins);

    assert runner.winPercent() == expected;
  }

  @Test
  public void toStringPrintsTheCorrectString() {
    int games = 10;
    int wins = 3;
    String expected = "30% wins";

    GameRunner runner = new GameRunner(games);
    Whitebox.setInternalState(runner, "wins", wins);

    assert runner.toString().equals(expected);
  }
}
