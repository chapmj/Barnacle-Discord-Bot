package botchannel;

import discord4j.core.object.entity.Message;

public final class NullBotChannel extends AbstractBotChannel
{

    public NullBotChannel() { }


    @Override
    public void triggerRule(Message message)
    {

    }
}
