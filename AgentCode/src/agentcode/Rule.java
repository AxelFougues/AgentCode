package agentcode;

import java.util.ArrayList;

public class Rule {
    protected ArrayList<Condition> conditions;

    public Rule() {
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
}
