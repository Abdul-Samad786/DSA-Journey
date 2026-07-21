import java.util.*;
public class Mazegame {
    // === Internal Position class ===
static class Position {
    int row, col;
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

// === Internal Player class ===
static class Player {
    private int row, col;

    public Player(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

// === Internal Maze class ===
static class Maze {
    private final char[][] grid = {
        {'#', '#', '#', '#', '#', '#'},
        {'#', '.', '.', '.', '.', '#'},
        {'#', '.', '#', '#', '.', '#'},
        {'#', '.', '#', '.', '.', '#'},
        {'#', '.', '#', '.', '#', '#'},
        {'#', '.', '.', '.', 'E', '#'},
        {'#', '#', '#', '#', '#', '#'}
    };

    public void printMaze(Player player) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (r == player.getRow() && c == player.getCol()) {
                    System.out.print('P');
                } else {
                    System.out.print(grid[r][c]);
                }
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length &&
               (grid[row][col] == '.' || grid[row][col] == 'E');
    }

    public boolean isExit(int row, int col) {
        return grid[row][col] == 'E';
    }

    public void showHint(Player player) {
        int rows = grid.length, cols = grid[0].length;
        Queue<Position> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        Position[][] parent = new Position[rows][cols];

        int startR = player.getRow(), startC = player.getCol();
        queue.add(new Position(startR, startC));
        visited[startR][startC] = true;

        int[] dR = {-1, 1, 0, 0};
        int[] dC = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            if (isExit(current.row, current.col)) {
                List<Position> path = new ArrayList<>();
                while (current != null && !(current.row == startR && current.col == startC)) {
                    path.add(current);
                    current = parent[current.row][current.col];
                }
                Collections.reverse(path);
                if (!path.isEmpty()) {
                    Position next = path.get(0);
                    int dr = next.row - startR, dc = next.col - startC;
                    if (dr == -1) System.out.println("Hint: Move UP");
                    else if (dr == 1) System.out.println("Hint: Move DOWN");
                    else if (dc == -1) System.out.println("Hint: Move LEFT");
                    else if (dc == 1) System.out.println("Hint: Move RIGHT");
                }
                return;
            }

            for (int i = 0; i < 4; i++) {
                int newR = current.row + dR[i];
                int newC = current.col + dC[i];

                if (newR >= 0 && newC >= 0 && newR < rows && newC < cols &&
                    !visited[newR][newC] && (grid[newR][newC] == '.' || grid[newR][newC] == 'E')) {
                    visited[newR][newC] = true;
                    parent[newR][newC] = current;
                    queue.add(new Position(newR, newC));
                }
            }
        }

        System.out.println("No path to exit found.");
    }
}

// === Main method ===
public static void main(String[] args) {
    Maze maze = new Maze();
    Player player = new Player(1, 1);
    Scanner scanner = new Scanner(System.in);
    Stack<Position> undoStack = new Stack<>();
    Queue<String> moveLog = new LinkedList<>();

    while (true) {
        maze.printMaze(player);
        System.out.print("Enter move (W/A/S/D), U=Undo, H=Hint, Q=Quit: ");
        char move = scanner.next().toUpperCase().charAt(0);

        int row = player.getRow();
        int col = player.getCol();
        int newRow = row, newCol = col;

        switch (move) {
            case 'W': newRow--; moveLog.add("Up"); break;
            case 'A': newCol--; moveLog.add("Left"); break;
            case 'S': newRow++; moveLog.add("Down"); break;
            case 'D': newCol++; moveLog.add("Right"); break;
            case 'U':
                if (!undoStack.isEmpty()) {
                    Position prev = undoStack.pop();
                    player.setPosition(prev.row, prev.col);
                    System.out.println("Move undone.");
                } else {
                    System.out.println("No move to undo.");
                }
                continue;
            case 'H':
                maze.showHint(player);
                continue;
            case 'Q':
                System.out.println("Game exited.");
                return;
            default:
                System.out.println("Invalid input.");
                continue;
        }

        if (maze.isValidMove(newRow, newCol)) {
            undoStack.push(new Position(row, col));
            player.setPosition(newRow, newCol);
        } else {
            System.out.println("Can't move there!");
        }

        if (maze.isExit(player.getRow(), player.getCol())) {
            maze.printMaze(player);
            System.out.println("🎉 Congratulations! You reached the exit!");
            break;
        }
    }

    scanner.close();
}
} 