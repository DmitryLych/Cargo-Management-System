package lych.trucks.domain.audit;

/**
 * Auditing service.
 */
public interface Auditing {

    /**
     * Service logging audit.
     *
     * @param serviceName a name of service.
     * @param methodName  a name of service method.
     */
    void logService(String serviceName, String methodName);

    /**
     * Exception handling logging audit.
     *
     * @param methodName a handler method name.
     * @param args       a args of method.
     */
    void logHandle(String methodName, String args);

    /**
     * Controller logging auditor.
     *
     * @param controllerName a controller name.
     * @param methodName     a method name.
     * @param args           a args of method.
     */
    void logController(String controllerName, String methodName, String args);
}
