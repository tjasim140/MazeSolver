import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;

public class Maze {
    private int row;
    private int col;
    private String[][] maze;
    private ArrayList<String> pathTaken;
   // private ArrayList<String> deadEnd;
    private String checkPoint;
    private ArrayList<String> checkPoints;

    public Maze (String[][] maze){
        row =0;
        col=0;
        this.maze = maze;
        pathTaken = new ArrayList<String>();
        checkPoints = new ArrayList<String>();
    }

    public String getCurrent (){
        return "(" + row + "," + col + ")";
    }

    public void updatePath(){
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
    public ArrayList<String> filterDeadEnds(ArrayList<String> list){
        for (int i=0; i< pathTaken.size();i++){
            for(int j=0;j< pathTaken.size();j++){
                if(pathTaken.get(j).equals(pathTaken.get(i))&&i!=j){
                    int remove = i;
                    while (remove<j){
                        pathTaken.remove(pathTaken.get(remove));
                        i++;
                        j--;
                    }
                }
            }
        }
        return pathTaken;
    }
    public void move () {
        while (!getCurrent().equals(("(" + (maze.length-1) + "," + (maze[0].length-1) + ")"))) {
            if (!getCurrent().equals("(0,0)")) {
                updatePath();
                if ((checkRight()&&checkDown()||checkRight()&&checkUp()||checkLeft()&&checkDown()||checkLeft()&&checkUp())&&!checkPoints.contains("("+row+","+col+")")){
                    checkPoints.add("("+row+","+col+")");
                }
                if (checkLeft()&&(!pathTaken.contains("("+row+","+(col-1)+")"))) {
                    col--;
                } else if (checkDown()&&(!pathTaken.contains("("+(row+1)+","+col+")"))) {
                    row++;
                } else if (checkRight()&&(!pathTaken.contains("("+row+","+(col+1)+")"))) {
                    col++;
                } else if (checkUp()&&(!pathTaken.contains("("+(row-1)+","+col+")"))) {
                    row--;
                } else { //if the fork's path doesn't work, goes back to the last checkpoint that works getting rid of the one that doesn't
                    if (checkPoints.size()>2) {
                        checkPoints.remove(checkPoints.getLast());
                    }
                    row = Integer.parseInt(checkPoints.getLast().substring(1,checkPoints.getLast().indexOf(",")));
                    col = Integer.parseInt(checkPoints.getLast().substring(checkPoints.getLast().indexOf(",")+1,checkPoints.getLast().indexOf(")")));
                }
            } else if (getCurrent().equals("(0,0)")) { //adds 0,0 to path
                updatePath();
                if (checkDown() && !pathTaken.contains("(" + (row + 1) + "," + col + ")")) {
                    row++;
                } else if (checkRight() && !pathTaken.contains("(" + row + "," + (col + 1) + ")")) {
                    col++;
                }
            }
        }
        pathTaken.add("(" + (maze.length-1) + "," + (maze[0].length-1) + ")");
        filterDeadEnds(pathTaken);
        System.out.println(pathTaken);
    }
}
