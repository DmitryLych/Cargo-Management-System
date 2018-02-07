package lych.trucks.domain.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Implementation of {@link Auditing}.
 */
@Service
@Slf4j
public class LoggerBasedAuditor implements Auditing {

    @Override
    public void logService(final String serviceName, final String methodName) {
        methodNameValidate(methodName);

        if (isBlank(serviceName)) {
            throw new IllegalStateException("Name of service is mandatory.");
        }

        log.info("Audit Log from Service: {} - from Method: {}", serviceName, methodName);
    }

    @Override
    public void logHandle(final String methodName, final String args) {

        methodNameValidate(methodName);

        log.info("Audit Log from Handle method: {} - Args: {} ", methodName, args);
    }

    @Override
    public void logController(final String controllerName, final String methodName, final String args) {
        methodNameValidate(methodName);

        if (isBlank(controllerName)) {
            throw new IllegalStateException("Name of controller is mandatory.");
        }

        log.info("Audit Log from Controller: {} - from Method: {} - Args: {}", controllerName, methodName, args);
    }

    private static void methodNameValidate(final String name) {

        if (isBlank(name)) {
            throw new IllegalStateException("Name of method is mandatory.");
        }
    }
}
