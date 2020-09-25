package botguild;

import botchannel.BotChannel;
import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.channel.GuildChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class BotGuild {

    private final Guild guild;
    private List<BotChannel> channels;
    private static final Logger lg = LoggerFactory.getLogger("BotGuild");

    protected BotGuild()
    {
        this.guild = null;
    }
    public BotGuild(Guild guild) {
        this.guild = guild;
        this.channels = new ArrayList<>();
        lg.debug("Created BotGuild name: " + guild.getName());
    }

    public List<BotChannel> getChannels()
    {
        return channels;
    }

    public Flux<GuildChannel> getRemoteChannels()
    {
        return this.guild.getChannels();
    }

    public Guild getRemoteGuild()
    {
        return this.guild;
    }

    public boolean equals(Snowflake id)
    {
        return (id != null && guild.getId().equals(id));
    }

    public boolean isNull()
    {
        return false;
    }

    protected void addChannel(BotChannel botChannel)
    {
        this.channels.add(botChannel);
        lg.debug("Added channel");
    }

    void addChannels(List<BotChannel> botChannels)
    {
        this.channels.addAll(botChannels);
        lg.debug("Added channels");
    }

    void clearChannels()
    {
        this.channels.clear();
        lg.debug("BotGuild." + this.getName() + "Cleared channels");
    }

    String getName()
    {
        return guild.getName();
    }
}
