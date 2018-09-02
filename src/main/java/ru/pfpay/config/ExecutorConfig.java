package ru.pfpay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ExecutorConfig implements AsyncConfigurer {

    public static final String TRANSACTION_EXECUTOR = "transactionExecutor";
    public static final String REQUEST_EXECUTOR = "requestExecutor";

    @Bean(name = TRANSACTION_EXECUTOR)
    public ThreadPoolTaskExecutor transactionExecutor() {
        return createExecutor(TRANSACTION_EXECUTOR, 10, Integer.MAX_VALUE);
    }

    @Bean(name = REQUEST_EXECUTOR)
    public ThreadPoolTaskExecutor requestExecutor() {
        return createExecutor(REQUEST_EXECUTOR, 10, Integer.MAX_VALUE);
    }

    private ThreadPoolTaskExecutor createExecutor(String threadNamePrefix, Integer corePoolSize, Integer maxPoolSize) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setAllowCoreThreadTimeOut(true);
        executor.initialize();
        return executor;
    }

}
