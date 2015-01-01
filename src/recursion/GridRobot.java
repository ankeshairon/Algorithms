package recursion;

/**
 * Imagine a robot sitting on the upper left hand corner of an NxN grid. The robot can
 * only move in two directions: right and down. Imagine certain squares are “off limits”, such that the robot can not step on them.
 * How many possible paths are there for the robot?
 */
public class GridRobot {

    public static void main(String[] args) {
        boolean[][] g = new boolean[3][3];
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                g[i][j] = true;
            }
        }
        System.out.println(new GridRobot().getPaths(g, 2, 2));

    }

    private int getPaths(boolean[][] g, int x, int y) {
        if (x < 0 || y < 0)
            return -1;
        if (x == 0 && y == 0)
            return 1;
        if (!g[x][y])
            return 0;
        int c = 0;
        if (x != 0 && g[x - 1][y])
            c += getPaths(g, x - 1, y);
        if (y != 0 && g[x][y - 1])
            c += getPaths(g, x, y - 1);
        return c;
    }

}
