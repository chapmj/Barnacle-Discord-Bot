package client;

import discord4j.core.DiscordClient;
import discord4j.discordjson.json.UserGuildData;
import reactor.core.publisher.Flux;

public class BotClient {
    final DiscordClient client;

    private BotClient(){
        client = null;
    }

    public BotClient (String token)
    {
        this.client = DiscordClient.create(token);
    }

    public Flux<UserGuildData> getGuilds()
    {
        return this.client.getGuilds();
    }
    public BotGateway login()
    {
        BotGateway gateway;
        var gw = this.client.login().block();
        gateway = new BotGateway(gw);
        return gateway;

    }
}
