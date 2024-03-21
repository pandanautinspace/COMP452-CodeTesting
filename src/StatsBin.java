import java.util.ArrayList;

public class StatsBin {
    private static final int [] BIN_EDGES = {1, 2, 4, 6, 8, 10, 12, 14};
    public static ArrayList<String> getBinNames() {
        ArrayList<String> s = new ArrayList<>();
        for(int binIndex=0; binIndex<BIN_EDGES.length; binIndex++) {
            String binName;
            if (binIndex == BIN_EDGES.length - 1) {
                // last bin
                binName = BIN_EDGES[binIndex] + " or more";
            } else {
                int upperBound = BIN_EDGES[binIndex + 1] - 1;
                if (upperBound > BIN_EDGES[binIndex]) {
                    binName = BIN_EDGES[binIndex] + "-" + upperBound;
                } else {
                    binName = Integer.toString(BIN_EDGES[binIndex]);
                }
            }
            s.add(binName);
        }
        return s;
    }

    public static ArrayList<Integer> getBinGames(GameStats stats) {
        ArrayList<Integer> result = new ArrayList<>();
        for(int binIndex=0; binIndex<BIN_EDGES.length; binIndex++) {
            final int lowerBound = BIN_EDGES[binIndex];
            int numGames = 0;

            if (binIndex == BIN_EDGES.length - 1) {
                // last bin
                // Sum all the results from lowerBound on up
                for (int numGuesses = lowerBound; numGuesses < stats.maxNumGuesses(); numGuesses++) {
                    numGames += stats.numGames(numGuesses);
                }
            } else {
                int upperBound = BIN_EDGES[binIndex + 1];
                for (int numGuesses = lowerBound; numGuesses <= upperBound; numGuesses++) {
                    numGames += stats.numGames(numGuesses);
                }
            }
            result.add(numGames);
        }
        return result;
    }
}
