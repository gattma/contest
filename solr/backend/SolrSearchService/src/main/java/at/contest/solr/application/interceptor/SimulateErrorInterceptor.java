package at.contest.solr.application.interceptor;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@SimulateError
@Interceptor
public class SimulateErrorInterceptor {

    @Inject
    Logger logger;

    @Inject
    @ConfigProperty(name = "error.rate")
    double errorRate;

    @AroundInvoke
    public Object simulateError(InvocationContext ctx) throws Exception {
        logger.debug("SimulateErrorInterceptor#simulateError()");
        logger.debugf("Configured error rate %f", errorRate);
        var val = Math.random();
        if(val < errorRate) {
            logger.debug("Simulating Error...");
            throw new Exception("Simulated Error");
        }
        return ctx.proceed();
    }

}
