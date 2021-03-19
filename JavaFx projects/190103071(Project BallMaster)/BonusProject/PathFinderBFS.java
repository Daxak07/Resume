import java.util.LinkedList;

public class PathFinderBFS {
    public static class Cell  {
        int x;
        int y;
        int dist;
        Cell prev;  

        Cell(int x, int y, int dist, Cell prev) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.prev = prev;
        }
        public int getX(){
            return x;
        }
        public int getY(){
            return y;
        }
    }
    
    public static int[][] print(int[][] matrix, int[] start, int[] end) {
		int sx = start[0];
        int sy = start[1];
		int dx = end[0];
        int dy = end[1];
		
	   if (matrix[sx][sy] == 1 || matrix[dx][dy] == 1)
		   return null;
	   
	    int m = matrix.length;
	    int n = matrix[0].length;	    
        Cell[][] cells = new Cell[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0 || matrix[i][j] == 2) {
                    cells[i][j] = new Cell(i, j, Integer.MAX_VALUE, null);
                }
            }
        }
       
        LinkedList<Cell> queue = new LinkedList<>();       
        Cell src = cells[sx][sy];
        src.dist = 0;
        queue.add(src);
        Cell dest = null;
        Cell p;
        while ((p = queue.poll()) != null) {

            if (p.x == dx && p.y == dy) { 
                dest = p;
                break;
            }

            update(cells, queue, p.x - 1, p.y, p);

            update(cells, queue, p.x + 1, p.y, p);

            update(cells, queue, p.x, p.y - 1, p);

            update(cells, queue, p.x, p.y + 1, p);
        }
        if (dest == null) {
            int[][] arr1 = new int[0][0];
            return arr1;
        } else {
            LinkedList<Cell> path = new LinkedList<>();
            p = dest;
            do {
                path.addFirst(p);
            } while ((p = p.prev) != null);
            int[][] arr = new int[path.size()][2];
            for (int i = 0; i < path.size(); i++) {
                arr[i][0]=path.get(i).y;
                arr[i][1]=path.get(i).x;
            }
            return arr;
        }
    }

    static void update(Cell[][] hex, LinkedList<Cell> queue, int x, int y, Cell parent) { 
        if (x < 0 || x >= hex.length || y < 0 || y >= hex[0].length || hex[x][y] == null) {
            return;
        }
        
        int dist = parent.dist + 1;
        Cell p = hex[x][y];
        if (dist < p.dist) {
            p.dist = dist;
            p.prev = parent;
            queue.add(p);
        }
    }
}