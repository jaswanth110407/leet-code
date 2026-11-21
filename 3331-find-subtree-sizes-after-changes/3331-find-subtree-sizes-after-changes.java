
import java.util.*;

class Solution {
    List<Integer>[] children;
    int[] newParent;
    int[] answer;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        children = new ArrayList[n];
        newParent = new int[n];
        answer = new int[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
            newParent[i] = parent[i];
        }

        // Build the original tree
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        // Find the closest ancestor with the same character
        int[] last = new int[26];
        Arrays.fill(last, -1);

        dfs(0, s, last);

        // Build the modified tree
        List<Integer>[] newChildren = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            newChildren[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newChildren[newParent[i]].add(i);
        }

        // Calculate subtree sizes
        Arrays.fill(answer, 0);
        calculateSize(0, newChildren);

        return answer;
    }

    void dfs(int node, String s, int[] last) {
        int ch = s.charAt(node) - 'a';

        int old = last[ch];

        if (old != -1) {
            newParent[node] = old;
        }

        last[ch] = node;

        for (int child : children[node]) {
            dfs(child, s, last);
        }

        last[ch] = old;
    }

    int calculateSize(int node, List<Integer>[] tree) {
        int size = 1;

        for (int child : tree[node]) {
            size += calculateSize(child, tree);
        }

        answer[node] = size;
        return size;
    }
}
