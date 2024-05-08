package com.company.employeesservice.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {


    public static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.company.employeesservice.service.*Impl.*(..))", throwing = "exception")
    public void logServiceException(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
    }


    /**
     * Aspect method to log exceptions thrown from repository methods.
     * This method intercepts exceptions thrown from any method in the EmployeeRepository
     * and logs the error message at the ERROR level.
     *
     * @param exception The exception thrown from the repository method.
     */
    @AfterThrowing(pointcut = "execution(* com.company.employeesservice.repository.EmployeeRepository.*(..))", throwing = "exception")
    public void logRepositoryException(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
    }
}
