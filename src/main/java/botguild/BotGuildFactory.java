package botguild;

import discord4j.core.object.entity.Guild;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * Factory BotGuild and related datastructures
 * @author github.com/chapmj
 */
public class BotGuildFactory {
    private static final Logger lg = LoggerFactory.getLogger("BotGuildFactory");

    /**
     * Create local BotGuild instances from Flux Stream
     *
     * @param guilds Flux of Guild instances
     */
    public static void create(Flux<Guild> guilds)
    {
        lg.debug("Create BotGuilds from flux");
        guilds.collectList().block().forEach(BotGuildFactory::create);//todo check npe
    }

    /**
     * Create BotGuild and associate it with appropriate manager.
     * @param guild Discord Guild object
     * @return Guild adapter
     */
    public static BotGuild create(Guild guild)
    {
        lg.debug("Find BotGuild instance by long");
        var opt = BotGuildMgr.find(guild.getId());
        var botGuild = opt.orElse(BotGuildMgr.add(new BotGuild(guild)));
        lg.debug("Created BotGuild instance");

        return botGuild;
    }
}
