package aop.aspects;

import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.*;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(public void get*())")
//    @Before("execution(public void aop.UniLibrary.getBook())")  // fqdn определяет, метод какого класса будет выполнен
    public void beforeGetBookAdvice() {
        System.out.println("beforeGetBookAdvice(): попытка получить книгу"); // простое подобие логирования
    }

//    @Before("execution(public void returnBook())")
//    public void beforeReturnBookAdvice() {
//        System.out.println("beforeGetBookAdvice(): попытка вернуть книгу");
//    }
}
