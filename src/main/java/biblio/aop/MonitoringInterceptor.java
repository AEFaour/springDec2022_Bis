package biblio.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MonitoringInterceptor {
	
	static Logger logger = LoggerFactory.getLogger("monitoring");
	public MonitoringInterceptor() {
		System.err.println("*** MonitoringInterceptor ***");
	}
	@Around("@annotation(Monitorable)")
	public Object  monitoring(ProceedingJoinPoint joinPoint) throws Throwable {
		long debut = System.currentTimeMillis();
		Object o = joinPoint.proceed();
		long fin = System.currentTimeMillis();
		logger.info("méthode {} durée = {} ms" ,joinPoint.getSignature().getName(),  (fin-debut));
		return o;
	}

}
