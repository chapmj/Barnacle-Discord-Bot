package botchannel;

import discord4j.core.object.entity.Message;

public abstract class AbstractBotChannel
{
    public abstract void triggerRule(Message message);
}
