import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
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

    Thread timer = new Thread(new Runnable() {
        public void run()
        {
            if(System.currentTimeMillis() >= timeRemaining)
            {
                
            }
        }});

    public long timeRemaining = 0;

    private static final long serial = -524526543L;
    private boolean isAboard = true;
    private String destination = "";
    private JLabel destinationLabel = new JLabel("Amino Acid Symbol: ");
    private JLabel consoleLabel = new JLabel("Instructions: ");
    private JLabel infoLabel = new JLabel("Right || Wrong || Time");
    private TextField answerText = new TextField();
    private TextArea statusText = new TextArea();
    private TextField totalRight = new TextField();
    private TextField totalWrong = new TextField();
    private TextField timeRemaining = new TextField();
    private JButton startButton = new JButton("Start");
    private JButton cancelButton = new JButton("Cancel");


    private class startQuiz implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            Random rand = new Random();
            int indexPointer = rand.nextInt(20);

            statusText.setText("Enter Symbol for Amino Acid: " + FULL_NAMES[indexPointer]);

            timeRemaining = System.currentTimeMillis() + (30 * 1000);
        }
    }

    private class getDestination implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

            destination = answerText.getText();
        }
    }

    private class retCrew implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(isAboard == true)
            {
                statusText.setText("The crew is already aboard and quite bored. Try sending them somewhere.");
            }
            else
            {
                statusText.setText("Bringing the crew back with haste!");
                isAboard = true;
            }

        }
    }


    private QuizDisplay()
    {
        super("Enterprise Control Panel");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(cancelButton);

        JPanel destPanel = new JPanel();
        destPanel.add(destinationLabel);
        destPanel.add(answerText);

        JPanel consPanel = new JPanel();
        consPanel.add(consoleLabel);
        consPanel.add(statusText);

        JPanel infoPanel = new JPanel();
        infoPanel.add(infoLabel);
        infoPanel.add(totalRight);
        infoPanel.add(totalWrong);
        infoPanel.add(timeRemaining);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(destPanel, BorderLayout.CENTER);
        getContentPane().add(consPanel, BorderLayout.NORTH);
        getContentPane().add(infoPanel, BorderLayout.EAST);
        startButton.addActionListener(new startQuiz());
        cancelButton.addActionListener(new retCrew());
        answerText.addActionListener(new getDestination());
        answerText.setColumns(2);
        totalRight.setColumns(2);
        totalRight.setText("0");
        totalRight.setEditable(false);
        totalWrong.setColumns(2);
        totalWrong.setText("0");
        totalWrong.setEditable(false);
        timeRemaining.setColumns(4);
        timeRemaining.setText(":30");
        timeRemaining.setEditable(false);
        statusText.setText("Type in the correct Amino Acid for: ");
        statusText.setEditable(false);

        setVisible(true);
    }

    public static void main(String [] args)
    {
        new QuizDisplay();
    }

}