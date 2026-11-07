import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        // Free servers: weight, index
        PriorityQueue<int[]> free = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[1], b[1]);
            }
        );

        // Busy servers: available time, weight, index
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Long.compare(a[0], b[0]);
                }
                if (a[1] != b[1]) {
                    return Long.compare(a[1], b[1]);
                }
                return Long.compare(a[2], b[2]);
            }
        );

        // Initially all servers are free
        for (int i = 0; i < n; i++) {
            free.offer(new int[]{servers[i], i});
        }

        long time = 0;

        for (int j = 0; j < m; j++) {

            // Maintain global time
            time = Math.max(time, j);

            // Move all available servers to free queue
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] server = busy.poll();

                free.offer(new int[]{
                    (int) server[1],
                    (int) server[2]
                });
            }

            // Wait if no server is free
            if (free.isEmpty()) {
                time = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] server = busy.poll();

                    free.offer(new int[]{
                        (int) server[1],
                        (int) server[2]
                    });
                }
            }

            // Select server with minimum weight and index
            int[] server = free.poll();

            int weight = server[0];
            int index = server[1];

            ans[j] = index;

            // Update server's next available time
            busy.offer(new long[]{
                time + tasks[j],
                weight,
                index
            });
        }

        return ans;
    }
}