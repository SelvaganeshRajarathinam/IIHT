package com.cts.controller;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "api/students")
public class NewRelicController {

        private static final Logger LOGGER = LoggerFactory.getLogger(NewRelicController.class);

        private final DistributionSummary counter;

        @Autowired
        public NewRelicController(final MeterRegistry registry) {
            this.counter = DistributionSummary.builder("get.counter.requests")
                                              .tags("version", "v1")
                                              .publishPercentileHistogram()
                                              .register(registry);
        }

        @RequestMapping(method = RequestMethod.GET)
        @ResponseBody
        public List<String> getStudents() {
            LOGGER.info("/students [GET]");
            counter.record(10.0); // <-- We shall record latency here
            return Collections.emptyList();
        }
}
