package hciproject.datnh.englishquiz.entity;

public class WordQuizEntity {
    private int id;
    private String name;
    private String word;
    private String blankWord;

    public WordQuizEntity() {
    }

    public WordQuizEntity(int id, String name, String word, String blankWord) {
        this.id = id;
        this.name = name;
        this.word = word;
        this.blankWord = blankWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getBlankWord() {
        return blankWord;
    }

    public void setBlankWord(String blankWord) {
        this.blankWord = blankWord;
    }
}
