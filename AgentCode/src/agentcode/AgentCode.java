package agentcode;

import java.util.ArrayList;

public class AgentCode {

    public static void main(String[] args) {
        World w = new World(10);
        
        //This is how to create rules
        ArrayList<Rule> rules = new ArrayList<>();
        Rule r = new Rule();
        Condition c1 = new Condition(Condition.Direction.S, Condition.Actor.E); //Means Is there an E-nemy  S-outh of me
        Condition c2 = new Condition(Condition.Direction.H, Condition.Actor.N); //Means Is there an N-othing  H-ere
        r.addCondition(c1);
        r.addCondition(c2);
        rules.add(r);
        
        //This is how to add elements to the world
        w.addElementToCell(new EnemyElement(5,5));
        w.addElementToCell(new AgentElement(5,2, rules));
        w.display();
        
        //Then call playTurn to move one step forward
        for (int i = 0; i < 10; i++) {
            w = w.playTurn(w);
            w.display();
        }
    }
    
}
