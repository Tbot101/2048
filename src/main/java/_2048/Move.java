package _2048;

public class Move {
    private Tile[][] copy;

    public Move(Tile[][] blocks) {
        copy = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copy[i][j] = new Tile(blocks[i][j].getValue());
            }
        }
    }

    public void shift() {
        for (int row = 0; row < 4; row++) {
            for (int col = 3; col >= 0; col--) {
                if (copy[row][col].getValue() == 0) {
                    int i = 1;
                    while (col - i >= 0 && copy[row][col - i].getValue() == 0) {
                        i++;
                    }
                    if (col - i >= 0) {
                        copy[row][col].setValue(copy[row][col - i].getValue());
                        copy[row][col - i].setValue(0);
                    }
                }
            }
        }
    }

    public void compare() {
        for (int row = 0; row < 4; row++) {
            for (int col = 3; col > 0; col--) {
                if (copy[row][col].getValue() == copy[row][col - 1].getValue()) {
                    copy[row][col].setValue(
                            copy[row][col].getValue() + copy[row][col - 1].getValue()
                    );
                    copy[row][col - 1].setValue(0);
                }
            }
        }
    }

    public void rotate(int times) {
        for (int t = 0; t < times; t++) {
            Tile[][] rot = new Tile[4][4];
            for (int y = 0; y < rot.length; y++) {
                for (int x = 0; x < rot[0].length; x++) {
                    rot[x][3 - y] = new Tile(copy[y][x].getValue());
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    copy[i][j].setValue(rot[i][j].getValue());
                }
            }
        }
    }

    public void applyData(Tile[][] blocks) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                blocks[i][j].setValue(copy[i][j].getValue());
            }
        }
    }
}
