// MazeGame.java

import java.util.*;

public class MazeGame {
    public static void main(String[] args) {
        Maze maze = new Maze();
        Player player = new Player(1, 1); // starting position
        Scanner scanner = new Scanner(System.in);

        Stack<Position> undoStack = new Stack<>();
        Queue<String> moveLog = new LinkedList<>();

        while (true) {
            maze.printMaze(player);
            System.out.print("\nMove (W/A/S/D), U to undo, H for hint, Q to quit: ");
            char move = scanner.next().toUpperCase().charAt(0);

            if (move == 'Q') break;
            if (move == 'U') {
                if (!undoStack.isEmpty()) {
                    Position prev = undoStack.pop();
                    player.setPosition(prev.row, prev.col);
                    System.out.println("Undo successful.");
                } else {
                    System.out.println("Nothing to undo!");
                }
                continue;
            }
            if (move == 'H') {
                maze.showHint(player);
                continue;
            }

            int newRow = player.row;
            int newCol = player.col;

            switch (move) {
                case 'W': newRow--; break;
                case 'A': newCol--; break;
                case 'S': newRow++; break;
                case 'D': newCol++; break;
                default:
                    System.out.println("Invalid move!");
                    continue;
            }

            if (maze.isValidMove(newRow, newCol)) {
                undoStack.push(new Position(player.row, player.col));
                moveLog.add("Move " + move);
                player.setPosition(newRow, newCol);

                if (maze.isExit(newRow, newCol)) {
                    maze.printMaze(player);
                    System.out.println("\n🎉 Congratulations! You've reached the exit.");
                    break;
                }
            } else {
                System.out.println("Blocked by wall!");
            }
        }

        System.out.println("\nGame Over. Your Moves:");
        for (String log : moveLog) System.out.println(log);
    }
}

// Maze.java

class Maze {
    private char[][] grid = {
            {'#','#','#','#','#','#','#','#','#','#'},
            {'#','P','.','.','#','.','.','.','.','#'},
            {'#','.','#','.','#','.','#','#','.','#'},
            {'#','.','#','.','.','.','.','#','.','#'},
            {'#','.','#','#','#','#','.','#','.','#'},
            {'#','.','.','.','.','#','.','#','.','E'},
            {'#','#','#','#','.','#','.','#','#','#'},
            {'#','.','.','#','.','#','.','.','.','#'},
            {'#','.','#','#','.','#','#','#','.','#'},
            {'#','#','#','#','#','#','#','#','#','#'}
    };

    public void printMaze(Player player) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == player.row && j == player.col) {
                    System.out.print('P');
                } else {
                    System.out.print(grid[i][j]);
                }
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] != '#';
    }

    public boolean isExit(int row, int col) {
        return grid[row][col] == 'E';
    }

    public void showHint(Player player) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(player.row, player.col));
        visited[player.row][player.col] = true;

        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            if (isExit(pos.row, pos.col)) {
                System.out.println("Hint: Try heading toward " + pos);
                return;
            }

            for (int[] d : new int[][]{{-1,0},{1,0},{0,-1},{0,1}}) {
                int newRow = pos.row + d[0];
                int newCol = pos.col + d[1];
                if (isValidMove(newRow, newCol) && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;
                    queue.add(new Position(newRow, newCol));
                }
            }
        }

        System.out.println("No path found!");
    }
}

// Player.java

class Player {
    int row, col;

    public Player(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

// Position.java

class Position {
    int row, col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
