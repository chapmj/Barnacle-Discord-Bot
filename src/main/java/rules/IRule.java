package rules;

import discord4j.core.object.entity.Message;

public interface IRule
{
    void evaluateMessage(Message message);
}
