package rules;

import discord4j.core.object.entity.Message;
import rules.ruleaction.AbstractRuleAction;

public class AbstractRuleDecorator extends AbstractRule
{

    protected final AbstractRule rule;
    protected final AbstractRuleAction action;

    protected AbstractRuleDecorator(AbstractRule rule, AbstractRuleAction action, String ruleDescription)
    {
       super(ruleDescription);
       this.rule = rule;
       this.action = action;
    }

    /***
     * Checks condition then executes response
     * @param message discord message entity to validate
     */
    @Override
    public void evaluateMessage(Message message)
    {

    }
}
