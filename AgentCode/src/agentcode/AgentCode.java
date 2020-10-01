package agentcode;

import java.util.ArrayList;

public class AgentCode {

    public static void main(String[] args) {
        World w = new World(10);

        //This is how to create rules
        ArrayList<Rule> rules = new ArrayList<>();
        Rule r = new Rule("Attack");
        Rule r2 = new Rule("Move Random");
        Rule r3 = new Rule("Chase Up");
        Rule r4 = new Rule("Chase Down");
        Rule r5 = new Rule("Chase Left");
        Rule r6 = new Rule("Chase Right");
        
        Condition c1 = new Condition(Condition.Direction.H, Condition.Actor.E); //Means Is there an E-nemy  H-ere
        
        Condition c2 = new Condition(Condition.Direction.N, Condition.Actor.E); //Means Is there an E-nemy  N-orth of me
        Condition c3 = new Condition(Condition.Direction.E, Condition.Actor.E); //Means Is there an E-nemy  E-ast of me
        Condition c4 = new Condition(Condition.Direction.S, Condition.Actor.E); //Means Is there an E-nemy  S-outh of me
        Condition c5 = new Condition(Condition.Direction.W, Condition.Actor.E); //Means Is there an E-nemy  W-est of me
        
        Condition c6 = new Condition(Condition.Direction.H, Condition.Actor.N); //Means Is there an N-othing  H-ere
        
        Condition c7 = new Condition(Condition.Direction.N, Condition.Actor.N); //Means Is there an N-othing  N-orth of me
        Condition c8 = new Condition(Condition.Direction.E, Condition.Actor.N); //Means Is there an N-othing  E-ast of me
        Condition c9 = new Condition(Condition.Direction.S, Condition.Actor.N); //Means Is there an N-othing  S-outh of me
        Condition c10 = new Condition(Condition.Direction.W, Condition.Actor.N); //Means Is there an N-othing  W-est of me
        
        r.addCondition(c1);
        
        r2.addCondition(c7);
        r2.addCondition(c8);
        r2.addCondition(c9);
        r2.addCondition(c10);
        
        r3.addCondition(c2);
        
        r4.addCondition(c4);
        
        r5.addCondition(c5);
        
        r6.addCondition(c3);
        
        rules.add(r);
        rules.add(r2);
        rules.add(r3);
        rules.add(r4);
        rules.add(r5);
        rules.add(r6);

        //This is how to add elements to the world
        w.addElementToCell(new EnemyElement(5, 5));
        w.addElementToCell(new AgentElement(5, 2, rules));
        w.display();

        //Then call playTurn to move one step forward
        for (int i = 0; i < 10; i++) {
            w = w.playTurn(w);
            w.display();
            try {

                Thread.sleep(1000);
            }catch(Exception e){}
        }
    }

}
