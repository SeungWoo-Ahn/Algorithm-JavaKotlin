package programmers.Practice.Level2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Friends4Block {
    private static class Block {
        private static final char EMPTY = 'X';

        private final int id;
        private char type;

        public Block(int id, char type) {
            this.id = id;
            this.type = type;
        }

        public boolean isSameType(Block block) {
            return this.type == block.type;
        }

        public boolean isEmpty() {
            return this.type == EMPTY;
        }

        public void toEmpty() {
            this.type = EMPTY;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Block block)) return false;
            return Objects.equals(id, block.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    private static class Game {
        private final Block[][] gameBoard;
        private final int[] dx = {0, 0, 1, 1};
        private final int[] dy = {0, 1, 0, 1};

        public Game(int m, int n, String[] board) {
            Block[][] gameBoard = new Block[m][n];
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    int id = x * n + y;
                    char type = board[x].charAt(y);
                    gameBoard[x][y] = new Block(id, type);
                }
            }
            this.gameBoard = gameBoard;
        }

        public int play() {
            int removedCnt = 0;
            Set<Block> removableBlocks;
            while (!(removableBlocks = getRemovableBlocks()).isEmpty()) {
                removedCnt += removableBlocks.size();
                removableBlocks.forEach(Block::toEmpty);
                pullDownBlocks();
            }
            return removedCnt;
        }

        private Set<Block> getRemovableBlocks() {
            Set<Block> removableBlocks = new HashSet<>();
            for (int x = 0; x < gameBoard.length - 1; x++) {
                for (int y = 0; y < gameBoard[x].length - 1; y++) {
                    if (gameBoard[x][y].isEmpty()) continue;
                    if (is4Blocks(x, y)) {
                        for (int i = 0; i < 4; i++) {
                            int cx = x + dx[i];
                            int cy = y + dy[i];
                            removableBlocks.add(gameBoard[cx][cy]);
                        }
                    }
                }
            }
            return removableBlocks;
        }

        private boolean is4Blocks(int x, int y) {
            Block block = gameBoard[x][y];
            for (int i = 1; i < 4; i++) {
                int cx = x + dx[i];
                int cy = y + dy[i];
                if (!gameBoard[cx][cy].isSameType(block)) {
                    return false;
                }
            }
            return true;
        }

        private void pullDownBlocks() {
            for (int y = 0; y < gameBoard[0].length; y++) {
                int emptyX = gameBoard.length - 1;
                for (int x = gameBoard.length - 1; x >= 0; x--) {
                    if (gameBoard[x][y].isEmpty()) {
                        emptyX = x;
                        break;
                    }
                }
                for (int x = emptyX - 1; x >= 0; x--) {
                    if (gameBoard[x][y].isEmpty()) continue;
                    Block block = gameBoard[x][y];
                    Block emptyBlock = gameBoard[emptyX][y];
                    gameBoard[emptyX][y] = block;
                    gameBoard[x][y] = emptyBlock;
                    emptyX--;
                }
            }
        }
    }

    private static int solution(int m, int n, String[] board) {
        return new Game(m, n, board).play();
    }

    public static void main(String[] args) {
        int m = 6;
        int n = 6;
        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        int answer = solution(m, n, board);
        System.out.print(answer);
    }
}
