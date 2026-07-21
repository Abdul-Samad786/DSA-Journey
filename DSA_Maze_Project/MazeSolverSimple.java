import java.util.*;

public class MazeSolverSimple {
    public static void main(String[] args) {
        char[][] maze = createMaze();
        Queue<String> queue = new LinkedList<>();
        queue.add("");

        while (!queue.isEmpty()) {
            String path = queue.poll();
            if (reachedEnd(maze, path)) {
                printMaze(maze, path);
                break;
            }

            for (char dir : new char[]{'L', 'R', 'U', 'D'}) {
                String newPath = path + dir;
                if (isValid(maze, newPath)) {
                    queue.add(newPath);
                }
            }
        }
    }

    public static char[][] createMaze() {
        return new char[][]{
            {'#', '#', '#', '#', '#', 'o', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', ' ', '#', ' ', '#', ' ', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', '#', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', 'x', '#', '#', '#', '#', '#'}
        };
    }

    public static boolean isValid(char[][] maze, String path) {
        int[] pos = getPosition(maze, path);
        int x = pos[0], y = pos[1];

        // Check out of bounds
        if (x < 0 || x >= maze[0].length || y < 0 || y >= maze.length) {
            return false;
        }

        // Check if it's a wall
        return maze[y][x] != '#';
    }

    public static boolean reachedEnd(char[][] maze, String path) {
        int[] pos = getPosition(maze, path);
        int x = pos[0], y = pos[1];

        return maze[y][x] == 'x';
    }

    public static int[] getPosition(char[][] maze, String path) {
        // Find starting point (the 'o' in the top row)
        int startX = 0;
        for (int i = 0; i < maze[0].length; i++) {
            if (maze[0][i] == 'o') {
                startX = i;
                break;
            }
        }

        int x = startX, y = 0;
        for (char move : path.toCharArray()) {
            if (move == 'L') x--;
            else if (move == 'R') x++;
            else if (move == 'U') y--;
            else if (move == 'D') y++;
        }
        return new int[]{x, y};
    }

    public static void printMaze(char[][] maze, String path) {
        int startX = 0;
        for (int i = 0; i < maze[0].length; i++) {
            if (maze[0][i] == 'o') {
                startX = i;
                break;
            }
        }

        int x = startX, y = 0;
        // Create a copy of the maze to draw the path
        char[][] copy = new char[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                copy[i][j] = maze[i][j];
            }
        }

        for (char move : path.toCharArray()) {
            if (move == 'L') x--;
            else if (move == 'R') x++;
            else if (move == 'U') y--;
            else if (move == 'D') y++;

            if (copy[y][x] == ' ') {
                copy[y][x] = '.';
            }
        }

        // Print the maze with the path
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[0].length; j++) {
                System.out.print(copy[i][j] + " ");
            }
            System.out.println();
        }
    }
}