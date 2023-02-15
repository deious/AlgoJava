package a0111.camp;

import java.io.*;
import java.util.*;

public class Solution_d1_2072_홀수만더하기_서울_20반_김한성 {

	public static void main(String args[]) throws Exception
    {
		System.setIn(new FileInputStream("res/input_d1_2072.txt"));
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
 
        int[] arr = new int[10];
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int answer = 0;
             
            for (int i = 0; i < 10; i++) 
            {
                arr[i] = sc.nextInt();
                if (arr[i] % 2 == 1)
                {
                    answer += arr[i];
                }
            }
             
            System.out.println("#" + test_case + " " + answer);
 
        }
    }

}
