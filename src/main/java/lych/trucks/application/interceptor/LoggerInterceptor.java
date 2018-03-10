package lych.trucks.application.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Interceptor.
 */
@Slf4j
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final Method method = ((HandlerMethod) handler).getMethod();
            final String message = "Called method '"
                    + method.getName()
                    + "' in controller '"
                    + method.getDeclaringClass().getSimpleName()
                    + "'";

            log.info(message);
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(final HttpServletRequest request,
                                final HttpServletResponse response,
                                final Object handler,
                                final Exception ex) throws Exception {
        if (handler instanceof HandlerMethod) {
            final Method method = ((HandlerMethod) handler).getMethod();
            final String message = "Method '"
                    + method.getName()
                    + "' in controller '"
                    + method.getDeclaringClass().getSimpleName()
                    + "' finished with code "
                    + Integer.toString(response.getStatus());

            log.info(message);
        }

        super.afterCompletion(request, response, handler, ex);
    }
}
