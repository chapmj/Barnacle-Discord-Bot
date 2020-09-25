package rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rules.ruleaction.AbstractRuleAction;

public class RuleFactory
{
    private final Logger log;
    AbstractRuleAction action;
    IRule rule;

    //private RuleFactory() {}
    public RuleFactory(AbstractRuleAction action)
    {
        this.rule = new RuleBasic();
        this.action = action;
        this.log = LoggerFactory.getLogger("RuleFactory");
    }

    public IRule create(String blockedPhrase)
    {
        rule = new RuleBlockPhraseAndRespond(rule, action, "Block: ", blockedPhrase);
        log.debug("AddedBlock and response: (" + blockedPhrase + ", " + action.getDescription() + ")");
        return rule;
    }
}
