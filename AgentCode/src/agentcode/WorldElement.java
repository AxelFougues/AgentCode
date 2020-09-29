package agentcode;

public abstract class WorldElement {

    protected int currentX, currentY;

    public WorldElement(int currentX, int currentY) {
        this.currentX = currentX;
        this.currentY = currentY;
    }
    
    public int getX(){return currentX;}
    public int getY(){return currentY;}

    public abstract World playTurn(World realWorld);
    
    @Override
    public abstract String toString(); //this is used to identify the type of element and also for dysplay
    
    //#######################################################################
        protected World moveUp(World realWorld){
        realWorld.removeElementToCell(this);
        currentY--;
        realWorld.addElementToCell(this);
        return realWorld;
    }
    
    protected World moveDown(World realWorld){
        realWorld.removeElementToCell(this);
        currentY++;
        realWorld.addElementToCell(this);
        return realWorld;
    }
    
    protected World moveLeft(World realWorld){
        realWorld.removeElementToCell(this);
        currentX--;
        realWorld.addElementToCell(this);
        return realWorld;
    }
    
    protected World moveRight(World realWorld){
        realWorld.removeElementToCell(this);
        currentX--;
        realWorld.addElementToCell(this);
        return realWorld;
    }
    
}
