package rules.ruleaction;

import botchannel.BotChannelMgr;
import discord4j.core.object.entity.Message;

public class RuleActionWithChannelMessage extends AbstractRuleActionDecorator
{

    private final String response;

    public RuleActionWithChannelMessage(AbstractRuleAction action, String response, String description)
    {
        super(action, description);
        this.response = response;
    }

    @Override
    public void execute(Message message)
    {

        var optBotChannel = BotChannelMgr.find(message.getChannelId());
        if(optBotChannel.isPresent())
        {
            var botChannel = optBotChannel.get();
            botChannel.createMessage(response);
            //give message to some sort of dispatcher
        }

    }
}
