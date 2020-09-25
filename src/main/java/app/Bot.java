package app;

import botchannel.BotChannelMgr;
import client.BotClient;
import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.message.MessageCreateEvent;
import event.EventMgr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rules.AbstractRule;
import rules.RuleBasic;
import rules.RuleBlockPhraseAndRespond;
import rules.ruleaction.AbstractRuleAction;
import rules.ruleaction.RuleActionBasic;
import rules.ruleaction.RuleActionWithChannelMessage;

public class Bot
{
    public static void main(String[] args) {
        Logger lg = LoggerFactory.getLogger("Bot");
        lg.debug("HELLO");
        //read token from file or args
        String temptoken = "";//hidden on github
        final var token = args.length > 0 ? args[0] : temptoken;
        final var client = new BotClient(token);
        final var gateway = client.login();

        assert(token.equals(""));
        assert(gateway != null);

        gateway.refresh();

        String response = "Please keep political chat to <#753316790754476133>";
        AbstractRuleAction action = new RuleActionBasic();
        action = new RuleActionWithChannelMessage(action, response, String.format("Send: [%s]",response));

        AbstractRule rule = new RuleBasic();
        rule = new RuleBlockPhraseAndRespond(rule, action, "Block: ", "Zapp Brannigan");

        var optBotChannel = BotChannelMgr.find(Snowflake.of(753316790754476133L));

        if (optBotChannel.isPresent())
        {
            var botChannel = optBotChannel.get();
            botChannel.rule = rule;
        }

        //add channel permissions as parameter
        gateway.subscribeTo(MessageCreateEvent.class, EventMgr::accept);

        EventMgr.processEvents(10000);

        gateway.disconnect();

    }
}
