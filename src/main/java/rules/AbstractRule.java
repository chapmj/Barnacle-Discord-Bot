package rules;

import discord4j.core.object.entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractRule implements IRule
{
    protected final String ruleDescription;
    protected final Logger log;

    protected AbstractRule()
    {
        this.ruleDescription = "";
        this.log = LoggerFactory.getLogger("");
    }

    protected AbstractRule(String ruleDescription)
    {
        this.ruleDescription = ruleDescription;
        this.log = LoggerFactory.getLogger(ruleDescription);
    }

    public void evaluateMessage(Message message)
    { }
}
