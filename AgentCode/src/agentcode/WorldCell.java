/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentcode;

import java.util.ArrayList;

/**
 *
 * @author Az
 */
public class WorldCell {
    protected ArrayList<WorldElement> elements;

    public WorldCell() {
        elements = new ArrayList<>();
    }
    
    public void addElement(WorldElement element) {
        elements.add(element);
    }
    
    public boolean removeElement(WorldElement element) {
        for (int i = 0; i < elements.size(); i++) {
            if(element.toString().equals(elements.get(i).toString())){
                elements.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public World playTurn(World realWorld) {
        for (int i = 0; i < elements.size(); i++) {
            realWorld = elements.get(i).playTurn(realWorld);
        }
        return realWorld;
    }

    @Override
    public String toString() {
        if(elements.isEmpty()) return "0";
        String result = "";
        for (WorldElement element : elements) {
            result += element.toString();
        }
        return result;
    }
    
    
    
}
