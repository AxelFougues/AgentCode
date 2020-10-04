package agentcode;

import java.util.ArrayList;

public class AgentCode {

    public static void main(String[] args) {
        World w = new World(10);

        //This is how to create rules
        ArrayList<Rule> rules = new ArrayList<>();
        
        
        rules.add(Rule.buildAttackRule());
        
        rules.add(Rule.buildChaseUpRule());
        rules.add(Rule.buildChaseDownRule());
        rules.add(Rule.buildChaseLeftRule());
        rules.add(Rule.buildChaseRightRule());
        
        //This is how to add elements to the world
        w.addElementToCell(new EnemyElement(5, 5));
        w.addElementToCell(new AgentElement(5, 2, rules));
        
        w.AddElementToPlayOrder(5, 2);
        w.AddElementToPlayOrder(5, 5);
        
        w.display();

        //Then call playTurn to move one step forward
        for (int i = 0; i < 20; i++) {
            //System.out.println("============================ \n");
            System.out.println("Itération " + (i+1));
            w = w.playTurn(w);
//            try {
//
//                Thread.sleep(1000);
//            }catch(Exception e){}
            //System.out.println("============================ \n");
        }
    }

}
