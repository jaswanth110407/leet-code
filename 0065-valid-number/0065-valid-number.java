class Solution {
    public boolean isNumber(String s) {
        boolean digit = false;
        boolean dot = false;
        boolean exp = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                digit = true;
            } else if (c == '+' || c == '-') {
                if (i > 0 && s.charAt(i - 1) != 'e'
                        && s.charAt(i - 1) != 'E') {
                    return false;
                }
                if (i > 0 && s.charAt(i - 1) == 'e'
                        || i > 0 && s.charAt(i - 1) == 'E') {
                    // Sign is allowed immediately after exponent.
                } else if (i != 0) {
                    return false;
                }
                digit = false;
            } else if (c == '.') {
                if (dot || exp) {
                    return false;
                }
                dot = true;
            } else if (c == 'e' || c == 'E') {
                if (exp || !digit) {
                    return false;
                }
                exp = true;
                digit = false;
                dot = true; // Prevent decimal points after exponent.
            } else {
                return false;
            }
        }

        return digit;
    }
}