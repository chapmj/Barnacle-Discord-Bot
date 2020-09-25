package event;

import botchannel.BotChannelMgr;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.AbstractCollection;
import java.util.concurrent.ConcurrentLinkedDeque;

public class EventMgr
{
    private static EventMgr instance = null;
    private AbstractCollection<MessageCreateEvent> events = null;
    private static final Logger lg = LoggerFactory.getLogger("EventMgr");

    private EventMgr()
    {}

    public static EventMgr getInstance()
    {
        if(EventMgr.instance == null)
        {
            EventMgr.instance = new EventMgr();
            EventMgr.lg.debug("Created instance");
            EventMgr.instance.events = new ConcurrentLinkedDeque<>();
            EventMgr.lg.debug("Created event list");
        }

        return EventMgr.instance;
    }

    public static void processEvents(int idleTime) //todo: just stream it
    {
        while(true)
        {
            if(EventMgr.getInstance().events.isEmpty())
            {}
            else
            {
                lg.debug("Begin processing events");
                EventMgr.lg.debug("Processing Events -- START");
                EventMgr.getInstance()
                        .events.forEach(EventMgr::parseEvent);
                EventMgr.lg.debug("Processing Events -- END");

                EventMgr.getInstance().events.clear(); //problematic

                EventMgr.lg.debug("Reset event queue");

                lg.debug("End processing events");

            }

            try
            {
                Thread.sleep(idleTime);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void accept(MessageCreateEvent event)//problematic.. just use streams
    {
        EventMgr.lg.debug("Accept event");
        EventMgr.getInstance()
                .events.add(event);
        EventMgr.lg.debug("Added event to queue");
    }

    private static void parseEvent(MessageCreateEvent event)
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