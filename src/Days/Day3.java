package Days;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Day3 extends Day {

    protected Day3(int puzzleNumber) {
        super(3, puzzleNumber);
    }

    @Override
    void solve() {
        var text = super.readFile();
        var result = super.getPuzzleNumber() == 1 ? solvePuzzle1(text) : solvePuzzle2(text);
        super.printResult(result);
    }

    private int solvePuzzle1(String input) {
        int result = 0;
        String regex = "(mul)\\(([0-9])([0-9]?)([0-9]?),([0-9])([0-9]?)([0-9]?)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String match = matcher.group();
            var splitText = match.split(",");
            splitText[0] = splitText[0].replaceAll("(mul)\\(", "");
            splitText[1] = splitText[1].replaceAll("\\)", "");

            int number1 = Integer.parseInt(splitText[0]);
            int number2 = Integer.parseInt(splitText[1]);

            result += number1 * number2;
        }
        return result;
    }

    private int solvePuzzle2(String input) {
        final String dontString = "don't()";
        final String doString = "do()";

        String current = "";

        boolean reading = true;
        while (input.length() > 0) {
            if (input.indexOf(dontString) == 0) {
                reading = false;
            }
            if (input.indexOf(doString) == 0) {
                reading = true;
            }
            if (reading) {
                current += input.charAt(0);
            }
            input = input.substring(1);
        }

        return solvePuzzle1(current);
    }

}
