package kz.iitu.itse1908.daniyal.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class ConfigLogger {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(kz.iitu.itse1908.daniyal.config.TrackTime)")
    public void TrackTimeAnnotationDeclarationMethods(){
    }

    @Pointcut("execution(* kz.iitu.itse1908.daniyal.repository..*(..))")
    public void allRepoProcessingMethods(){
    }

    @Pointcut("execution(public void kz.iitu.itse1908.daniyal.repository.CustomerRepository.*(..))")
    public void customerRepoInterfaceProcessingMethods(){
    }

    @Pointcut("execution(public * kz.iitu.itse1908.daniyal.config..*())")
    public void beanCreationProcessingMethods(){
    }

    @Pointcut("execution(* *.src.test.java.kz.iitu.itse1908.daniyal.config.ConfigLoggerTest.*(..))")
    public void TestMethods(){
    }

    @Before("allRepoProcessingMethods() || beanCreationProcessingMethods() || customerRepoInterfaceProcessingMethods()")
    public void BeforeAdvice(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("-BEFORE- Метод: " + methodName + " запускается...");
    }

    @After("allRepoProcessingMethods() || beanCreationProcessingMethods()")
    public void AfterAdvice(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        log.info("-AFTER- Метод: " + methodName + " исполнен." + "\n");
    }

//    @AfterReturning(pointcut = "execution(* kz.iitu.itse1908.daniyal.repository.CarDealerRepositoryImpl.getListOfCars())", returning = "list")
//    public void AfterReturningAdvice(JoinPoint jp, Object list){
//        String methodName = jp.getSignature().getName();
//        log.info("-AFTER-RETURNING- Метод: " + methodName + " исполнен без ошибок. --- Метод вернул тип: " + list.getClass() + " со значением: " + list.toString());
//    }
//
//    @AfterThrowing(pointcut = "execution(* kz.iitu.itse1908.daniyal.repository.CarDealerRepositoryImpl.*(..))", throwing = "error")
//        public void AfterThrowingAdviceCarDealer(JoinPoint jp, Throwable error){
//        String methodName = jp.getSignature().getName();
//        log.error("-AFTER-THROWING- Метод: " + methodName + " вызвал ИСКЛЮЧЕНИЕ: " + error);
//    }
//
//    @AfterThrowing(pointcut = "execution(* kz.iitu.itse1908.daniyal.repository.CarRepositoryImpl.*(..))", throwing = "error")
//    public void AfterThrowingAdviceCar(JoinPoint jp, Throwable error){
//        String methodName = jp.getSignature().getName();
//        log.error("-AFTER-THROWING- Метод: " + methodName + " вызвал ИСКЛЮЧЕНИЕ: " + error);
//    }
//
//    @AfterThrowing(pointcut = "execution(* kz.iitu.itse1908.daniyal.repository.CustomerRepositoryImpl.*(..))", throwing = "error")
//    public void AfterThrowingAdviceCustomer(JoinPoint jp, Throwable error){
//        String methodName = jp.getSignature().getName();
//        log.error("-AFTER-THROWING- Метод: " + methodName + " вызвал ИСКЛЮЧЕНИЕ: " + error);
//    }

    @Around("TrackTimeAnnotationDeclarationMethods() || beanCreationProcessingMethods()")
    public Object executionTime(ProceedingJoinPoint jp) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = jp.proceed();
        long exeTime = System.currentTimeMillis() - start;
        log.warn("-AROUND- " + jp.getSignature() + " method executed in " + exeTime + "ms\n");
        return proceed;
    }

}
