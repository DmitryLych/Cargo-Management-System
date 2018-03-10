package lych.trucks.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Service pointcut holder.
 */
@Aspect
public class LoggingPointcut {

    @Pointcut("execution(public * lych.trucks.domain.service.*.*(..))"
            + "||execution(public * lych.trucks.application.security.service.*.*(..))")
    public void logService() {
        // avoid implementation.
    }

    @Pointcut("execution(public * lych.trucks.application.exception..*.*(..))")
    public void logHandle() {
        // avoid implementation
    }

    @Pointcut("execution(public * lych.trucks.application.controller..*.*(..))")
    public void logController() {
        // avoid implementation
    }
}

