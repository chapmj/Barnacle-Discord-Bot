package rules.ruleaction;

import discord4j.core.object.entity.Message;

public class AbstractRuleActionDecorator extends AbstractRuleAction
{
    protected final AbstractRuleAction action;

    protected AbstractRuleActionDecorator(AbstractRuleAction action, String description)
    {
        super(description);
        this.action = action;
    }

    @Override
    public void execute(Message message)
    {
    }
}
