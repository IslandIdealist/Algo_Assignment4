/**
 * CISC 380
 * Algorithms Assignment 4
 *
 * Implements dynamic programming solutions to see if a subset adds up to a value.
 *
 * @author Matt Jerrard and Jesse Smrekar
 * Due Date: 04/03/2020
 */

public class DynamicSum{

    public static Boolean[] array;

    public DynamicSum(){
        //YOUR CODE HERE
    }//constructor

    /**
     *Checks to see if a subset of arr adds up to exactly t with an iterative solution.
     *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return True, if a subset adds up to t, false otherwise.
     *
     *
     * The runtime of this function is defined by the calculateTableBoolean() function that is called if the base case is not reached.
     * There is a for loop that runs the length of the target number many times, and a nest for loop inside that will run the length
     * of the given array many times. There are constant time operations inside of the loops, and then these loops are repeated again.
     * This gives us an overall running time of O(row*column) in the worst case. [row=target-1, column=length of array-1]
     *
     * This running time will also be reached in the best case because these loops will still run row*column many times.
     *
     * This gives us an overall running time of O(row*column)
     */
    public boolean isSum(int[] arr, int t)
    {

        if(arr.length == 0) //base case if the array is of length 0
        {
            return false;
        }
        else
        {
            Boolean[][] boolTable = new Boolean[arr.length][t];
            return calculateTableBoolean(boolTable, arr, arr.length-1, t-1);
        }

    }//isSum

    public boolean calculateTableBoolean( Boolean[][] boolTable, int[] arr, int column, int row )
    {

        //base case -- populate table by looping through all possible target values, the first column of the table is the base case

        for( int i = 1;  i <= row; i++ )
        {

            for( int j = 1; j <= column; j++ )
            {

                if( arr[j-1] == i )
                {
                    boolTable[1][i] = true;
                    break;
                }
                else
                {
                    boolTable[1][i] = false;
                }

            }

        }

        //finish populating the table, modeled off of our sub problem

        for( int i = 2; i <= column; i++ )
        {

            for( int j = 1; j <= row; j++ )
            {

                if( (j - arr[i-1]) > 0 )
                {
                    boolTable[i][j] = (boolTable[i-1][j-arr[i-1]] || boolTable[i-1][j]);
                }
                else
                {
                    boolTable[i][j] = boolTable[i-1][j];
                }

            }

        }

        return boolTable[column][row]; // returns the bottom right boolean value
    }

    public Boolean calculateTableMemoization(Boolean[] array, int[] arr, int start, int newTarget)
    {
        if(start >= arr.length)
        {
            return false;
        }

        if( arr[start] == newTarget )
        {
            return true;
        }

        array[start] =  ( (calculateTableMemoization(array, arr, start+1, newTarget - arr[start])) || (calculateTableMemoization(array, arr, start+1, newTarget)) );

        return array[start];

    }//calculateTableMemoization

    /**
     *Checks to see if a subset of arr adds up to exactly t with a memoizied solution.
     *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return    True, if a subset adds up to t, false otherwise.
     *
     *
     * The runtime of this function is defined by the calculateTableMemoization() function that is called.
     * The calculateTableMemoization() function will run n*n many times because there are two recursive calls
     * In the worst case there will be n*n many calls.
     *
     * In the best case, there will also be n*n many calls since the recursive calls will run n*n many times
     *
     * This gives us an overall running time of O(n^2)
     *
     */

    public boolean isSumMem(int[] arr, int t )
    {

        Boolean[] array = new Boolean[arr.length];

        return calculateTableMemoization(array, arr, 0, t);

    }//isSumMem

    /**
     * Recovers the subset of arr that adds up to t, if it exists.
     *
     * @param arr the array of integers to take subsets from.
     * @param t   the value the subset could add up to.
     * @return a subset of arr that adds up to t, null otherwise.
     *
     */
    public int[] getSubset(int[] arr, int t){

        /*if(arr.length == 0) //base case if the array is of length 0
        {
            return false;
        }
        else
        {
            Integer[][] table = calculateTableMemoization(arr, t); //create the table

            for(int i = 0; i < table.length; i++) //loop through each row
            {
                for(int j = 0; j < table[i].length; j++) //loop through each column
                {
                    if(table[i][j] != null)
                    {
                        if ( table[i][j] == t ) //check current value against the target, return true if they match
                        {
                            return true;
                        }
                    }

                }
            }
        }*/



        return null;
    }//getSubset

}//class