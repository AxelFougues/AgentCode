/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentcode;

/**
 *
 * @author Th0ma
 */
public class ObstacleElement extends WorldElement {
    public ObstacleElement(int currentX, int currentY) {
        super(currentX, currentY);
    }

    @Override
    public String toString() {
        return "O";
    }

    @Override
    public World playTurn(World realWorld) {
        return realWorld;
    }
}
