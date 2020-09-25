package rules.ruleaction;

import discord4j.core.object.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRuleAction
{
    private final static Logger lg = LoggerFactory.getLogger("RuleAction");
    private final String description;

    protected AbstractRuleAction()
    {
        this.description = "";
    }

    AbstractRuleAction(String description)
    {
        this.description = description;
    }

    public void execute(Message message)
    {
        /* execute(message); */
        lg.debug("Rule stack completed execution");
    }

    public String getDescription()
    {
        return this.description;
    }
}
