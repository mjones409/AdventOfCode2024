package Days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;


class Day2 extends Day {
    protected Day2(int puzzleNumber) {
        super(2, puzzleNumber);
    }

    @Override
    public void solve() {
        String text = super.readFile();

        ArrayList<ArrayList<Integer>> columns = new ArrayList<>();

        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            ArrayList<Integer> row = new ArrayList<>();
            columns.add(row);
            for (String cell : line.split("\\s+")) {
                row.add(Integer.parseInt(cell));
            }
        }
        int result =super.getPuzzleNumber() == 1?solvePuzzle1(columns):solvePuzzle2(columns);
        super.printResult(result);
    }

    private int solvePuzzle2(ArrayList<ArrayList<Integer>> grid) {
        int validCount= 0;
        for(ArrayList<Integer> row : grid) {
            if(isValid(row)){
                validCount++;
                continue;
            }
            for(int i=0;i<row.size();i++) {
                var newRow= removeFromList(row,i);
                if(isValid(newRow)){
                    validCount++;
                    break;
                }
            }
        }
        return validCount;
    }

    private ArrayList<Integer> removeFromList(ArrayList<Integer> row, int indexToRemove) {
        var newRow = new ArrayList<Integer>();
        for(Integer cell : row) {
            newRow.add(cell);
        }
        newRow.remove(indexToRemove);
        return newRow;
    }

    private boolean isValid(ArrayList<Integer> row){

            if(hasDuplicates(row)){
                return false;
            }

            if(isValidExceptDuplicates(row)){
                return true;
            }
        return false;
    }

    private int solvePuzzle1(ArrayList<ArrayList<Integer>> grid) {
        int validCount= 0;
        for(ArrayList<Integer> row : grid) {
           if(isValid(row)){
               validCount++;
           }
        }
        return validCount;
    }

    private boolean isValidExceptDuplicates(ArrayList<Integer> row){
        boolean increasing = row.get(0)<row.get(1);

        for(int i = 1; i<row.size(); i++) {
            if(increasing && row.get(i)<row.get(i-1)) {
                return false;
            }
            if(!increasing && row.get(i)>row.get(i-1)) {
                return false;
            }
            if(Math.abs(row.get(i)-row.get(i-1))>3){
                return false;
            }
        }
        return true;
    }

    private boolean hasDuplicates(ArrayList<Integer> row) {
        HashSet<Integer> set = new HashSet<>();
        for(Integer cell : row) {
            if(set.contains(cell)) {
               return true;
            }
            set.add(cell);
        }
        return false;
    }
}
