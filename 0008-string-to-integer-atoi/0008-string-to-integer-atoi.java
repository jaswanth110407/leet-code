
class Solution {
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();

        // 1. Ignore leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 2. Check the sign
        int sign = 1;

        if (i < n && s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (i < n && s.charAt(i) == '+') {
            i++;
        }

        // 3. Convert digits to integer
        long result = 0;

        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            result = result * 10 + digit;

            // 4. Handle 32-bit integer overflow
            if (sign * result <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            if (sign * result >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            i++;
        }

        return (int) (sign * result);
    }
}
