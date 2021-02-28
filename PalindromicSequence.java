/**
 * CISC 380
 * Algorithms Assignment 4 EXTRA CREDIT
 *
 * Implements a dynamic programming solution to find the length of the longest palindromic subsequence.
 *
 * @author Jesse Smrekar & Matt Jerrard
 * Due Date: xx/xx/xx
 */
import java.lang.*;


public class PalindromicSequence{

    /**
     * Implements a dynamic programming solution to find the length of the longest Palindromic subsequence of the given string.
     *
     *
     * @param x the string that may contain a palindromic subsequence
     * @return the length of the longest palindromic subsequence, or 0 if there is none.
     */
    public static int getLengthLongestPalindrome(String x){

        /** Answer array will look like this:
         * [ 0 | X | X | X ...]
         * [ 0 | 0 | X | X ...]
         * [ 0 | 0 | 0 | X ...]
         * ...
         */

        int[][] answerArray = new int[x.length()][x.length()];

        getLengthRecursive(x, 0, x.length()-1, answerArray);
        

        return answerArray[0][x.length()-1];

    }//longestPalindrome



    private static int getLengthRecursive( String x, int start, int end, int[][] table){

        // If the value in the table we are looking for
        // is already calculated.
        //System.out.println("start: " + start + " end: " + end );

        if(start >= end) return 0;

        if(table[start][end] != 0) return table[start][end];


        // If we need to calculate the value
        //see if there is a duplicate letter earlier in the string

        for(int i=start; i<end; i++){

            if( x.charAt(i) == x.charAt(end) ){

                int insideCount = 0;
                for(int j=i+1; j<end; j++){

                    if( x.charAt(j) == x.charAt(end) ) insideCount ++;
                }

                if(insideCount % 2 == 1){
                    table[start][end] =  Math.max( getLengthRecursive(x, i+1, end, table) + 1, getLengthRecursive(x, i, end-1, table) + 1 );

                }
                else{
               table[start][end] = Math.max( getLengthRecursive(x, start, end-1, table), getLengthRecursive(x, i+1, end, table) + 2);
                }

                return table[start][end];
            }
        }

        table[start][end] = Math.max( 1, getLengthRecursive(x, start, end-1, table) );

        return table[start][end];

    }// returns longest palindrome


    /**
     * Implements a dynamic programming solution to return the longest palindromic subsequence of the given string 
     * @param x the string that may contain a palindromic subsequence
     * @return the string of the longest palindrome, or null if there is none
     */
    public static String getLongestPalindrome(String x) {

        String letters = "";
        String result = "";

        int[][] answerArray = new int[x.length()][x.length()];

        getLengthRecursive(x, 0, x.length()-1, answerArray);

        boolean found = false;
        boolean singleMiddle = false;
        int currMax = answerArray[x.length()-1][x.length()-1];
        int i = 0;
        int j = x.length()-1;

        while(!found){

            while( true ){

                if(j<1) break;

                if(answerArray[i][j-1] == answerArray[i][j]){
                    j--;
                }
                else break;
            }

            letters += x.charAt(i);
            i++;

            if(answerArray[i][j] == 0){
                found = true;

                if(answerArray[i-1][j] == 1){
                    singleMiddle = true;
                }
            }
        }

        result = letters;
        if(singleMiddle){
            result = result.substring(0, result.length()-1);
        }

        for(int k=letters.length()-1; k>=0; k--){

            result += letters.charAt(k);
        }

        return result;
    }

}//class