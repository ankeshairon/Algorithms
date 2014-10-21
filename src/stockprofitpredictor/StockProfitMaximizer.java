package stockprofitpredictor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StockProfitMaximizer {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("src//stockprofitpredictor//test.txt")));
        Integer noOfTests = Integer.parseInt(br.readLine());
        for (int t=0; t<noOfTests; t++){
            Integer n = Integer.parseInt(br.readLine());
            Integer[] prices = new Integer[n];

            String[] ps = br.readLine().split(" ");
            for (int i = 0 ; i < n ; i++) {
                prices[i] = Integer.parseInt(ps[i]);
            }

            Integer[] maxFromEnd = new Integer[n];
            maxFromEnd[n-1] = prices[n-1];
            for (int i=n-2 ; i>=0; i-- ){
                if(prices[i] >= maxFromEnd[i+1]) {
                    maxFromEnd[i] = prices[i];
                } else {
                    maxFromEnd[i] = maxFromEnd[i+1];
                }
            }
            long profit = 0l;
            int sharesOwned=0;
            for (int i = 0 ; i < n ; i++) {
                if (prices[i] < maxFromEnd[i]){
                    sharesOwned++;
                    profit = profit - prices[i];
                } else if (prices[i] == maxFromEnd[i]) {
                    if (sharesOwned > 0) {
                        profit = profit + (long)sharesOwned * prices[i];
                        sharesOwned = 0;
                    }
                } else {
                    throw new AssertionError();
                }
            }
            System.out.println(profit);
        }
    }
}