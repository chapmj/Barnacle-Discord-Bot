package botguild;

import discord4j.common.util.Snowflake;

public class NullBotGuild extends BotGuild {
    public NullBotGuild() {
        super();
    }

    @Override
    public boolean equals(Snowflake id)
    {
        return false;
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
