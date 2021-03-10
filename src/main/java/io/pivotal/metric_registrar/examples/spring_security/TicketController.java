package io.pivotal.metric_registrar.examples.spring_security;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TicketController {

    private static final String METRIC_NAME_PREFIX = "pcf_ticket_";
    private static final String TOTAL_SUFFIX = "_total";
    private MeterRegistry registry;

    public TicketController(MeterRegistry registry) {
        this.registry = registry;
    }

    @PostMapping("/increment/opened")
    public ResponseEntity<String> increaseOpened() {
        incrementCounter("opened");
        
        return new ResponseEntity<>("{}", null, HttpStatus.OK);
    }

    @PostMapping("/increment/closed")
    public ResponseEntity<String> increaseClosed() {
        incrementCounter("closed");
        
        return new ResponseEntity<>("{}", null, HttpStatus.OK);
    }

    private void incrementCounter(String name) {
        Counter c = registry.counter(METRIC_NAME_PREFIX + name + TOTAL_SUFFIX);
        c.increment();
    }

}
