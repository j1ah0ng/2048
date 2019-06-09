public class Board {

    int[][] board;
    DisplayPane pane;

    public Board() {
        board = new int[4][4];


        // Now, assign two random points on the grid to be 2
        int r = (int) (Math.random() * 4);
        int c = (int) (Math.random() * 4);
        change(r, c, 2);
        int j = (int) (Math.random() * 4);
        int k = (int) (Math.random() * 4);
        while (j == r && c == k) {
            j = (int) (Math.random() * 4);
            k = (int) (Math.random() * 4);
        }
        change(j, k, 2);
    }

    /** Propagates changes to the displaypane */
    private void change(int r, int c, int point) {
        board[r][c] = point;
    }

    /** Deals with spawning and checking for game over per turn */
    private void iterate() {
        int numNonZero = 0;
        for (int r = 0; r < 4; ++r) {
            for (int c = 0; c < 4; ++c) {
                if (board[r][c] != 0) ++numNonZero;
            }
        }

        if (numNonZero == 16) {
            gameOver();
            return;
        } else {
            // Spawn a new block somewhere
            int r = (int) (Math.random() * 4);
            int c = (int) (Math.random() * 4);
            while (board[r][c] != 0) {
                r = (int) (Math.random() * 4);
                c = (int) (Math.random() * 4);
            }
            change(r, c, (Math.random() * 2 < 1) ? 2 : 4);
        }
    }

    /** Ends the game */
    private void gameOver() {

    }

    /** Shifts right */
    public void toRight() {
        a: for (int r = 0; r < 4; ++r) {
            b: for (int c = 3; c >= 0; --c) {
                if (board[r][c] == 0) continue;
                else {
                    int num = board[r][c];
                    int index = c + 1;
                    while (index < 4) {
                        if (board[r][index] != 0) {
                            // combine
                            if (board[r][index] == num) {
                                change(r, index, 2 * num);
                                change(r, index - 1, 0);
                            } else continue b;
                        } else {
                            // move it over
                            change(r, index, num);
                            change(r, index - 1, 0);
                        }
                        ++index;
                    }
                }
            }
        }
    }
}
