package org.jbehave.examples.trader.guice;

import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.guice.GuiceStepsFactory;
import org.jbehave.examples.trader.BeforeAfterSteps;
import org.jbehave.examples.trader.TraderStory;
import org.jbehave.examples.trader.service.TradingService;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;

/**
 * Example of configuring a single story to use steps defined in a Guice injector.
 */
public abstract class TraderStoryUsingGuice extends TraderStory {

    @Override
    protected List<CandidateSteps> createSteps(Configuration configuration) {
        Injector parent = createInjector();
        return new GuiceStepsFactory(configuration, parent).createCandidateSteps();
    }

    private Injector createInjector() {
        Injector parent = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
              bind(TradingService.class).in(Scopes.SINGLETON);
              bind(GuiceTraderSteps.class).in(Scopes.SINGLETON);
              bind(BeforeAfterSteps.class).in(Scopes.SINGLETON);
            }
          });
        return parent;
    }
    
}
