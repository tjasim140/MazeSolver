import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;

public class Maze {
    private int row;
    private int col;
    private String[][] maze;
    private ArrayList<String> pathTaken;
    private ArrayList<String> deadEnd;

    public Maze (String[][] maze){
        row =0;
        col=0;
        this.maze = maze;
        pathTaken = new ArrayList<String>();
    }

    public ArrayList<String> getPathTaken() {
        return pathTaken;
    }
    public String getCurrent (){
        return "(" + row + "," + col + ")";
    }

    public void updatePath(){
        System.out.println(getCurrent());
        pathTaken.add(getCurrent());
    }

    public boolean checkUp(){
        if(row==0){
            return false;
        }
        return maze[row - 1][col].equals(".");
    }
    public boolean checkDown(){
        if(row== maze.length-1){
            return false;
        }
        return maze[row + 1][col].equals(".");
    }
    public boolean checkRight(){
        if(col== maze[0].length-1){
            return false;
        }
        return maze[row][col+1].equals(".");
    }
    public boolean checkLeft(){
        if(col==0){
            return false;
        }
        return maze[row][col-1].equals(".");
    }

    public void move () {
        while (!getCurrent().equals(("(" + (maze.length-1) + "," + (maze[0].length-1) + ")"))) {
            if (!getCurrent().equals("(0,0)")) {
                updatePath();
                if (checkLeft()&&!pathTaken.contains("("+row+","+(col-1)+")")&&!deadEnd.contains("(" + row + "," + (col - 1) + ")")) {
                    col--;
                } else if (checkDown()&&!pathTaken.contains("("+(row+1)+","+col+")")&&!deadEnd.contains("("+(row+1)+","+col+")")) {
                    row++;
                } else if (checkRight()&&!pathTaken.contains("("+row+","+(col+1)+")")&&!deadEnd.contains("("+row+","+(col+1)+")")) {
                    col++;
                } else if (checkUp()&&!pathTaken.contains("("+(row-1)+","+col+")")&&!deadEnd.contains("("+(row-1)+","+col+")")) {
                    row--;
                }
            } else if (getCurrent().equals("(0,0)")) {
                updatePath();
                if (checkDown() && !pathTaken.contains("(" + (row + 1) + "," + col + ")")) {
                    row++;
                } else if (checkRight() && !pathTaken.contains("(" + row + "," + (col + 1) + ")")) {
                    col++;
                }
            } else {
                deadEnd.add(pathTaken.removeLast());
            }
        }
        pathTaken.add("(" + (maze.length-1) + "," + (maze[0].length-1) + ")");
    }

}
