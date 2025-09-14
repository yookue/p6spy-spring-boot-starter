# P6spy Spring Boot Starter

Spring Boot application integrates p6spy print SQL logs quickly.

## Quickstart

- Import dependencies

```xml
    <dependency>
        <groupId>cn.unikue.springstarter</groupId>
        <artifactId>p6spy-spring-boot-starter</artifactId>
        <version>LATEST</version>
    </dependency>
```

> By default, this starter will auto take effect, you can turn it off by `spring.p6spy.enabled = false`

- Configure Spring Boot `application.yml` with prefix `spring.p6spy`

```yml
spring:
    datasource:
        url: 'jdbc:p6spy:mysql://xxx'
        driver-class-name: com.p6spy.engine.spy.P6SpyDriver
    p6spy:
        appender: com.p6spy.engine.spy.appender.Slf4JLogger
        log-message-format: cn.unikue.springstarter.p6spy.strategy.CompactSingleLineFormat
        reload-properties: false
```

## Document

- Github: https://github.com/unikueltd/p6spy-spring-boot-starter
- P6spy github: https://github.com/p6spy/p6spy
- P6spy configurations: https://p6spy.readthedocs.io/en/latest/configandusage.html

## Requirement

- jdk 17+

## License

This project is under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)

See the `NOTICE.txt` file for required notices and attributions.

## Donation

You like this package? Then [donate to us](https://unikue.cn/donation) to support the development.

## Copyright

Beijing Unikue Network Technology Ltd.

## Website

- Unikue: https://unikue.cn
