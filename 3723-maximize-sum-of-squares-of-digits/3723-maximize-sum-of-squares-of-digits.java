
class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num) {
            return "";
        }

        int r = sum % 9;

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < sum / 9; i++) {
            ans.append('9');
        }

        if (r > 0) {
            ans.append((char) ('0' + r));
        }

        while (ans.length() < num) {
            ans.append('0');
        }

        return ans.toString();
    }
}
