package bowling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Match {
    private final String[] player;
    private final List<Integer> rolls = new ArrayList<>();

    public Match(String... player) {
        this.player = player;
    }

    public void addRoll(int pins) {
        rolls.add(pins);
    }

    public List<Integer> rolls() {
        return unmodifiableList(rolls);
    }
}
