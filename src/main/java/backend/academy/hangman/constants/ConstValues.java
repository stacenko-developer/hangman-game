package backend.academy.hangman.constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConstValues {

    public final static String CAT = "КОТ";
    public final static String DOG = "СОБАКА";
    public final static String LION = "ЛЕВ";
    public final static String KANGAROO = "КЕНГУРУ";
    public final static String PENGUIN = "ПИНГВИН";
    public final static String CAMEL = "ВЕРБЛЮД";
    public final static String CHAMELEON = "ХАМЕЛЕОН";
    public final static String ORANGUTAN = "ОРАНГУТАН";
    public final static String CROCODILE = "КРОКОДИЛ";
    public final static String APPLE = "ЯБЛОКО";
    public final static String PEAR = "ГРУША";
    public final static String PLUM = "СЛИВА";
    public final static String ORANGE = "АПЕЛЬСИН";
    public final static String KIWI = "КИВИ";
    public final static String PEACH = "ПЕРСИК";
    public final static String GRAPEFRUIT = "ГРЕЙПФРУТ";
    public final static String MANGO = "МАНГО";
    public final static String PINEAPPLE = "АНАНАС";
    public final static String BEACH = "ПЛЯЖ";
    public final static String SLEEP = "СОН";
    public final static String PARK = "ПАРК";
    public final static String VACATION = "ОТПУСК";
    public final static String CRUISE = "КРУИЗ";
    public final static String PICNIC = "ПИКНИК";
    public final static String CAMPING = "КЕМПИНГ";
    public final static String EXCURSION = "ЭКСКУРСИЯ";
    public final static String SAUNA = "САУНА";
    public final static String SOUP = "СУП";
    public final static String PORRIDGE = "КАША";
    public final static String BREAD = "ХЛЕБ";
    public final static String SALAD = "САЛАТ";
    public final static String COMPOTE = "КОМПОТ";
    public final static String BURGER = "БУРГЕР";
    public final static String LASAGNA = "ЛАЗАНЬЯ";
    public final static String CAKE = "ПИРОЖНОЕ";
    public final static String MARINADE = "МАРИНАД";
    public final static String RADIO = "РАДИО";
    public final static String LAMP = "ЛАМПА";
    public final static String WATCH = "ЧАСЫ";
    public final static String TABLET = "ПЛАНШЕТ";
    public final static String HEADPHONES = "НАУШНИКИ";
    public final static String PRINTER = "ПРИНТЕР";
    public final static String NUKE = "МИКРОВОЛНОВКА";
    public final static String BATTERY = "АККУМУЛЯТОР";
    public final static String TELEVISION = "ТЕЛЕВИЗОР";

    public final static String HINT_FOR_CAT = "Домашнее животное, которое любит ловить мышей";
    public final static String HINT_FOR_DOG = "Четвероногий друг человека";
    public final static String HINT_FOR_LION = "Царь животных, который живет в саванне";
    public final static String HINT_FOR_KANGAROO = "Носит детеныша в сумке";
    public final static String HINT_FOR_PENGUIN = "Птица, которая не умеет летать";
    public final static String HINT_FOR_CAMEL = "Животное с горбами, которое обитает в пустыне";
    public final static String HINT_FOR_CHAMELEON = "Ящерица, которая меняет цвет для маскировки и общения";
    public final static String HINT_FOR_ORANGUTAN = "Большая обезьяна с длинными руками и яркой окраской";
    public final static String HINT_FOR_CROCODILE = "Имеет крепкие челюсти и часто живет в реках и болотах";
    public final static String HINT_FOR_APPLE = "Имеет сладкий или кислый вкус и часто используется в выпечке";
    public final static String HINT_FOR_PEAR = "Имеет форму, похожую на форму бутылки";
    public final static String HINT_FOR_PLUM = "Часто используется для приготовления варенья";
    public final static String HINT_FOR_ORANGE = "Часто упоминается в контексте витамина C и здоровья";
    public final static String HINT_FOR_KIWI = "Фрукт, названный в честь птицы из Новой Зеландии";
    public final static String HINT_FOR_PEACH = "Фрукт с сочной мякотью и большой косточкой внутри";
    public final static String HINT_FOR_GRAPEFRUIT = "Фрукт с кислым вкусом и розовой или красной мякотью";
    public final static String HINT_FOR_MANGO = "Тропический фрукт с ярко-оранжевой сочной мякотью";
    public final static String HINT_FOR_PINEAPPLE = "Фрукт с толстой колючей кожурой и ярко-желтой сочной мякотью";
    public final static String HINT_FOR_BEACH = "Место у воды, покрытое песком или галькой";
    public final static String HINT_FOR_SLEEP = "Состояние покоя и расслабления";
    public final static String HINT_FOR_PARK = "Зеленая зона в городе";
    public final static String HINT_FOR_VACATION = "Период времени, когда люди отдыхают от работы и путешествуют";
    public final static String HINT_FOR_CRUISE = "Путешествие на большом корабле";
    public final static String HINT_FOR_PICNIC = "Встреча на природе с едой";
    public final static String HINT_FOR_CAMPING = "Отдых на природе с ночёвкой в палатке";
    public final static String HINT_FOR_EXCURSION = "Прогулка с гидом, который рассказывает об интересных местах";
    public final static String HINT_FOR_SAUNA = "Место, где можно насладиться горячим паром и высокой температурой";
    public final static String HINT_FOR_SOUP = "Первое блюдо, которое подают горячим";
    public final static String HINT_FOR_PORRIDGE = "Блюдо из вареных круп";
    public final static String HINT_FOR_BREAD = "Продукт, который готовят из муки, воды и дрожжей";
    public final static String HINT_FOR_SALAD = "Блюдо, которое готовят из нарезанных овощей";
    public final static String HINT_FOR_COMPOTE = "Сладкий напиток, который готовят из вареных фруктов и сахара";
    public final static String HINT_FOR_BURGER = "Еда, которую часто едят с картошкой фри и напитками";
    public final static String HINT_FOR_LASAGNA = "Блюдо, которое состоит из слоев пасты и сыра";
    public final static String HINT_FOR_CAKE = "Кордитерское изделие с кремом и начинкой внутри";
    public final static String HINT_FOR_MARINADE = "Смесь жидкостей и специй, используемая для замачивания мяса";
    public final static String HINT_FOR_RADIO = "Устройство для прослушивания музыки или новостей";
    public final static String HINT_FOR_LAMP = "Устройство, которое освещает помещение";
    public final static String HINT_FOR_WATCH = "Прибор, который показывает текущее время";
    public final static String HINT_FOR_TABLET = "Портативное устройство с сенсорным экраном";
    public final static String HINT_FOR_HEADPHONES = "Устройство для прослушивания аудиоконтента";
    public final static String HINT_FOR_PRINTER = "Устройство, которое переводит данные с компьютера на бумагу";
    public final static String HINT_FOR_NUKE = "Устройство для быстрого разогрева пищи";
    public final static String HINT_FOR_BATTERY = "Устройство, которое хранит электроэнергию";
    public final static String HINT_FOR_TELEVISION = "Устройство для просмотра видеоконтента и передач";

    public final static List<String> ANIMALS_FOR_EASY_LEVEL = List.of(CAT, DOG, LION);
    public final static List<String> ANIMALS_FOR_MEDIUM_LEVEL = List.of(KANGAROO, PENGUIN, CAMEL);
    public final static List<String> ANIMALS_FOR_HARD_LEVEL = List.of(CHAMELEON, ORANGUTAN, CROCODILE);
    public final static List<String> FRUITS_FOR_EASY_LEVEL = List.of(APPLE, PEAR, PLUM);
    public final static List<String> FRUITS_FOR_MEDIUM_LEVEL = List.of(ORANGE, KIWI, PEACH);
    public final static List<String> FRUITS_FOR_HARD_LEVEL = List.of(GRAPEFRUIT, MANGO, PINEAPPLE);
    public final static List<String> REST_FOR_EASY_LEVEL = List.of(BEACH, SLEEP, PARK);
    public final static List<String> REST_FOR_MEDIUM_LEVEL = List.of(VACATION, CRUISE, PICNIC);
    public final static List<String> REST_FOR_HARD_LEVEL = List.of(CAMPING, EXCURSION, SAUNA);
    public final static List<String> FOOD_FOR_EASY_LEVEL = List.of(SOUP, PORRIDGE, BREAD);
    public final static List<String> FOOD_FOR_MEDIUM_LEVEL = List.of(SALAD, COMPOTE, BURGER);
    public final static List<String> FOOD_FOR_HARD_LEVEL = List.of(LASAGNA, CAKE, MARINADE);
    public final static List<String> ELECTRONICS_FOR_EASY_LEVEL = List.of(RADIO, LAMP, WATCH);
    public final static List<String> ELECTRONICS_FOR_MEDIUM_LEVEL = List.of(TABLET, HEADPHONES, PRINTER);
    public final static List<String> ELECTRONICS_FOR_HARD_LEVEL = List.of(NUKE, BATTERY, TELEVISION);

    public final static List<String> HANGMAN_STAGES = List.of(
        """
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========
            """,
        """
              +---+
              |   |
              O   |
                  |
                  |
                  |
            =========
            """,
        """
              +---+
              |   |
              O   |
              |   |
                  |
                  |
            =========
            """,
        """
              +---+
              |   |
              O   |
             /|   |
                  |
                  |
            =========
            """,
        """
              +---+
              |   |
              O   |
             /|\\  |
                  |
                  |
            =========
            """,
        """
              +---+
              |   |
              O   |
             /|\\  |
             /    |
                  |
            =========
            """,
        """
              +---+
              |   |
              O   |
             /|\\  |
             / \\  |
                  |
            =========
            """
    );

    public final static Map<String, String> HINTERS = new HashMap<>();

    static {
        HINTERS.put(CAT, HINT_FOR_CAT);
        HINTERS.put(DOG, HINT_FOR_DOG);
        HINTERS.put(LION, HINT_FOR_LION);
        HINTERS.put(KANGAROO, HINT_FOR_KANGAROO);
        HINTERS.put(PENGUIN, HINT_FOR_PENGUIN);
        HINTERS.put(CAMEL, HINT_FOR_CAMEL);
        HINTERS.put(CHAMELEON, HINT_FOR_CHAMELEON);
        HINTERS.put(ORANGUTAN, HINT_FOR_ORANGUTAN);
        HINTERS.put(CROCODILE, HINT_FOR_CROCODILE);
        HINTERS.put(APPLE, HINT_FOR_APPLE);
        HINTERS.put(PEAR, HINT_FOR_PEAR);
        HINTERS.put(PLUM, HINT_FOR_PLUM);
        HINTERS.put(ORANGE, HINT_FOR_ORANGE);
        HINTERS.put(KIWI, HINT_FOR_KIWI);
        HINTERS.put(PEACH, HINT_FOR_PEACH);
        HINTERS.put(GRAPEFRUIT, HINT_FOR_GRAPEFRUIT);
        HINTERS.put(MANGO, HINT_FOR_MANGO);
        HINTERS.put(PINEAPPLE, HINT_FOR_PINEAPPLE);
        HINTERS.put(BEACH, HINT_FOR_BEACH);
        HINTERS.put(SLEEP, HINT_FOR_SLEEP);
        HINTERS.put(PARK, HINT_FOR_PARK);
        HINTERS.put(VACATION, HINT_FOR_VACATION);
        HINTERS.put(CRUISE, HINT_FOR_CRUISE);
        HINTERS.put(PICNIC, HINT_FOR_PICNIC);
        HINTERS.put(CAMPING, HINT_FOR_CAMPING);
        HINTERS.put(EXCURSION, HINT_FOR_EXCURSION);
        HINTERS.put(SAUNA, HINT_FOR_SAUNA);
        HINTERS.put(SOUP, HINT_FOR_SOUP);
        HINTERS.put(PORRIDGE, HINT_FOR_PORRIDGE);
        HINTERS.put(BREAD, HINT_FOR_BREAD);
        HINTERS.put(SALAD, HINT_FOR_SALAD);
        HINTERS.put(COMPOTE, HINT_FOR_COMPOTE);
        HINTERS.put(BURGER, HINT_FOR_BURGER);
        HINTERS.put(LASAGNA, HINT_FOR_LASAGNA);
        HINTERS.put(CAKE, HINT_FOR_CAKE);
        HINTERS.put(MARINADE, HINT_FOR_MARINADE);
        HINTERS.put(RADIO, HINT_FOR_RADIO);
        HINTERS.put(LAMP, HINT_FOR_LAMP);
        HINTERS.put(WATCH, HINT_FOR_WATCH);
        HINTERS.put(TABLET, HINT_FOR_TABLET);
        HINTERS.put(HEADPHONES, HINT_FOR_HEADPHONES);
        HINTERS.put(PRINTER, HINT_FOR_PRINTER);
        HINTERS.put(NUKE, HINT_FOR_NUKE);
        HINTERS.put(BATTERY, HINT_FOR_BATTERY);
        HINTERS.put(TELEVISION, HINT_FOR_TELEVISION);
    }
}
