package server;

public class Question {
    private final String text;
    private final String correctAnswer;

    public Question(String text, String correctAnswer) {
        this.text = text;
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public boolean checkAnswer(String answer) {
        if (answer == null) return false;
        return this.correctAnswer.trim().equalsIgnoreCase(answer.trim());
    }

    @Override
    public String toString() {
        return text;
    }
}