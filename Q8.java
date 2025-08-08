/*A warehouse stores products in multiple bins arranged in a single row. Each bin contains a certain number of items.
The warehouse receives daily order requests, and for each order, workers have to pick consecutive bins so that the total number of items picked exactly matches the order quantity.

However, due to restrictions:

Workers cannot pick from more than K bins for a single order.

Workers must choose bins in their current order (no reordering allowed).

If multiple sets of bins satisfy the order, choose the one with the smallest starting bin index.

Your task is to find the start and end index of the bins from which the workers should pick items for each order.
If it is not possible to fulfill the order under these constraints, return -1 -1.

Test case 01

bins = [5, 2, 7, 3, 1, 5, 2]
orderQuantity = 8
K = 3
Output: 5 7


Test case 02 

bins = [1, 2, 3, 4, 5]
orderQuantity = 9
K = 2
Output: 4 5

Test Case 03:

bins = [4, 4, 4, 4]
orderQuantity = 8
K = 2
Output: 1 2 
*/

import java.util.*;
public class Q8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] bins = new int[n];
        for (int i = 0; i < n; i++) {
            bins[i] = sc.nextInt();
        }
        int orderQuantity = sc.nextInt();
        int K = sc.nextInt();
        System.out.println(findBins(bins, orderQuantity, K));
    }

    private static String findBins(int[] bins, int orderQuantity, int K) {
        int n = bins.length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n && j < i + K; j++) {
                sum += bins[j];
                if (sum == orderQuantity) {
                    return (i + 1) + " " + (j + 1);
                }
            }
        }
        return "-1 -1";
    }
}
