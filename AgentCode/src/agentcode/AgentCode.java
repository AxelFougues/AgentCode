package agentcode;

public class AgentCode {

    public static void main(String[] args) {
        World w = new World(10);
        
        w.addElementToCell(new EnemyElement(5,5));
        w.addElementToCell(new AgentElement(5,2));
        w.display();
        
        //w.removeElementToCell(new EnemyElement(5,5));
        //w.display();
        
        w = w.playTurn(w);
        w.display();
    }
    
}
