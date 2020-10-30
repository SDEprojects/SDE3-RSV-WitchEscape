package com.question.answer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QuestionDialog extends JDialog {
    private String selectedAnswer;
    private SubmitHandler buttonHandler = new SubmitHandler();
    private Question question;

    /**
     *
     * @param owner
     * @param title
     * @param modalityType
     */
    public QuestionDialog(Window owner, String title, ModalityType modalityType, Question question) {
        super(owner, title, modalityType);
        this.question = question;

        JPanel questionPanel = createQuestionPanel();

        this.add(questionPanel, BorderLayout.CENTER);
        this.add(getButtonPanel(), BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(owner);
        //setVisible(true);

    }

    /**
     * Create Button panel
     *
     * @return
     */
    private JPanel getButtonPanel() {
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(buttonHandler);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        return buttonPanel;
    }

    /**
     * Creates panel with question, and answers.
     *
     * @return
     */
    private JPanel createQuestionPanel() {
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.PAGE_AXIS));
        questionPanel.add(new JLabel(question.getQuestion()));

        // Group the radio buttons.
        ButtonGroup group = new ButtonGroup();

        List<String> choices = question.getChoices();
        for (String choice : choices) {
            JRadioButton rb = new JRadioButton(choice);
            rb.addActionListener(new SelectionHandler());
            rb.setActionCommand(choice);
            questionPanel.add(rb);
            group.add(rb);
        }
        return questionPanel;
    }


    public boolean isCorrectAnswer() {
        return question.isCorrectAnswer(selectedAnswer);
    }

    //Even handler for submit button
    class SubmitHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // dispose the current dialog from the view.
            QuestionDialog.this.dispose();
        }
    }

    //Even handler for radio button
    class SelectionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedAnswer = e.getActionCommand();
        }

    }
}