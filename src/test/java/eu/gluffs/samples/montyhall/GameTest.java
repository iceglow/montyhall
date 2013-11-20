package eu.gluffs.samples.montyhall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.easymock.EasyMock.expect;
import static org.powermock.api.easymock.PowerMock.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Game.class})
public class GameTest {

  @Test
  public void newGamesCreatesBoxes() {
    Game game = new Game(0);

    boolean[] boxes = Whitebox.getInternalState(game, "boxes");

    assert boxes.length == 3;
    assert !boxes[0];
    assert boxes[1];
    assert !boxes[2];
  }

  @Test
  public void newGamesSetsTheGuess() {
    int expected = 1;
    Game game = new Game(expected);

    int guess = Whitebox.getInternalState(game, "guess");

    assert guess == expected;
  }

  @Test
  public void playCallsChangeBox() {
    Game spy = createPartialMockForAllMethodsExcept(Game.class, "play");

    spy.changeBox();
    expectLastCall().once();
    expect(spy.isCorrect()).andReturn(true).once();
    replay(spy);

    spy.play(true);

    verify(spy);
  }

  @Test
  public void playDontCallChangeBox() {
    Game spy = createPartialMockForAllMethodsExcept(Game.class, "play");

    expect(spy.isCorrect()).andReturn(true).once();
    replay(spy);

    spy.play(false);

    verify(spy);
  }

  @Test
  public void playReturnsResultOfIsCorrect() {
    boolean expected = true;
    Game spy = createPartialMockForAllMethodsExcept(Game.class, "play");

    expect(spy.isCorrect()).andReturn(expected).once();
    replay(spy);

    boolean result = spy.play(false);

    verify(spy);
    assert result == expected;
  }

  @Test
  public void changeBoxChangesTheGuess() {
    for (int i = 0; i < 3; i++) {
      Game game = new Game(i);
      game.changeBox();

      int guess = Whitebox.getInternalState(game, "guess");

      assert guess != i;
    }
  }

  @Test
  public void isCorrectChecksTheBoxAgainstTheGuess() {
    Game game = new Game(0);
    boolean[] boxes = Whitebox.getInternalState(game, "boxes");

    for (int i = 0; i < 3; i++) {
      Whitebox.setInternalState(game, "guess", i);

      assert game.isCorrect() == boxes[i];
    }
  }
}
