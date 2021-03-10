# Demo application how to get custom metrics in doppler/prometheus


## Running the app locally

```bash
cd ticket-metrics
./gradlew bootRun
```

### Push the app to Cloud Foundry

```
cd ticket-metrics
./gradlew build
cf push
```

## Register the prometheus endpoint to Metric registrar

In order to get the metrics into the loggregator system, using VMWare Tanzu [Metric Registrar](https://docs.pivotal.io/application-service/2-8/metric-registrar/using.html)

```bash
$ cf register-metrics-endpoint ticket-metrics /actuator/prometheus
$ cf tail -f --envelope-type=counter ticket-metrics | grep pcf_ticket
```



## Endpoints

- POST `/increase/closed` - increments the `pcf_ticket_closed` counter
- POST `/increase/opened` - increments the `pcf_ticket_opened` counter
- `/actuator/prometheus` - Prometheus endpoint for metrics

