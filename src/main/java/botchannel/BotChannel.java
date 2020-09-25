package botchannel;
import discord4j.common.util.Snowflake;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.GuildChannel;
import discord4j.core.object.entity.channel.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rules.AbstractRule;
import rules.IRule;

/*** Interface to a Discord4j channel
 ***/
public final class BotChannel extends AbstractBotChannel {
   private final GuildChannel guildChannel;
   public IRule rule;

   private static final Logger lg = LoggerFactory.getLogger("BotChannel");

   // rules
   //private Set ruleTriggers; //eliminate collection by using decorations
   private BotChannel()
   {
      this.guildChannel = null;
   }

   public BotChannel(GuildChannel guildChannel)
   {
     this.guildChannel = guildChannel;
     lg.debug("Created BotChannel name: " + guildChannel.getName());
   }

   public boolean equals(Snowflake id)
   {
      return (id != null && guildChannel.getId().equals(id));
   }

   public boolean isNull()
   {
      return false;
   }

   public void createMessage(String string)
   {
       switch (guildChannel.getType())
       {
          case GUILD_TEXT:
             ((TextChannel)guildChannel).createMessage(string).block(); // TODO: send message only once
             lg.debug(String.format("Sending message to remote channel: [%s]", guildChannel.getName()));
             break;

          default: break;
       }
   }

   @Override
   public void triggerRule(Message message)
   {
      rule.evaluateMessage(message);  //send self for double dispatch?
   }
}
