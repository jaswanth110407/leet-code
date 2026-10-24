import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public boolean isPossible(int[] target) {
        PriorityQueue<Integer> maxHeap =
            new PriorityQueue<>(Collections.reverseOrder());

        long sum = 0;

        for (int num : target) {
            maxHeap.offer(num);
            sum += num;
        }

        while (true) {
            int largest = maxHeap.poll();
            long remaining = sum - largest;

            if (largest == 1 || remaining == 1) {
                return true;
            }

            if (remaining == 0 || largest <= remaining) {
                return false;
            }

            long previous = largest % remaining;

            if (previous == 0) {
                return false;
            }

            sum = remaining + previous;
            maxHeap.offer((int) previous);
        }
    }
}
