package Days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

abstract class Day {

    private  final int dayNumber;
    private final int puzzleNumber;


    protected Day (int dayNumber, int puzzleNumber) {
        this.dayNumber = dayNumber;
        this.puzzleNumber = puzzleNumber;
    }

    protected String readFile() {
        try {
            String relativePath = String.join("", "src/Data/Day", String.valueOf(dayNumber));
            var absolutePath = Paths.get(relativePath).toAbsolutePath();
            return Files.readString(absolutePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected int getPuzzleNumber(){
        return puzzleNumber;
    }

    abstract void solve();

    void printResult(int result){
        String statement = String.join(" ",
                "The answer to Day",
                Integer.toString(dayNumber),
                "Puzzle",
                Integer.toString(puzzleNumber),
                "is:",
                Integer.toString(result));
        System.out.println(statement);
    }
}
