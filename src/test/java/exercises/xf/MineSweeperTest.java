package exercises.xf;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 * Tests for MineSweeper.
 *
 * Created by guisil on 02/02/2017.
 */
public class MineSweeperTest {

    private MineSweeper mineSweeper;

    @Before
    public void setUp() throws Exception {
        mineSweeper = new ActualMineSweeper();
    }

    @Test
    public void shouldValidateMineField() throws Exception {
        String mineField = "*...\n..*.\n....";
        mineSweeper.setMineField(mineField);
    }

    @Test
    public void shouldValidateMineFieldWithOnlyOneCharacter() throws Exception {
        String mineField = ".";
        mineSweeper.setMineField(mineField);
    }

    @Test
    public void shouldNotValidateEmptyMineField() throws Exception {
        assertThatThrownBy(() -> mineSweeper.setMineField(""))
                .as("Checking if an empty string as mine field causes an exception")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> mineSweeper.setMineField("\n\n"))
                .as("Checking if a string containing only newlines as mine field causes an exception")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotValidateMineFieldWithInvalidNewlines() throws Exception {
        assertThatThrownBy(() -> mineSweeper.setMineField("..*.\n\n..**.\n...."))
                .as("Checking if a string containing lines of different lengths causes an exception")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> mineSweeper.setMineField("\n..*.\n..**.\n...."))
                .as("Checking if a string containing lines of different lengths causes an exception")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> mineSweeper.setMineField("..*.\n..**.\n....\n"))
                .as("Checking if a string containing lines of different lengths causes an exception")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotValidateMineFieldWithDifferentLineLengths() throws Exception {
        assertThatThrownBy(() -> mineSweeper.setMineField("..*.\n..**.\n...."))
                .as("Checking if a string containing lines of different lengths causes an exception")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldGetHintField() throws Exception {
        String mineField = "*...\n..*.\n....";
        mineSweeper.setMineField(mineField);
        String expectedHintField = "*211\n12*1\n0111";
        assertThat(mineSweeper.getHintField()).as("Checking hint field").isEqualTo(expectedHintField);
    }

    @Test
    public void shouldThrowExceptinoIfMineFieldWasNotInitialised() throws Exception {
        assertThatThrownBy(() -> mineSweeper.getHintField())
                .as("Checking if an exception is thrown when trying to get the hint field without initializing the mine field first")
                .isInstanceOf(IllegalStateException.class);
    }
}