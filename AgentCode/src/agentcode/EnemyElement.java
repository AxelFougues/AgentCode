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
public class EnemyElement extends WorldElement{

    @Override
    public String toString() {
        return "E";
    }

    @Override
    public World playTurn(World realWorld) {
        //do nothing, maybe implement random movement later for fun
        return realWorld;
    }
    
}
