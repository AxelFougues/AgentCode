package agentcode;

import java.util.ArrayList;

public class Rule {
    protected ArrayList<Condition> conditions;
    protected String name;

    public Rule(String str) {
        name = str;
        conditions = new ArrayList<>();
    }

    public void addCondition(Condition condition){
        conditions.add(condition);
    }
    
    public boolean validate(World world, int x, int y){
        for (Condition condition : conditions) {
            if(!condition.evaluate(world, x, y)) return false;
        }
        return true;
    }
    
    public String name(){
        return name;
    }
}
