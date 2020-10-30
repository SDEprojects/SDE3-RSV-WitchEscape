package com.question.answer;

import com.question.answer.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionUtil {
    private static List<Question> questions = null;

    /*
    * Create questions and return a list of Questions.
    *
     */
    public static List<Question> getQuestions() {
        if (questions == null) {
            questions = new ArrayList<Question>();
            questions.add(createQuestion1());
            questions.add(createQuestion2());
            questions.add(createQuestion3());
            questions.add(createQuestion4());
        }
        return questions;
    }

    private static Question createQuestion1() {
        String question = "What occurs once in a minute, twice in a moment and never in thousand years?";
        Question q = new Question(question);
        q.addChoice("Having a baby");
        q.addChoice("Getting Rich");
        q.addChoice("Life");
        q.addChoice("The letter 'M'", true);
        return q;
    }

    private static Question createQuestion2() {
        String question = "How can you place a pencil on the floor so no one can see it?";

        Question q = new Question(question);
        q.addChoice("Put it  vertically");
        q.addChoice("Put it horizontally");
        q.addChoice("Next to the Wall", true);
        q.addChoice("Have it face the light");
        return q;
    }

    private static Question createQuestion3() {
        String question = "You throw way the outside, eat the inside, and throw away the inside. What am I?";

        Question q = new Question(question);
        q.addChoice("Corn on a cob", true);
        q.addChoice("A hotdog");
        q.addChoice("A lollipop");
        q.addChoice("Yogurt");
        return q;
    }

    private static Question createQuestion4() {
        String question = "When asked how ild she is, Karen replied, In 2 years I will be twice as old as I was 5 years ago. How old is she now?";

        Question q = new Question(question);
        q.addChoice("11");
        q.addChoice("12", true);
        q.addChoice("13");
        q.addChoice("14");
        return q;
    }

}
