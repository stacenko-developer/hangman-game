package backend.academy.hangman.words;

import backend.academy.hangman.constants.ConstValues;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import lombok.experimental.UtilityClass;
import static backend.academy.hangman.constants.ExceptionTextValues.NO_SUCH_HINTER_EXCEPTION_TEXT;
import static backend.academy.hangman.constants.ExceptionTextValues.NULL_WORD_EXCEPTION_TEXT;
import static org.apache.commons.lang3.StringUtils.isBlank;

@UtilityClass
public class HintProvider {

    public static final Map<String, String> HINTERS = new HashMap<>(ConstValues.HINTERS);

    public static String getHint(String word) {
        if (isBlank(word)) {
            throw new NullPointerException(NULL_WORD_EXCEPTION_TEXT);
        }

        if (!HINTERS.containsKey(word)) {
            throw new NoSuchElementException(NO_SUCH_HINTER_EXCEPTION_TEXT);
        }

        return HINTERS.get(word);
    }
}
