package lych.trucks.aspect;

import lombok.AllArgsConstructor;
import lych.trucks.domain.audit.Auditing;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Logging advisor.
 */
@Aspect
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoggingAdvisor {

    private final Auditing auditing;

    @Before("lych.trucks.aspect.LoggingPointcut.logService()")
    public void serviceLogAdvice(final JoinPoint joinPoint) {

        auditing.logService(joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @Before("lych.trucks.aspect.LoggingPointcut.logController()")
    public void controllerLogAdvice(final JoinPoint joinPoint) {

        auditing.logController(joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @Before("lych.trucks.aspect.LoggingPointcut.logHandle()")
    public void handleLogAdvice(final JoinPoint joinPoint) {

        auditing.logHandle(joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }
}
