import java.util.*;

class Solution {

    class Window {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        long sumLeft = 0, sumRight = 0;
        int sizeLeft = 0, sizeRight = 0;

        void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                left.merge(val, 1, Integer::sum);
                sumLeft += val;
                sizeLeft++;
            } else {
                right.merge(val, 1, Integer::sum);
                sumRight += val;
                sizeRight++;
            }
            balance();
        }

        void remove(int val) {
            if (left.containsKey(val)) {
                removeValue(left, val);
                sumLeft -= val;
                sizeLeft--;
            } else {
                removeValue(right, val);
                sumRight -= val;
                sizeRight--;
            }
            balance();
        }

        void removeValue(TreeMap<Integer, Integer> map, int val) {
            int count = map.get(val);
            if (count == 1) map.remove(val);
            else map.put(val, count - 1);
        }

        void balance() {
            int target = (sizeLeft + sizeRight + 1) / 2;

            while (sizeLeft > target) {
                int val = left.lastKey();
                removeValue(left, val);
                sumLeft -= val;
                sizeLeft--;

                right.merge(val, 1, Integer::sum);
                sumRight += val;
                sizeRight++;
            }

            while (sizeLeft < target) {
                int val = right.firstKey();
                removeValue(right, val);
                sumRight -= val;
                sizeRight--;

                left.merge(val, 1, Integer::sum);
                sumLeft += val;
                sizeLeft++;
            }
        }

        long getCost() {
            long median = left.lastKey();

            return median * sizeLeft - sumLeft
                 + sumRight - median * sizeRight;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        if ((long) x * k > n) return -1;

        int windows = n - x + 1;
        long[] cost = new long[windows];

        Window window = new Window();

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        cost[0] = window.getCost();

        for (int i = x; i < n; i++) {
            window.remove(nums[i - x]);
            window.add(nums[i]);

            cost[i - x + 1] = window.getCost();
        }

        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {
                curr[i] = curr[i - 1];

                if (i >= x && prev[i - x] != INF) {
                    long take = prev[i - x] + cost[i - x];
                    curr[i] = Math.min(curr[i], take);
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n] == INF ? -1 : prev[n];
    }
}