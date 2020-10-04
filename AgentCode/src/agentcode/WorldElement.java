package agentcode;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class WorldElement implements Cloneable {


    protected int currentY, currentX;
    private boolean playedItsTurn;

    public WorldElement(int currentX, int currentY) {
        this.currentY = currentX;
        this.currentX = currentY;
        playedItsTurn= false;
    }
    
    public int getX(){return currentY;}
    public int getY(){return currentX;}

    public abstract World playTurn(World realWorld);
    
    @Override
    public abstract String toString(); //this is used to identify the type of element and also for dysplay
    
    //#######################################################################
    protected World moveUp(World realWorld) {  
        try {
            if((currentY-1) <= 0){ //Move valide ?
                return realWorld;
            }
            
            WorldElement oldSelf = (WorldElement) this.clone();
            currentY--;
            
            if(realWorld.addElementToCell(this)){
                realWorld.removeElementToCell(oldSelf);
            }else{
                currentY++;
            }
            
            return realWorld;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(WorldElement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return realWorld;
    }

    protected World moveDown(World realWorld) {
        try {
            if((currentY+1) >= (realWorld.worldDimensions - 1)){ //Move valide ?
                return realWorld;
            }
            
            WorldElement oldSelf = (WorldElement) this.clone();
            currentY++;
            
            if(realWorld.addElementToCell(this)){
                
                realWorld.removeElementToCell(oldSelf);
            }
            else{
                currentY--;
            }
            
            return realWorld;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(WorldElement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return realWorld;
    }

    protected World moveLeft(World realWorld) {
        try {
            if((currentX-1) <= 0){ //Move valide ?
                return realWorld;
            }
            
            WorldElement oldSelf = (WorldElement) this.clone();
            currentX--;
            
            if(realWorld.addElementToCell(this)){
                realWorld.removeElementToCell(oldSelf);
            }else{
                currentX++;
            }
            
            return realWorld;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(WorldElement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return realWorld;
    }

    protected World moveRight(World realWorld) {        
        try {
            if((currentX+1) >= (realWorld.worldDimensions - 1)){ //Move valide ?
                return realWorld;
            }
            
            WorldElement oldSelf = (WorldElement) this.clone();
            currentX++;
            
            if(realWorld.addElementToCell(this)){
                realWorld.removeElementToCell(oldSelf);
            }else{
                currentX--;
            }
            
            return realWorld;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(WorldElement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return realWorld;
    }
    
    /**
     * @return the playedItsTurn
     */
    public boolean didPlayItsTurn() {
        return playedItsTurn;
    }

    /**
     * @param playedItsTurn the playedItsTurn to set
     */
    public void setPlayedItsTurn(boolean playedItsTurn) {
        this.playedItsTurn = playedItsTurn;
    }
}
