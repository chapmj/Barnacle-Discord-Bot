package event;

import botchannel.BotChannelMgr;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventMgr
{
    private static final Logger lg = LoggerFactory.getLogger("EventMgr");

    private EventMgr()
    {}

    public static void parseEvent(MessageCreateEvent event)
    {
        assert(event != null);

        EventMgr.lg.debug("Parsing event...");

        if(event.getGuildId().isPresent())
        {
            final var message = event.getMessage();
            final var channelId = message.getChannelId();//no longer parsing event

            EventMgr.lg.debug("Parsed event.message.channelId...");

            var botChannel = BotChannelMgr.find(channelId);

            if(message.getAuthor().isPresent() && botChannel.isPresent() && !message.getAuthor().get().isBot())
            {
                EventMgr.lg.debug("event.message.author is not Bot");
                var content = message.getContent();
                EventMgr.lg.debug("Parsing event.message.content...");
                EventMgr.lg.debug("Content: " + content);

                EventMgr.lg.debug("Rules Start...");
                botChannel.get().triggerRule(message);
                EventMgr.lg.debug("...Rules End");
            }
        }
    }
}