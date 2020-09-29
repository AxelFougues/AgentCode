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
            }
        }
    }
    
    public boolean addElementToCell(WorldElement element, int x, int y){
        if( x > 0 && x < worldDimensions && y > 0 && y < worldDimensions){
            worldGrid[x][y].addElement(element);
            return true;
        }
        return false;
    }
    
    public boolean removeElementToCell(WorldElement element, int x, int y){
        if( x > 0 && x < worldDimensions && y > 0 && y < worldDimensions){
            return worldGrid[x][y].removeElement(element);
        }
        return false;
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
