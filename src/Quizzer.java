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
        int maxTime = 30;
        long timeRemaining = System.currentTimeMillis() + (maxTime * 1000);
        int indexPointer = 0;

        while(isPlaying==true)
        {
            System.out.println("Enter Symbol for Amino Acid: " + FULL_NAMES[indexPointer]);
            String aString = System.console().readLine().toUpperCase();
            String aChar = "" + aString.charAt(0);
            if(System.currentTimeMillis() >= timeRemaining)
            {
                System.out.println("Time is up!");
                isPlaying = false;
            }
            else if(aChar.equals(SHORT_NAMES[indexPointer]))
            {
                indexPointer++;
                System.out.println("Correct!");
            }
            else
            {
                System.out.println("Incorrect.");
                isPlaying = false;
            }
            System.out.println("Total Score: " + indexPointer);
            System.out.println("Time Remaining: " + ((timeRemaining/1000) - (System.currentTimeMillis()/1000)));
        }
    }
}
