package metrics;

/*
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.newrelic.NewRelicConfig;
import io.micrometer.newrelic.NewRelicMeterRegistry;
*/
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class NewRelicMetricWriter {

    /*NewRelicConfig newRelicConfig = new NewRelicConfig() {
        @Override
        public String accountId() {
            return "693300";
        }

        @Override
        public String apiKey() {
            return "MY_INSIGHTS_API_KEY";
        }

        @Override
        public String get(String k) {
            return null; // accept the rest of the defaults
        }
    };

    MeterRegistry registry = new NewRelicMeterRegistry(newRelicConfig, Clock.SYSTEM);*/
}
