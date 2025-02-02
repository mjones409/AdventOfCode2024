package Days;

import java.util.ArrayList;
import java.util.Collections;


class Day1 extends Day {

    Day1(int puzzleNumber) {
        super(1, puzzleNumber);
    }

    @Override
    public void solve() {
        // read file
        String text = super.readFile();

        // get columns
        String[] lines = text.split("\\r?\\n");
        ArrayList<Integer> leftColumn = new ArrayList<Integer>();
        ArrayList<Integer> rightColumn = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split("   ");
            leftColumn.add(Integer.parseInt(parts[0]));
            rightColumn.add(Integer.parseInt(parts[1]));
        }


        // solve puzzle
        int result = super.getPuzzleNumber() == 1 ? solvePuzzle1(leftColumn, rightColumn) : solvePuzzle2(leftColumn, rightColumn);

        super.printResult(result);

    }

   private   int solvePuzzle2(ArrayList<Integer> leftColumn, ArrayList<Integer> rightColumn) {
        int result = 0;

        for (int num : leftColumn) {
            int freq = Collections.frequency(rightColumn, num);
            result += freq * num;
        }

        return result;
    }

    private int solvePuzzle1(ArrayList<Integer> leftColumn, ArrayList<Integer> rightColumn) {

        Collections.sort(leftColumn);
        Collections.sort(rightColumn);
        int distance = 0;

        for (int i = leftColumn.size() - 1; i >= 0; i--) {
            distance += Math.abs(leftColumn.get(i) - rightColumn.get(i));
        }
        return distance;
    }
}
