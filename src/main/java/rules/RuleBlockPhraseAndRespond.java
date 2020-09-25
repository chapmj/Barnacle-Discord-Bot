package rules;

import discord4j.core.object.entity.Message;
import rules.ruleaction.AbstractRuleAction;


public final class RuleBlockPhraseAndRespond extends AbstractRuleDecorator
{
    private final String blockedPhrase;

    public RuleBlockPhraseAndRespond(IRule rule, AbstractRuleAction action, String ruleDescription, String blockedPhrase)
    {
        super(rule, action, ruleDescription);
        this.blockedPhrase = blockedPhrase;
    }

    @Override
    public void evaluateMessage(Message message)
    {
        log.debug(String.format("Excecuting rule: [%s: %s]", ruleDescription, blockedPhrase));

        var content = message.getContent();

        log.debug(String.format("Message content: [%s]", message.getContent()));

        if(content.contains(blockedPhrase))
        {
            log.debug(String.format("Action called: [%s]", action.getDescription()));
            action.execute(message);
        }

        log.debug(String.format("Evaluating next rule: [%s]", ruleDescription));
        rule.evaluateMessage(message); //continue until complete
    }
}
