import java.util.Random;

public class Quizzer
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

    public static void main(String[] args)
    {
        boolean isPlaying = true;
        System.out.println("Please enter a time limit: ");
        int maxTime = Integer.parseInt(System.console().readLine());
        long timeRemaining = System.currentTimeMillis() + (maxTime * 1000);
        int points = 0;

        while(isPlaying==true)
        {
            Random rand = new Random();
            int indexPointer = rand.nextInt(20);

            System.out.println("Enter Symbol for Amino Acid: " + FULL_NAMES[indexPointer]);
            String aString = System.console().readLine().toUpperCase();
            String aChar = "" + aString.charAt(0);
            if(System.currentTimeMillis() >= timeRemaining)
            {
                System.out.println("You're too slow!");
                isPlaying = false;
            }
            else if(aChar.equals(SHORT_NAMES[indexPointer]))
            {
                System.out.println("Correct!");
                points++;
            }
            else
            {
                System.out.println("Incorrect. The symbol for " + FULL_NAMES[indexPointer] + " is " + SHORT_NAMES[indexPointer]);
                isPlaying = false;
            }
            System.out.println("Total Score: " + points);
            System.out.println("Time Remaining: " + ((timeRemaining/1000) - (System.currentTimeMillis()/1000)));
        }
    }
}
