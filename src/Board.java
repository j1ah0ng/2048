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
                    while (board[r][index] != 0 && index < 4) {
                        // "bubble" the current point towards the right until
                        // we reach a non-zero point, then deal with the next
                        // step
                        ++index;
                    }
                    // we have either reached index==4 or a board[r][index]
                    // is a non-zero point.
                    if (index >= 4) {
                        // move the current point to the last one
                        change(r, 3, num);
                        change(r, c, 0);
                    } else {
                        // check for collisions
                        if (board[r][index] == num) {
                            change(r, index, 2 * num);
                            change (r, c, 0);
                        } else {
                            change(r, index-1, num);
                            change(r, c, 0);
                        }
                    }
                }
            }
        }
    }
}
