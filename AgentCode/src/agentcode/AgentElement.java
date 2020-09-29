/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentcode;

/**
 *
 * @author Az
 */
public class AgentElement extends WorldElement{
    
    protected int perceptionRadius; //1 is single cell, 2 is 3x3, 3 is 5x5 etc...
    
    protected World currentWorldPerception;
    
    //######################################################################

    @Override
    public String toString() {
        return "A";
    }
    
    public World playTurn(World realWorld){
        updatePerception(realWorld);
        return next(realWorld);
    }
    
    
    //######################################################################
    
    
    protected void updatePerception(World realWorld){ //Refresh currentWorldPerception with available world data
        
    }
    
    protected World next(World realWorld){ // choose the appropriate action and execute it
        //go through rules
        
        //perform action
        
        //update currentWorldPerception and realWorld separately!
        return realWorld;
    }
    
}
