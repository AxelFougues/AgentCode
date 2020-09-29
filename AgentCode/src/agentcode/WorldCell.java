package agentcode;

import java.util.ArrayList;

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
    
    public boolean containsElement(WorldElement element){
        for (int i = 0; i < elements.size(); i++) {
            if(element.toString().equals(elements.get(i).toString())){
                return true;
            }
        }
        return false;
    }
    
    public boolean isEmpty(){
        return elements.isEmpty();
    }
    
    public World playTurn(World realWorld) {
        for (int i = 0; i < elements.size(); i++) {
            realWorld = elements.get(i).playTurn(realWorld);
        }
        return realWorld;
    }

    @Override
    public String toString() {
        if(elements.isEmpty()) return " ";
        String result = "";
        for (WorldElement element : elements) {
            result += element.toString();
        }
        return result;
    }
    
    
    
}
