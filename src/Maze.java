import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;

public class Maze {
    private int row;
    private int col;
    private String[][] maze;
    private ArrayList<String> pathTaken;
    private ArrayList<String> checkPoints;

    public Maze (String[][] maze, ArrayList<String> pathTaken){
        row =0;
        col=0;
        this.maze = maze;
        this.pathTaken = pathTaken;
        String current = "(" + row + "," + col + ")";
    }

    public boolean checkUp(){
        return maze[row - 1][col].equals(".");
    }

    public boolean checkDown(){
        return maze[row + 1][col].equals(".");
    }

    public boolean checkRight(){
        return maze[row][col+1].equals(".");
    }

    public boolean checkLeft(){
        return maze[row][col-1].equals(".");
    }

    public void updatePath(int row, int col){
        pathTaken.add("("+row+","+col+")");
    }

    public void move (){
        if(checkLeft()){
            col--;
        }
        else if (checkDown()){
            row++;
        }
        else if(checkRight()){
            col++;
        }
    }

}
