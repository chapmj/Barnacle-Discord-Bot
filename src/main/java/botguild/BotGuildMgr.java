package botguild;

import botchannel.BotChannelFactory;
import botchannel.BotChannelMgr;
import client.BotGateway;
import discord4j.common.util.Snowflake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 BotGuildMgr holds a collection of all BotGuild instances created.
 Global Singleton.
 Can be updated to an object pool later.
 */
public class BotGuildMgr {

    private static BotGuildMgr instance = null;
    private Set<BotGuild> guilds = null;
    private final static Logger lg = LoggerFactory.getLogger("BotGuildMgr");

    private BotGuildMgr()
    {}

    public static BotGuildMgr getInstance()
    {
        if(BotGuildMgr.instance == null)
        {
            BotGuildMgr.instance = new BotGuildMgr();
            BotGuildMgr.lg.debug("Created instance");
            BotGuildMgr.instance.guilds = new HashSet<>();
            BotGuildMgr.lg.debug("Created guild list");
        }

        return BotGuildMgr.instance;
    }

    //Referenced by BotGuildFactory
    static BotGuild add(BotGuild botGuild)
    {
        var instance = BotGuildMgr.getInstance();
        instance.guilds.add(botGuild);
        BotGuildMgr.lg.debug("Added botGuild to guild list");
        return botGuild;
    }

    static void remove(BotGuild botGuild)
    {
        var instance = BotGuildMgr.getInstance();
        instance.guilds.remove(botGuild);
        BotGuildMgr.lg.debug("Removed botGuild from guild list");
    }

    /**
     * Search guild by id
     * @param guildId Discord guild identifier
     * @return BotGuild option
     */
    public static Optional<BotGuild> find(Snowflake guildId)
    {
       BotGuildMgr.lg.debug("Find guild by guildId");
       return BotGuildMgr.getInstance().guilds.stream()
               .filter(botGuild -> botGuild.equals(guildId))
               .findAny();
    }

    public static void accept(BotGateway botGateway)
    {
        BotGuildMgr.lg.debug("Accepting Gateway");

        BotGuildMgr.getInstance().guilds.clear();
        BotGuildMgr.lg.debug("Cleared BotGuilds list");
        BotGuildMgr.lg.debug("Request Create botGuilds");
        BotGuildFactory.create(botGateway.getGuilds());
        BotGuildMgr.lg.debug("Created BotGuilds list");

        BotGuildMgr.refreshGuilds();

    }

    public static void refreshGuilds() {
        BotGuildMgr.getInstance().guilds.forEach(BotGuildMgr::refresh);
    }

    private static void refresh(BotGuild botGuild)
    {
        botGuild.getChannels().forEach(BotChannelMgr::remove);
        botGuild.clearChannels();

        var botChannels = botGuild.getRemoteChannels()
                .map(BotChannelFactory::create)
                .filter(Objects::nonNull)
                .collectList().block();

        if(botChannels != null)
        {
           botChannels.forEach(botGuild::addChannel);
        }

        lg.debug("Refreshed " + botGuild.getName());
    }
}
