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
public abstract class WorldElement {

    
    public abstract World playTurn(World realWorld);
    
    @Override
    public abstract String toString(); //this is used to identify the type of element and also for dysplay
    
}
