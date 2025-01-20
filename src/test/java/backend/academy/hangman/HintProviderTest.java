package backend.academy.hangman;

import java.util.NoSuchElementException;
import backend.academy.hangman.words.HintProvider;
import org.junit.jupiter.api.Test;
import static backend.academy.hangman.constants.ConstValues.HINTERS;
import static backend.academy.hangman.constants.ExceptionTextValues.NO_SUCH_HINTER_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.NULL_WORD_EXCEPTION_TEXT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HintProviderTest {

    @Test
    public void getHintByWord_ShouldReturnHint() {
        HINTERS.forEach((word, correctHint) -> {
            String hint = HintProvider.getHint(word);

            assertEquals(hint, correctHint);
        });
    }

    @Test
    public void getHintByNullWord_ShouldThrowNullPointerException() {
        assertThatThrownBy(() -> {
            HintProvider.getHint(null);
        }).isInstanceOf(NullPointerException.class)
            .hasMessageContaining(NULL_WORD_EXCEPTION_TEXT);
    }

    @Test
    public void getHintByNonExistentKey_ShouldThrowNullPointerException() {
        final String nonExistentKey = "Несуществующий ключ";

        assertThatThrownBy(() -> {
            HintProvider.getHint(nonExistentKey);
        }).isInstanceOf(NoSuchElementException.class)
            .hasMessageContaining(NO_SUCH_HINTER_EXCEPTION_TEXT);
    }
}
