package kz.iitu.itse1908.daniyal.Scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@PropertySource("classpath:application.properties")
@EnableAsync
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");

//    @Async
//    @Scheduled(fixedRate = 4000)//все задачи независимы и выполняются без задержки, но если предыдущая запущена, следующая не начнется
//    public void reportFixedRate() {
//        log.info("-- fixedRate - The time {}", date.format(new Date()));
//    }
//
//    @Async
//    @Scheduled(fixedDelay = 30000)//обязательно ждет пердыдущего выполнения задачи
//    public void reportFixedDelay() {
//        log.info("-- fixedDelay - The time {}", date.format(new Date()));
//    }
//
//    @Scheduled(initialDelay = 10000, fixedDelay = 3000)//выдерживает начальныую задержку и после выполняется в соответствии с fixedDelay
//    public void reportinitialDelay() {
//        log.info("-- initialDealy - The time {}", date.format(new Date()));
//    }

//    @Scheduled(cron = "${cron.expression}")//sec, min, hour, dayOfMonth, month, dayOfWeek
//    public void reportInCronExpressions() {
//        log.info("-- cron(every 10min from 8pm to 12pm every saturday and monday) - The time {}", date.format(new Date()));
//    }
//
//    @Scheduled(cron = "${cron.expression2}")//
//    public void reportInCronExpressions2() {
//        log.info("-- cron(every 30min only if the first monday will be at the 5th december) - The time {}", date.format(new Date()));
//    }

}
