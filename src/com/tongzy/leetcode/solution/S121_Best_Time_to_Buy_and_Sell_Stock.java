package com.tongzy.leetcode.solution;

/**
 * 121. Best Time to Buy and Sell Stock
 * <p>
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 * <p>
 * Note that you cannot sell a stock before you buy one.
 * <p>
 * Example 1:
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 * <p>
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class S121_Best_Time_to_Buy_and_Sell_Stock {

    public int maxProfit(int[] prices) {
        int low = 0, high = 0;
        int i = 0;
        while (++i < prices.length) {
            if (prices[i] > prices[i - 1]) {
                low = prices[i - 1];
                high = prices[i];
                break;
            }
        }
        if (i >= prices.length) {
            return 0;
        }
        int lowCandidate = low;
        while (++i < prices.length) {
            if (prices[i] < lowCandidate) {
                lowCandidate = prices[i];
            } else if (prices[i] - lowCandidate > high - low) {
                low = lowCandidate;
                high = prices[i];
            }

        }
        return high - low;
    }
/*
    Runtime: 1 ms, faster than 78.39% of Java online submissions for Best Time to Buy and Sell Stock.
    Memory Usage: 36.8 MB, less than 100.00% of Java online submissions for Best Time to Buy and Sell Stock.
*/

    public static void main(String[] args) {
        int[] prices = {4, 7, 1, 2, 11};
        System.out.println(new S121_Best_Time_to_Buy_and_Sell_Stock().maxProfit(prices));
    }

    /**
     * official solution
     */
    public int maxProfit2(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }
/*
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Best Time to Buy and Sell Stock.
    Memory Usage: 36.1 MB, less than 100.00% of Java online submissions for Best Time to Buy and Sell Stock.
*/
}
