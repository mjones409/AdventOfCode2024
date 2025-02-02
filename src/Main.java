import Days.DayRunner;

public class Main {
    final static int puzzlesPerDay = 2;
    final static int daysOfAdventOfCode = 25;

    public static void main(String[] args) {
        DayRunner runner = new DayRunner();
        for (int i = 1; i <= daysOfAdventOfCode; i++) {
            for (int j = 1; j <= puzzlesPerDay; j++) {

                runner.runDay(i, j);
            }
        }
    }
}