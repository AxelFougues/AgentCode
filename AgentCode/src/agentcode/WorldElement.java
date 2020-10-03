package agentcode;

public abstract class WorldElement {

    protected int currentY, currentX;

    public WorldElement(int currentX, int currentY) {
        this.currentY = currentX;
        this.currentX = currentY;
    }
    
    public int getX(){return currentY;}
    public int getY(){return currentX;}

    public abstract World playTurn(World realWorld);
    
    @Override
    public abstract String toString(); //this is used to identify the type of element and also for dysplay
    
    //#######################################################################
        protected World moveUp(World realWorld){
        realWorld.removeElementToCell(this);
        currentX--;
        realWorld.addElementToCell(this);
        return realWorld;
    }
    
    protected World moveDown(World realWorld){
        realWorld.removeElementToCell(this);
        currentX++;
        realWorld.addElementToCell(this);
        return realWorld;
    }
    
    protected World moveLeft(World realWorld){
        realWorld.removeElementToCell(this);
        currentY--;
        realWorld.addElementToCell(this);
        return realWorld;
    }
    
    protected World moveRight(World realWorld){
        realWorld.removeElementToCell(this);
        currentY--;
        realWorld.addElementToCell(this);
        return realWorld;
    }
    
}
