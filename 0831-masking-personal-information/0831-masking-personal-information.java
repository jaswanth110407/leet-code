class Solution {
    public String maskPII(String s) {

        // Email address
        if (s.contains("@")) {
            s = s.toLowerCase();

            int at = s.indexOf('@');

            String first = s.substring(0, 1);
            String last = s.substring(at - 1, at);

            String domain = s.substring(at);

            return first + "*****" + last + domain;
        }

        // Phone number
        StringBuilder digits = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                digits.append(ch);
            }
        }

        int n = digits.length();
        int countryCode = n - 10;

        String lastFour = digits.substring(n - 4);

        if (countryCode == 0) {
            return "***-***-" + lastFour;
        }

        StringBuilder result = new StringBuilder("+");

        for (int i = 0; i < countryCode; i++) {
            result.append("*");
        }

        result.append("-***-***-").append(lastFour);

        return result.toString();
    }
}