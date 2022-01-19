
/**
 * In Text Twist, players try to score points by forming words using the letters from a 6-letter scrambled word. 
 * They win the round if they can successfully unscramble the 6-letter word.

Create a function that takes in an array of already-guessed words, 
the unscrambled 6-letter word and returns the total number of points the player scored in a particular round using the following rubric:

3-letter words are 1 pt
4-letter words are 2 pts
5-letter words are 3 pts
6-letter words are 4 pts + 50 pt bonus (for unscrambling the word)
Remember that invalid words (words that cannot be formed from the 6-letter unscrambled words) count as 0 pts.

Examples
totalPoints(["cat", "create", "sat"], "caster") ➞ 2
// Since "create" is an invalid word.

totalPoints(["trance", "recant"], "recant") ➞ 108
// Since "trance" and "recant" score 54 pts each.

totalPoints(["dote", "dotes", "toes", "set", "dot", "dots", "sted"], "tossed") ➞ 13
// Since 2 + 3 + 2 + 1 + 1 + 2 + 2 = 13

Notes:
If a 6-letter word has multiple anagrams, count each 6-letter unscramble as an additional 54 pts. 
For example, if the word is arches, and the player guessed arches and chaser, add 108 pts for both words.
 */

import java.util.*;

class TextTwist {
    public static int totalPoints(String[] words, String input) {
        int points = 0;
        int totalPoint = 0;

        input = input.toLowerCase();

        int[] characterCountInput = new int[128];
        for (int i = 0; i < input.length(); i++) {
            ++characterCountInput[input.charAt(i)];
        }

        for (int j = 0; j < words.length; j++) {
            String word = words[j].toLowerCase();

            int[] characterCountWords = new int[128];
            for (int i = 0; i < word.length(); i++) {
                ++characterCountWords[word.charAt(i)];
            }

            boolean isValid = true;

            for(int x = 0; x <= 127; x++){
                if(characterCountInput[x] < characterCountWords[x]){
                    isValid = false;
                }
            }

            if (isValid) {
                switch (word.length()) {
                    case 3:
                        points = 1;
                        break;
                    case 4:
                        points = 2;
                        break;
                    case 5:
                        points = 3;
                        break;
                    case 6:
                        points = 54;
                        break;
                }    
            }else{
                points = 0;
            }

            totalPoint += points;
        }

        return totalPoint;
    }

    public static void main(String[] args) {
        System.out.println(totalPoints(new String[] { "cat", "create", "sat" }, "caster")); // ➞ 2
        // Since "create" is an invalid word.

        System.out.println(totalPoints(new String[] { "trance", "recant" }, "recant")); // ➞ 108
        // Since "trance" and "recant" score 54 pts each.

        System.out.println(totalPoints(new String[] { "dote", "dotes", "toes", "set", "dot", "dots", "sted" }, "tossed")); // ➞13
        // Since 2 + 3 + 2 + 1 + 1 + 2 + 2 = 13
    }
}