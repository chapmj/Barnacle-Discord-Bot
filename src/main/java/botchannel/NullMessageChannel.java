package botchannel;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.channel.Channel;
import discord4j.rest.entity.RestChannel;
import reactor.core.publisher.Mono;

public class NullMessageChannel implements Channel {
    @Override
    public Type getType() {
        return null;
    }

    @Override
    public Mono<Void> delete(String reason) {
        return null;
    }

    @Override
    public RestChannel getRestChannel() {
        return null;
    }

    @Override
    public Snowflake getId() {
        return null;
    }

    @Override
    public GatewayDiscordClient getClient() {
        return null;
    }
}
