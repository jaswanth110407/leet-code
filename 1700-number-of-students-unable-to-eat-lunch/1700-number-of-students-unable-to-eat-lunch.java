class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int count0 = 0;
        int count1 = 0;

        // Count students who prefer each sandwich type
        for (int student : students) {
            if (student == 0) {
                count0++;
            } else {
                count1++;
            }
        }

        // Process sandwiches from the top of the stack
        for (int sandwich : sandwiches) {
            if (sandwich == 0 && count0 > 0) {
                count0--;
            } else if (sandwich == 1 && count1 > 0) {
                count1--;
            } else {
                break;
            }
        }

        return count0 + count1;
    }
}
