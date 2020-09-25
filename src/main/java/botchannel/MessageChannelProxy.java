package botchannel;

import discord4j.common.util.Snowflake;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.retriever.EntityRetrievalStrategy;
import discord4j.core.spec.MessageCreateSpec;
import discord4j.rest.entity.RestChannel;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Optional;
import java.util.function.Consumer;

public class MessageChannelProxy implements MessageChannel {

    final private MessageChannel msgChannel;

    public MessageChannelProxy(MessageChannel messageChannel)
    {
        this.msgChannel = messageChannel;
    }

    @Override
    public Optional<Snowflake> getLastMessageId() {
        return msgChannel.getLastMessageId();
    }

    @Override
    public Mono<Message> getLastMessage() {
        return this.msgChannel.getLastMessage();
    }

    @Override
    public Mono<Message> getLastMessage(EntityRetrievalStrategy retrievalStrategy) {
        return msgChannel.getLastMessage(retrievalStrategy);
    }

    @Override
    public Optional<Instant> getLastPinTimestamp() {
        return msgChannel.getLastPinTimestamp();
    }

    @Override
    public Mono<Message> createMessage(Consumer<? super MessageCreateSpec> spec) {
        return msgChannel.createMessage((spec));
    }

    @Override
    public Mono<Void> type() {
        return msgChannel.type();
    }

    @Override
    public Flux<Long> typeUntil(Publisher<?> until) {
        return msgChannel.typeUntil(until);
    }

    @Override
    public Flux<Message> getMessagesBefore(Snowflake messageId) {
        return msgChannel.getMessagesBefore(messageId);
    }

    @Override
    public Flux<Message> getMessagesAfter(Snowflake messageId) {
        return msgChannel.getMessagesAfter(messageId);
    }

    @Override
    public Mono<Message> getMessageById(Snowflake id) {
        return msgChannel.getMessageById(id);
    }

    @Override
    public Mono<Message> getMessageById(Snowflake id, EntityRetrievalStrategy retrievalStrategy) {
        return msgChannel.getMessageById(id, retrievalStrategy);
    }

    @Override
    public Flux<Message> getPinnedMessages() {
        return msgChannel.getPinnedMessages();
    }

    @Override
    public Type getType() {
        return msgChannel.getType();
    }

    @Override
    public Mono<Void> delete(String reason) {
        return msgChannel.delete();
    }

    @Override
    public RestChannel getRestChannel() {
        return msgChannel.getRestChannel();
    }

    @Override
    public Snowflake getId() {
        return msgChannel.getId();
    }

    public long getIdAsLong() {
        return msgChannel.getId().asLong();
    }

    @Override
    public GatewayDiscordClient getClient() {
        return msgChannel.getClient();
    }
}
