package programmers.Practice.Level4;

import java.util.ArrayList;

public class Autocomplete {
    private static class Node {
        private final char c;
        private int cnt;
        ArrayList<Node> child;

        public Node(char c) {
            this.c = c;
            this.cnt = 0;
            this.child = new ArrayList<>();
        }

        public Node getNextNode(char c) {
            for (Node cn: child) {
                if (cn.c == c) {
                    return cn;
                }
            }
            return null;
        }

        public void increaseCnt() {
            this.cnt++;
        }

        public void printNode() {
            System.out.println(c + " " + cnt);
        }
    }

    private static class Tree {
        private final Node[] root;

        public Tree() {
            root = new Node[26];
            for (int i = 0; i < root.length; i++) {
                char c = (char) ('a' + i);
                root[i] = new Node(c);
            }
        }

        private Node getRootNode(String word) {
            int rootIdx = word.charAt(0) - 'a';
            return root[rootIdx];
        }

        public void add(String word) {
            Node cur = getRootNode(word);
            cur.increaseCnt();
            for (int i = 1; i < word.length(); i++) {
                char c = word.charAt(i);
                Node nextNode = cur.getNextNode(c);
                if (nextNode == null) {
                    nextNode = new Node(c);
                    cur.child.add(nextNode);
                }
                nextNode.increaseCnt();
                cur = nextNode;
            }
        }

        public int find(String word) {
            Node cur = getRootNode(word);
            int depth = 1;
            for (int i = 1; i < word.length(); i++) {
                if (cur == null || cur.cnt == 1) break;
                char c = word.charAt(i);
                cur = cur.getNextNode(c);
                depth++;
            }
            return depth;
        }

        public void printTree() {
            for (Node rootNode: root) {
                if (rootNode.cnt > 0) {
                    printNode(rootNode);
                }
            }
        }

        private void printNode(Node node) {
            node.printNode();
            for (Node next: node.child) {
                printNode(next);
            }
        }
    }

    private static int solution(String[] words) {
        int cnt = 0;
        Tree wordTree = new Tree();
        for (String word: words) {
            wordTree.add(word);
        }
        for (String word: words) {
            cnt += wordTree.find(word);
        }
        return cnt;
    }

    public static void main(String[] args) {
        String[] words = {"word","war","warrior","world"};
        int answer = solution(words);
        System.out.print(answer);
    }
}
