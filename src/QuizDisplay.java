import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.*;


public class QuizDisplay extends JFrame
{

    public static String[] SHORT_NAMES =
            { "A","R", "N", "D", "C", "Q", "E",
                    "G",  "H", "I", "L", "K", "M", "F",
                    "P", "S", "T", "W", "Y", "V"
            };

    public static String[] FULL_NAMES =
            {
                    "alanine","arginine", "asparagine",
                    "aspartic acid", "cysteine",
                    "glutamine",  "glutamic acid",
                    "glycine" ,"histidine","isoleucine",
                    "leucine",  "lysine", "methionine",
                    "phenylalanine", "proline",
                    "serine","threonine","tryptophan",
                    "tyrosine", "valine"
            };

    // New runnable to check remaining time and report it to user in real time
    public class timeClock implements Runnable{
            public void run()
            {

                long endTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + 30;

                while(TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis())) <= endTime)
                {
                    if(endTime <= TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis())) )
                    {
                        answerText.setEditable(false);
                        statusText.setText("Time up!");
                        timeRemaining = 0;
                        timeRemainingField.setText("0");
                    }
                    else
                    {
                        try
                        {
                            timeRemaining = endTime - TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis()));
                            timeRemainingField.setText(String.valueOf(timeRemaining));
                            Thread.sleep(1000);
                        }
                        catch(InterruptedException e)
                        {
                        }
                    }

                }
            }
    }

    long timeRemaining = 0;
    int indexPointer = 0;
    int pointsCorrect =  0;
    int pointsIncorrect = 0;

    Random rand = new Random();

    private static final long serial = -524526543L;
    private String answer = "";
    private JLabel aminoLabel = new JLabel("Amino Acid Symbol: ");
    private JLabel consoleLabel = new JLabel("Instructions: ");
    private JLabel infoLabel = new JLabel("Right || Wrong || Time");
    private TextField answerText = new TextField();
    private TextArea statusText = new TextArea();
    private TextField totalRight = new TextField();
    private TextField totalWrong = new TextField();
    private TextField timeRemainingField = new TextField();
    private JButton startButton = new JButton("Start");
    private JButton cancelButton = new JButton("Cancel");


    // Action Listener for the start button. Starts new timeClock thread and unlocks answer text
    private class startQuiz implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            indexPointer = rand.nextInt(20);

            pointsCorrect = 0;
            pointsIncorrect = 0;
            totalRight.setText("0");
            totalWrong.setText("0");
            statusText.setText("Enter Symbol for Amino Acid: " + FULL_NAMES[indexPointer]);
            answerText.setEditable(true);

            timeClock tc = new timeClock();
            new Thread(tc).start();
        }
    }

    // New ActionListener that checks the user input for correct answers and updates stats accordingly
    private class checkAnswer implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

            answer = answerText.getText().toUpperCase();

            if(answer.equals(SHORT_NAMES[indexPointer]))
            {
                pointsCorrect += 1;
                totalRight.setText(String.valueOf(pointsCorrect));
                statusText.setText("Correct!");
            }
            else
            {
                pointsIncorrect += 1;
                totalWrong.setText(String.valueOf(pointsIncorrect));
                statusText.setText("Incorrect...");
            }
            indexPointer = rand.nextInt(20);
            answerText.setText("");
            statusText.setText("Enter Symbol for Amino Acid: " + FULL_NAMES[indexPointer]);
        }
    }

    // Action Listener for the cancel button that stops the quiz
    private class cancelQuiz implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            statusText.setText("Quiz cancelled with " + timeRemaining + " seconds left.");
            answerText.setEditable(false);

        }
    }


    private QuizDisplay()
    {
        super("Enterprise Control Panel");
        setSize(500,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(cancelButton);

        JPanel destPanel = new JPanel();
        destPanel.add(aminoLabel);
        destPanel.add(answerText);

        JPanel consPanel = new JPanel();
        consPanel.add(consoleLabel);
        consPanel.add(statusText);

        JPanel infoPanel = new JPanel();
        infoPanel.add(infoLabel);
        infoPanel.add(totalRight);
        infoPanel.add(totalWrong);
        infoPanel.add(timeRemainingField);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(destPanel, BorderLayout.CENTER);
        getContentPane().add(consPanel, BorderLayout.NORTH);
        getContentPane().add(infoPanel, BorderLayout.EAST);
        startButton.addActionListener(new startQuiz());
        cancelButton.addActionListener(new cancelQuiz());
        answerText.addActionListener(new checkAnswer());
        answerText.setEditable(false);
        answerText.setColumns(2);
        totalRight.setColumns(2);
        totalRight.setText("0");
        totalRight.setEditable(false);
        totalWrong.setColumns(2);
        totalWrong.setText("0");
        totalWrong.setEditable(false);
        timeRemainingField.setColumns(4);
        timeRemainingField.setText("30");
        timeRemainingField.setEditable(false);
        statusText.setText("Type in the correct Amino Acid for: ");
        statusText.setEditable(false);

        setVisible(true);
    }

    public static void main(String [] args)
    {
        new QuizDisplay();
    }

}