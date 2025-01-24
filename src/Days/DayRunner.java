package Days;

public class DayRunner {

    public void runDay(int day, int puzzleNumber){
        if(puzzleNumber !=1 && puzzleNumber !=2){
            System.out.println("Invalid puzzle number. There are only 2 puzzles per day.");
            return;
        }
        Day puzzle;
        switch(day){
            case 1:
                puzzle = new Day1(puzzleNumber);
                break;
            case 2:
                puzzle = new Day2(puzzleNumber);
                break;
            case 3:
                puzzle = new Day3(puzzleNumber);
                break;
            default:
                return;
        }
        puzzle.solve();
    }

}
