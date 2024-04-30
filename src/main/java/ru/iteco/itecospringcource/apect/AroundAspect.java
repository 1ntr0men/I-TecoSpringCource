package ru.iteco.itecospringcource.apect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.iteco.itecospringcource.model.User;

import java.util.List;

@Aspect
@Component
@Slf4j
public class AroundAspect {

    @Value("#{'${user-accept}'.split(',')}")
    public List<String> usersAccept;

    @Around("allGetMethodWithUser(user)")
    public Object aroundAllGetMethodWithUserArgAdvice(ProceedingJoinPoint proceedingJoinPoint, User user) throws Throwable {
        log.info("aroundAllGetMethodWithUserArgAdvice:: Around {} with user {}", proceedingJoinPoint.getSignature().toShortString(), user);

        if (usersAccept.contains(user.getName())) {
            log.info("User {} accepted", user);
            return proceedingJoinPoint.proceed();
        } else {
            throw new RuntimeException("Decline!");
        }
    }

    @Around("execution(* save*(..))")
    public Object aroundAllSaveMethodAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("aroundAllSaveMethodAdvice:: Around {} ", proceedingJoinPoint.getSignature().toShortString());
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("Error! {}", throwable);
            throw new RuntimeException("Around exception");
        }
    }

    @Pointcut("execution(* get*(.., ru.iteco.itecospringcource.model.User, ..)) && args(user)")
    public void allGetMethodWithUser(User user) {
    }

}
