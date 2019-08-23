package com.CK;

import java.util.Deque;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

//Deque
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        if (n == 0) return new int[0];

        Deque<int[]> stack = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!stack.isEmpty() && stack.peekLast()[1] < nums[i]){
                stack.pollLast();
            }
            stack.offerLast(new int[]{i,nums[i]});
        }
        res[0] = stack.peekFirst()[1];

        for (int i = k; i < n; i++){
            while (!stack.isEmpty() && stack.peekLast()[1] < nums[i]){
                stack.pollLast();
            }
            stack.offerLast(new int[]{i,nums[i]});

            if (stack.peekFirst()[0] == (i - k)){
                stack.pollFirst();
            }

            res[i - k + 1] = stack.peekFirst()[1];
        }

        return res;
    }
}

//DP
class Solution2 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        int [] left = new int[n];
        left[0] = nums[0];
        int [] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) left[i] = nums[i];  // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }
}


