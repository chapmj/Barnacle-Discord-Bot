package botchannel;

import discord4j.core.object.entity.channel.GuildChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import rules.RuleBasic;

import java.util.ArrayList;
import java.util.List;

public class BotChannelFactory {

    private static final Logger lg = LoggerFactory.getLogger("BotChannelFactory");

    public static BotChannel create(GuildChannel guildChannel)
    {
        var opt= BotChannelMgr.find(guildChannel.getId());
        var botChannel = opt.orElse(BotChannelMgr.add(new BotChannel(guildChannel)));
        botChannel.rule = new RuleBasic();

        return botChannel;
    }

    public static List<BotChannel> create(Flux<GuildChannel> channelsFlux)
    {
        lg.debug("Create BotGuilds from flux");
        var channelList = new ArrayList<BotChannel>();

        channelsFlux.collectList().block().forEach((c) -> {
            var botChannel = BotChannelFactory.create(c);
            channelList.add(botChannel);
        });
        return channelList;
    }

}


