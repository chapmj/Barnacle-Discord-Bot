package botchannel;

import discord4j.common.util.Snowflake;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 BotChannelMgr holds a collection of all BotChannel instances created.
 Global singleton.
 Can be updated to be an object pool later.
 */
public class BotChannelMgr {
    //idea:  store bot channels in a K,V.  Key = channelID, Value = pBotChannel

    private static BotChannelMgr instance = null;

    private Set<BotChannel> channels = null;

    private BotChannelMgr()
    {}

    public static BotChannelMgr getInstance()
    {
        if (BotChannelMgr.instance == null)
        {
            BotChannelMgr.instance = new BotChannelMgr();
            BotChannelMgr.instance.channels = new HashSet<>();
        }

        return instance;
    }

    //Referenced by BotChannelFactory
    static BotChannel add(BotChannel botChannel)
    {
        var instance = BotChannelMgr.getInstance();
        instance.channels.add(botChannel);
        return botChannel;
    }

    public static void remove(BotChannel botChannel)
    {
        var instance = BotChannelMgr.getInstance();
        instance.channels.remove(botChannel);
    }

    /**
     * Search channel by id
     * @param channelId Discord channel identifier
     * @return BotChannel option
     */
    public static Optional<BotChannel> find(Snowflake channelId)
    {
        return BotChannelMgr.getInstance().channels.stream()
                .filter(botChannel -> botChannel.equals(channelId))
                .findAny();
    }
}
