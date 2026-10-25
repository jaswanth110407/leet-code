class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        int star = -1, match = 0;

        while (i < s.length()) {
            // Match a single character or '?'
            if (j < p.length() &&
                (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                i++;
                j++;
            }
            // Remember the position of '*'
            else if (j < p.length() && p.charAt(j) == '*') {
                star = j;
                match = i;
                j++;
            }
            // Try matching one more character with '*'
            else if (star != -1) {
                j = star + 1;
                match++;
                i = match;
            }
            // No match found
            else {
                return false;
            }
        }

        // Skip remaining '*' characters
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }

        return j == p.length();
    }
}
