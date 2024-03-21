import java.util.ArrayList;

public class StatsBin {
    private int [] binEdges;

    public StatsBin(int[] binEdges) {
        this.binEdges = binEdges;
    }
    public ArrayList<String> getBinNames() {
        ArrayList<String> s = new ArrayList<>();
        for(int binIndex=0; binIndex<binEdges.length; binIndex++) {
            String binName;
            if (binIndex == binEdges.length - 1) {
                // last bin
                binName = binEdges[binIndex] + " or more";
            } else {
                binName = getBinName(binEdges[binIndex], binEdges[binIndex+1]);
            }
            s.add(binName);
        }
        return s;
    }

    private static String getBinName(int edge1, int edge2) {
        int upperBound = edge2 - 1;
        if (upperBound > edge1) {
            return edge1 + "-" + upperBound;
        } else {
            return Integer.toString(edge1);
        }
    }

    public ArrayList<Integer> getBinGames(GameStats stats) {
        ArrayList<Integer> result = new ArrayList<>();
        for(int binIndex=0; binIndex<binEdges.length; binIndex++) {
            final int lowerBound = binEdges[binIndex];
            int numGames = 0;

            if (binIndex == binEdges.length - 1) {
                // last bin
                // Sum all the results from lowerBound on up
                for (int numGuesses = lowerBound; numGuesses < stats.maxNumGuesses(); numGuesses++) {
                    numGames += stats.numGames(numGuesses);
                }
            } else {
                int upperBound = binEdges[binIndex + 1];
                for (int numGuesses = lowerBound; numGuesses <= upperBound; numGuesses++) {
                    numGames += stats.numGames(numGuesses);
                }
            }
            result.add(numGames);
        }
        return result;
    }
}
