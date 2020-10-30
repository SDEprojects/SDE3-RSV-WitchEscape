package com.question.answer;

import java.util.ArrayList;
import java.util.List;

/*
*Generate Question, with choices and Correct Answer
*
 */
public class Question {
    private String question;
    private List<String> choices = new ArrayList<String>();
    private String correctAnswer;

    // Constructor

    public Question(String question) {
        this.question = question;
    }

    // Getters and Setters

    public String getQuestion() {
        return question;
    }

    public List<String> getChoices() {
        return choices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /*
    *Adds the given choice
    * @param choice
     */

    public void addChoice(String choice) {
        this.addChoice(choice, false);
    }

    /*
    * Adds the given choice and if the isCorrrectAnswwer is true
    * then sets the correct answer to this choice
     */
    public void addChoice(String choice, boolean isCorrectAnswer) {
        this.choices.add(choice);
        if (isCorrectAnswer) {
            this.correctAnswer = choice;
        }
    }

    /*
    * Returns true, if the given answer is correct, and false otherwise
    * @param selectedAnswer
    * @return
     */
    public boolean isCorrectAnswer(String selectedAnswer) {
        return this.correctAnswer.equals(selectedAnswer);
    }
}
