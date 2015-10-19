package bowling;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoard {
    private Match match;

    public ScoreBoard(Match match) {
        this.match = match;
    }

    public Frame[] frames() {
        return toArray(buildFrames());
    }

    private List<Frame> buildFrames() {
        List<Frame> frames = new ArrayList<>();
        Frame frame = () -> 0;
        for (int i = 0; i < numberOfRolls(); i+=2) {
            frame = new RegularFrame(i, frame);
            frames.add(frame);
        }
        return frames;
    }

    private Frame[] toArray(List<Frame> frames) {
        return frames.toArray(new Frame[frames.size()]);
    }


    private int numberOfRolls() {
        return rolls().size();
    }

    public List<Integer> rolls() {
        return match.rolls();
    }

    public interface Frame {
        Integer score();
    }

    private class RegularFrame implements Frame {
        private int initialRoll;
        private Frame previous;

        public RegularFrame(int initialRoll, Frame previous) {
            this.initialRoll = initialRoll;
            this.previous = previous;
        }

        @Override
        public Integer score() {
            return isComplete() ? previous.score() + sumOfPins() : null;
        }

        private boolean isComplete() {
            return isCompleteBySpare() || isCompleteByStrike() || hasTwoRolls();
        }

        private boolean isCompleteBySpare(){ return isSpare() && hasThreeRolls();}

        private boolean isCompleteByStrike(){ return isStrike() && hasThreeRolls();}

        private boolean isSpare() {
            return hasTwoRolls() && (pinsInRoll(0) + pinsInRoll(1) == 10);
        }

        private boolean isStrike(){return hasOneRoll() && pinsInRoll(0)==10;}

        private boolean hasOneRoll() { return numberOfRolls()==1;}

        private Integer pinsInRoll(int index) {
            return rolls().get(initialRoll + index);
        }

        private boolean hasThreeRolls() {
            return numberOfRolls() > 2;
        }

        private boolean hasTwoRolls() {
            return numberOfRolls() > 1;
        }

        private int numberOfRolls() {
            return ScoreBoard.this.numberOfRolls() - initialRoll;
        }

        private int sumOfPins() {
            return pinsInRoll(0) + pinsInRoll(1) + extraPins();
        }

        private int extraPins() {
            return isSpare() ? pinsInRoll(2) : 0;
        }
    }
}
