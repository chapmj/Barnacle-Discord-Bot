package client;

import botguild.BotGuildMgr;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Guild;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

public class BotGateway {
    private final GatewayDiscordClient gateway;

    public BotGateway(GatewayDiscordClient gw) {
        this.gateway = gw;
    }

    public void subscribeTo(Class<MessageCreateEvent> eventClass)
    {
        gateway.on(eventClass).subscribe();
    }

    public void disconnect()
    {
        this.gateway.onDisconnect().block();
    }

    public void subscribeTo(Class<MessageCreateEvent> messageCreateEventClass, Consumer<MessageCreateEvent> parseEvent) {
        this.gateway.on(messageCreateEventClass).subscribe(parseEvent);
    }

    public void refresh()
    {
        BotGuildMgr.accept(this);
    }

    public Flux<Guild> getGuilds()
    {
        return this.gateway.getGuilds();
    }
}
