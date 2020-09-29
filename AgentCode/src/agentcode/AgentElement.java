package agentcode;

public class AgentElement extends WorldElement{
    
    protected int perceptionRadius; //1 is single cell, 2 is 3x3, 3 is 5x5 etc...
    
    protected World currentWorldPerception;

    public AgentElement(int currentX, int currentY) {
        super(currentX, currentY);
    }
    
    //######################################################################

    @Override
    public String toString() {
        return "A";
    }
    
    @Override
    public World playTurn(World realWorld){
        updatePerception(realWorld);
        return next(realWorld);
    }
    
    
    //######################################################################
    
    
    protected void updatePerception(World realWorld){ //Refresh currentWorldPerception with available world data
        if(perceptionRadius == 1){currentWorldPerception.setCell(realWorld.getCell(currentX, currentY), currentX, currentY); return;}
        for (int x = -perceptionRadius/2; x < perceptionRadius/2; x++) {
            for (int y = -perceptionRadius/2; y < perceptionRadius/y; y++) {
                currentWorldPerception.setCell(realWorld.getCell(currentX + x, currentY + y), currentX + x, currentY + y);
            }
        }
    }
    
    protected World next(World realWorld){ // choose the appropriate action and execute it
        //go through rules
        
        //perform action
        
        return realWorld;
    }
    
    protected World attack (World realWorld){
        realWorld.removeElementToCell(new EnemyElement(currentX, currentY));
        currentWorldPerception.removeElementToCell(new EnemyElement(currentX, currentY));
        return realWorld;
    }
    
    @Override
    protected World moveUp(World realWorld){
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentY--;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }
    
    @Override
    protected World moveDown(World realWorld){
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentY++;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }
    
    @Override
    protected World moveLeft(World realWorld){
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentX--;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }
    
    @Override
    protected World moveRight(World realWorld){
        realWorld.removeElementToCell(this);
        currentWorldPerception.removeElementToCell(this);
        currentX++;
        realWorld.addElementToCell(this);
        currentWorldPerception.addElementToCell(this);
        return realWorld;
    }
    
}
