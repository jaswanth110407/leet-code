class Solution {
    public String longestCommonPrefix(String[] strs) {

        // Take the first string as the starting prefix
        String prefix = strs[0];

        // Compare with every other string
        for (int i = 1; i < strs.length; i++) {

            // Keep removing characters until it matches
            while (!strs[i].startsWith(prefix)) {

                prefix = prefix.substring(0, prefix.length() - 1);

                // No common prefix
                if (prefix.length() == 0) {
                    return "";
                }
            }
        }

        return prefix;
    }
}