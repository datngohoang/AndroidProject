package hciproject.datnh.englishquiz.communicator;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hciproject.datnh.englishquiz.entity.ListeningQuizEntity;
import hciproject.datnh.englishquiz.entity.MultipleChoiceQuizEntity;
import hciproject.datnh.englishquiz.entity.WordQuizEntity;
import hciproject.datnh.englishquiz.model.ListeningQuizModel;
import hciproject.datnh.englishquiz.model.MultipleChoiceQuizModel;
import hciproject.datnh.englishquiz.model.WordQuizModel;

public class FakeController {
    static String[] listeningFileName = {"test", "test1", "test2", "test3", "test4", "test5"};

    static String getListening(int difficult, int quantity) {
        String result = "";

        ListeningQuizModel model = new ListeningQuizModel();

        model.setDifficult(difficult);
        model.setQuantity(quantity);
        Random random = new Random();

        List<ListeningQuizEntity> entities = new ArrayList<>();
        String[] answers = {"A", "B", "C", "D"};
        int n;

        for (int i = 0; i < quantity; i++) {
            n = random.nextInt(4);
            ListeningQuizEntity entity = new ListeningQuizEntity();
            entity.setId(i);
            entity.setQuestion("This is question " + i + ". Answer: " + answers[n]);
            entity.setAnswerA("Answer A");
            entity.setAnswerB("Answer B");
            entity.setAnswerC("Answer C");
            entity.setAnswerD("Answer D");
            entity.setAnswer(answers[n]);
            entity.setExplain("Because the answer is " + answers[n]);
            entity.setDifficult(difficult);

            //set random filename
            int m = random.nextInt(6);
            entity.setFilename(listeningFileName[m]);

            entities.add(entity);
        }

        model.setQuestions(entities);

        result = (new Gson()).toJson(model);

        return result;
    }

    static String getMultipleChoice(int difficult, int quantity) {
        String result = "";

        MultipleChoiceQuizModel model = new MultipleChoiceQuizModel();

        model.setDifficult(difficult);
        model.setQuantity(quantity);
        Random random = new Random();

        List<MultipleChoiceQuizEntity> entities = new ArrayList<>();
        String[] answers = {"A", "B", "C", "D"};
        int n;

        for (int i = 0; i < quantity; i++) {
            n = random.nextInt(4);
            MultipleChoiceQuizEntity entity = new MultipleChoiceQuizEntity();
            entity.setId(i);
            entity.setQuestion("This is question " + i + ". Answer: " + answers[n]);
            entity.setAnswerA("Answer A");
            entity.setAnswerB("Answer B");
            entity.setAnswerC("Answer C");
            entity.setAnswerD("Answer D");
            entity.setAnswer(answers[n]);
            entity.setExplain("Because the answer is " + answers[n]);
            entity.setDifficult(difficult);

            entities.add(entity);
        }

        model.setQuestions(entities);

        result = (new Gson()).toJson(model);

        return result;
    }

    static String getAllWord() {
        String result = "";

        WordQuizModel model = new WordQuizModel();
        List<WordQuizEntity> entities = new ArrayList<>();

        String[] name = {"Vocabulary", "Enterprise", "Efficiency", "Usability", "Effectiveness", "Dog", "Play", "Work"};
        String[] word = {"V O C A B U L A R Y", "E N T E R P R I S E", "E F F I C I E N C Y",
                "U S A B I L I T Y", "E F F E C T I V E N E S S", "D O G", "P L A Y", "W O R K"};
        String[] blankWord = {"V O _ A B _ L A _ Y", "E _ _ E _ P R _ S E", "E _ F I _ I E _ C Y",
                "U _ A B _ L I _ Y", "E F _ _ C T _ V E _ _ S S", "D _ G", "P _ _ Y", "W _ R _"};
        String[] meaning = {"Từ vựng", "Nhà Máy", "Hiệu quả",
                "Độ sử dụng", "Hiệu suất", "Con chó", "Chơi", "Làm việc"};
        String[] type = {"(n)", "(n)", "(n)",
                "(n)", "(n)", "(n)", "(v)", "(v)"};

        for (int i = 0; i < 5; i++) {
            WordQuizEntity entity = new WordQuizEntity();
            entity.setName(name[i]);
            entity.setId(i);
            entity.setWord(word[i]);
            entity.setBlankWord(blankWord[i]);
            entity.setType(type[i]);
            entity.setMeaning(meaning[i]);
            entities.add(entity);
        }

        model.setQuestions(entities);

        result = (new Gson()).toJson(model);

        return result;
    }

    static String getWord() {
        String result = "";

        WordQuizEntity entity = new WordQuizEntity();

        String[] name = {"Vocabulary", "Enterprise", "Efficiency", "Usability", "Effectiveness"};
        String[] word = {"V O C A B U L A R Y", "E N T E R P R I S E", "E F F I C I E N C Y",
                "U S A B I L I T Y", "E F F E C T I V E N E S S"};
        String[] blankWord = {"V O _ A B _ L A _ Y", "E _ _ E _ P R _ S E", "E _ F I _ I E _ C Y",
                "U _ A B _ L I _ Y", "E F _ _ C T _ V E _ _ S S"};
        String[] meaning = {"Từ vựng", "Nhà Máy", "HIệu quả",
                "Độ sử dụng", "Hiệu suất"};

        Random random = new Random();
        int n = random.nextInt(5);
        entity.setName(name[n]);
        entity.setId(n);
        entity.setWord(word[n]);
        entity.setBlankWord(blankWord[n]);
        entity.setMeaning(meaning[n]);
        entity.setType("n");

        result = (new Gson()).toJson(entity);

        return result;
    }
}
