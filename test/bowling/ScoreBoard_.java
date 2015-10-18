package bowling;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class ScoreBoard_ {

    @Test
    public void when_there_are_no_rolls_frames_should_be_zero() throws Exception {
        Match match = new Match("player");
        ScoreBoard board = new ScoreBoard(match);
        assertThat(board.frames().length, is(0));
    }

    @Test
    public void when_there_are_one_rolls_frames_should_be_one_and_score_null() throws Exception {
        Match match = new Match("player");
        match.addRoll(4);
        ScoreBoard board = new ScoreBoard(match);
        assertThat(board.frames().length, is(1));
        assertThat(board.frames()[0].score(), is(nullValue()));
    }

    @Test
    public void when_there_are_two_rolls_frames_should_be_one_with_score() throws Exception {
        Match match = new Match("player");
        match.addRoll(4);
        match.addRoll(5);
        ScoreBoard board = new ScoreBoard(match);
        assertThat(board.frames().length, is(1));
        assertThat(board.frames()[0].score(), is(9));
    }

    @Test
    public void when_there_are_two_rolls_with_spare_frames_should_be_one_and_score_null() throws Exception {
        Match match = new Match("player");
        match.addRoll(4);
        match.addRoll(6);
        ScoreBoard board = new ScoreBoard(match);
        assertThat(board.frames().length, is(1));
        assertThat(board.frames()[0].score(), is(nullValue()));
    }

    @Test
    public void when_there_are_three_rolls_frames_should_be_two_and_second_frame_score_null() throws Exception {
        Match match = new Match("player");
        match.addRoll(4);
        match.addRoll(5);
        match.addRoll(3);
        ScoreBoard board = new ScoreBoard(match);
        assertThat(board.frames().length, is(2));
        assertThat(board.frames()[0].score(), is(9));
        assertThat(board.frames()[1].score(), is(nullValue()));
    }

    @Test
    public void when_there_are_four_rolls_frames_should_be_two_with_score() throws Exception {
        Match match = new Match("player");
        match.addRoll(4);
        match.addRoll(5);
        match.addRoll(3);
        match.addRoll(4);
        ScoreBoard board = new ScoreBoard(match);
        assertThat(board.frames().length, is(2));
        assertThat(board.frames()[0].score(), is(9));
        assertThat(board.frames()[1].score(), is(16));
    }

    @Test
    public void when_there_are_four_rolls_with_spare_frames_should_be_two_with_score() throws Exception {
        Match match = new Match("player");
        match.addRoll(4);
        match.addRoll(6);
        match.addRoll(3);
        match.addRoll(3);
        ScoreBoard board = new ScoreBoard(match);
        assertThat(board.frames().length, is(2));
        assertThat(board.frames()[0].score(), is(13));
        assertThat(board.frames()[1].score(), is(19));
    }

    @Test
    public void when_there_are_two_rolls_with_strike_frames_should_be_two_without_score() throws Exception {
        Match match = new Match("player");
        match.addRoll(10);
        match.addRoll(6);
        ScoreBoard board = new ScoreBoard(match);
        assertThat(board.frames().length, is(2));
        assertThat(board.frames()[0].score(), is(nullValue()));
        assertThat(board.frames()[1].score(), is(nullValue()));
    }
}
