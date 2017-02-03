package exercises.xf;

import java.util.regex.Pattern;

/**
 * Implementation of MineSweeper.
 *
 * Created by guisil on 02/02/2017.
 */
public class ActualMineSweeper implements MineSweeper {

    private static final Pattern linePattern = Pattern.compile("[\\.\\*]+");

    private String mineField;


    /**
     * {@inheritDoc}
     */
    @Override
    public void setMineField(String mineField) throws IllegalArgumentException {

        this.mineField = mineField;

        String[] lines = mineField.split("\\n");
        if (lines.length == 0) {
            throw new IllegalArgumentException("Invalid mine field");
        }
        for (String line: lines) {
            if (!(matchLinePattern(line) && line.length() == lines[0].length())) {
                throw new IllegalArgumentException(" Invalid mine field");
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHintField() throws IllegalStateException {

        if (mineField == null) {
            throw new IllegalStateException("Mine field not initialised");
        }

        String mine = "*";
        String[] lines = mineField.split("\\n");

        int[][] mineFieldInts = new int[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            int nextMineIndex = lines[i].indexOf(mine);
            while (nextMineIndex != -1) {
                updateMineAndHints(mineFieldInts, i, nextMineIndex, lines[i].length());
                nextMineIndex = lines[i].indexOf(mine, nextMineIndex + 1);
            }
        }
        return hintFieldToString(mineFieldInts);
    }


    /**
     * Returns true if the given line matches the expected line pattern.
     */
    private boolean matchLinePattern(String line) {
        return linePattern
                .matcher(line)
                .matches();
    }

    /**
     * Updates given int array with the location of the mine (represented as -1)
     * and also with the incremented hints adjacent to that mine.
     */
    private void updateMineAndHints(int[][] hintFieldAsInts, int lineOfMine, int columnOfMine, int lineLength) {

        hintFieldAsInts[lineOfMine][columnOfMine] = -1;

        for(int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int lineToUpdate = lineOfMine + i;
                int columnToUpdate = columnOfMine + j;
                if (lineToUpdate < 0 || columnToUpdate < 0 || columnToUpdate >= lineLength) {
                    continue;
                }
                if (lineToUpdate >= hintFieldAsInts.length) {
                    break;
                }
                int currentCount = hintFieldAsInts[lineToUpdate][columnToUpdate];
                if (currentCount >= 0) {
                    hintFieldAsInts[lineToUpdate][columnToUpdate] = currentCount + 1;
                }
            }
        }
    }

    /**
     * Converts the int[][] representing the hint field back to a String.
     */
    private String hintFieldToString(int[][] hintFieldAsInts) {
        StringBuilder builder = new StringBuilder();
        for (int rowIndex = 0; rowIndex < hintFieldAsInts.length; rowIndex++) {
            for (int columnIndex: hintFieldAsInts[rowIndex]) {
                if (columnIndex < 0) {
                    builder.append("*");
                } else {
                    builder.append(columnIndex);
                }
            }
            if (rowIndex < hintFieldAsInts.length - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
