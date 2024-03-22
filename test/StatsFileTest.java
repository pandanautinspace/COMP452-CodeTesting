import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatsFileTest {

    @Test
    void numGames() {
        GameResult g = new GameResult(true, 100, 5);
        StatsFile s = new StatsFile();
        int current = s.numGames(5);
        s.writeGameResults(g);
        assertEquals(current+1, s.numGames(5));
    }

    @Test
    void maxNumGuesses() {
        StatsFile s = new StatsFile();
        int current = s.maxNumGuesses();
        GameResult g = new GameResult(true, 100, current+1);
        s.writeGameResults(g);
        assertEquals(current+1, s.maxNumGuesses());
    }
}