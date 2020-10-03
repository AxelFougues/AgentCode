package agentcode;

public class World {
 
    protected int worldDimensions;
    protected WorldCell [][] worldGrid;

    public World(int worldDimensions) {
        this.worldDimensions = worldDimensions;
        worldGrid = new WorldCell[worldDimensions][worldDimensions];
        for (int x = 0; x < worldDimensions; x++) {
            for (int y = 0; y < worldDimensions; y++) {
                worldGrid[x][y] = new WorldCell();
                if(y == 0 && x == 0){
                    worldGrid[x][y].addElement(new ObstacleElement(x,y));
                }
                else if(y == worldDimensions -1 && x == worldDimensions -1){
                    worldGrid[x][y].addElement(new ObstacleElement(x,y));
                }
                else if(y == worldDimensions -1 || x == worldDimensions -1){
                    worldGrid[x][y].addElement(new ObstacleElement(x,y));
                }
                else if(y == 0 || x == 0){
                    worldGrid[x][y].addElement(new ObstacleElement(x,y));
                }
            }
        }
    }
    
    public boolean addElementToCell(WorldElement element){
        if( element.getX() > 0 && element.getX() < worldDimensions && element.getY() > 0 && element.getY() < worldDimensions){
            worldGrid[element.getX()][element.getY()].addElement(element);
            return true;
        }
        return false;
    }
    
    public boolean removeElementToCell(WorldElement element){
        if( element.getX() > 0 && element.getX() < worldDimensions && element.getY() > 0 && element.getY() < worldDimensions){
            return worldGrid[element.getX()][element.getY()].removeElement(element);
        }
        return false;
    }
    
    public WorldCell getCell(int x, int y){
        return worldGrid[x][y];
    }
    
    public void setCell(WorldCell cell, int x, int y){
        worldGrid[x][y] = cell;
    }
    
    public boolean elementExists(WorldElement element){
        return worldGrid[element.getX()][element.getY()].containsElement(element);
    }
    
    public boolean hasEmptyCell(int x , int y){
        return worldGrid[x][y].isEmpty();
    }

    @Override
    public String toString() {
        String result = "============================================\n";
        for (int x = 0; x < worldDimensions; x++) {
            for (int y = 0; y < worldDimensions; y++) {
                result += worldGrid[x][y].toString() + " ";
            }
            result += "\n";
        }
        return result += "============================================\n";
    }
    
    public void display(){
        System.out.println(this.toString());
    }
    
    public World playTurn(World realWorld){
        for (int x = 0; x < worldDimensions; x++) {
            for (int y = 0; y < worldDimensions; y++) {
                realWorld = worldGrid[x][y].playTurn(realWorld);
            }
        }
        return realWorld;
    }
    
}
