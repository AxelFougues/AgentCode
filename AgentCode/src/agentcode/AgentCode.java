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
        
        rules.add(Rule.buildRandomMovementRule());
        
        rules.add(Rule.buildDownwardRestrictedRule());
        rules.add(Rule.buildUpwardRestrictedRule());
        rules.add(Rule.buildLeftwardRestrictedRule());
        rules.add(Rule.buildRightwardRestrictedRule());
        
        

        //This is how to add elements to the world
        w.addElementToCell(new EnemyElement(5, 5));
        w.addElementToCell(new AgentElement(5, 2, rules));
        w.display();

        //Then call playTurn to move one step forward
        for (int i = 0; i < 20; i++) {
            w = w.playTurn(w);
            w.display();
            try {

                Thread.sleep(1000);
            }catch(Exception e){}
        }
    }

}
