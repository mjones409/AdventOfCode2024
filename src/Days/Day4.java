package Days;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 extends Day{
    Day4(int puzzleNumber) {
        super(4, puzzleNumber);
    }

    @Override
    void solve() {

        // read file
        String text = super.readFile();

        String[] rows = getRows(text);
        String[] cols = getCols(rows);
        String[] diagonals1 = getdiagonals1(rows,cols);
        String[] diagonals2 = getdiagonals2(rows,cols);

        String[][] matrix = new String[rows.length][cols.length];
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < cols.length; j++) {
                matrix[i][j] = String.valueOf(rows[i].charAt(j));
            }
        }

        // solve puzzle
        int result = super.getPuzzleNumber() == 1 ? solvePuzzle1(rows,cols,diagonals1,diagonals2) : solvePuzzle2(matrix);

        super.printResult(result);


    }

    private int solvePuzzle1(String[] rows, String[] cols, String[] diagonals1,String[] diagonals2) {
        return getNumberOfOccurrences(rows,cols,diagonals1,diagonals2,"XMAS");
    }

    private int solvePuzzle2(String[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j].equals("A")) {
                    if(isXmas(matrix,i,j)){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private boolean isXmas(String[][] matrix, int row, int col) {
        if(row-1<0){
            return false;
        }
        if(col-1<0){
            return false;
        }
        if(col+1>=matrix[0].length){
            return false;
        }
        if(row+1>=matrix.length){
            return false;
        }

        String topLeft = matrix[row-1][col-1];
        String topRight = matrix[row+1][col-1];
        String bottomLeft = matrix[row-1][col+1];
        String bottomRight = matrix[row+1][col+1];

        if (!(
                (topLeft.equals("M") && bottomRight.equals("S"))||
                (topLeft.equals("S") && bottomRight.equals("M"))
        )){
                return false;
        }

        if (!(
                        (topRight.equals("M") && bottomLeft.equals("S"))||
                        (topRight.equals("S") && bottomLeft.equals("M"))
        )){
            return false;
        }

        return true;
    }

    private String[] getdiagonals2(String[] rows, String[] cols) {
        ArrayList<String> newRows = new ArrayList<>();
        for(String row : rows) {
            newRows.add(new StringBuilder(row).reverse().toString());
        }
        rows = newRows.toArray(new String[0]);
        HashMap<Integer,String> map = new HashMap<>();
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < cols.length; j++) {
                char letter = rows[i].charAt(j);
                int key = i+j;
                String value=map.getOrDefault(key,null);
                if(value==null){
                    map.put(i+j, String.valueOf(letter));
                }else{
                    map.put(key,value+letter);
                }
            }
        }
        String[] diagonals2 = new String[map.size()];
        for(HashMap.Entry<Integer,String> entry:map.entrySet()){
            diagonals2[entry.getKey()]=entry.getValue();
        }
        return diagonals2;
    }

    private String[] getdiagonals1(String[] rows, String[] cols) {
        HashMap<Integer,String> map = new HashMap<>();
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < cols.length; j++) {
                char letter = rows[i].charAt(j);
                int key = i+j;
                String value=map.getOrDefault(key,null);
                if(value==null){
                    map.put(i+j, String.valueOf(letter));
                }else{
                    map.put(key,value+letter);
                }
            }
        }
        String[] diagonals1 = new String[map.size()];
        for(HashMap.Entry<Integer,String> entry:map.entrySet()){
            diagonals1[entry.getKey()]=entry.getValue();
        }
        return diagonals1;
    }

    private String[] getRows(String text){
        return text.split("\\r?\\n");
    }

    private String[] getCols(String[] rows){
        ArrayList<String> colsList = new ArrayList<>();
        for(int i = rows[0].length() - 1; i >= 0; i--) {
            String col = "";
            for(int j = 0; j < rows.length; j++) {
                col+=rows[j].charAt(i);
            }
            colsList.add(col);
        }
        Collections.reverse(colsList);
        return colsList.toArray(new String[colsList.size()]);

    }


    private int getNumberOfOccurrences(String[] rows, String[] cols, String[] diagonals1, String[] diagonals2, String text){

        String reverseText=new StringBuilder(text).reverse().toString();
        int count = 0;
        Pattern patternForward = Pattern.compile("("+text+")");
        Pattern patternBackward = Pattern.compile("("+reverseText+")");
        Matcher matcher;
  
        for(String row : rows){
            // forward rows
            matcher = patternForward.matcher(row);
            count += matcher.results().count();
            // backward rows
            matcher = patternBackward.matcher(row);
            count += matcher.results().count();
        }

        for(String col : cols){
            // forward cols
            matcher = patternForward.matcher(col);
            count += matcher.results().count();
            // backward cols
            matcher = patternBackward.matcher(col);
            count += matcher.results().count();
        }

        for(String diagonal : diagonals1){
            // forward diagonals1
            matcher = patternForward.matcher(diagonal);
            count += matcher.results().count();
            // backward diagonals1
            matcher = patternBackward.matcher(diagonal);
            count += matcher.results().count();
        }

        for(String diagonal : diagonals2){
            // forward diagonals2
            matcher = patternForward.matcher(diagonal);
            count += matcher.results().count();
            // backward diagonals2
            matcher = patternBackward.matcher(diagonal);
            count += matcher.results().count();
        }


        return count;
    }

}
