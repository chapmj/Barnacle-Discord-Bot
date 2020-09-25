package rules;

import discord4j.core.object.entity.Message;

public class NullRule extends AbstractRule
{
    public void evaluateMessage(Message message)
    { }
}
