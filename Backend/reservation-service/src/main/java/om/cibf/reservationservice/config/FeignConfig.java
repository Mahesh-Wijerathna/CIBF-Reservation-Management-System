package om.cibf.reservationservice.config;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer retryer() {
        // Retry up to 3 times with 1 second interval
        return new Retryer.Default(1000, TimeUnit.SECONDS.toMillis(1), 3);
    }

    @Bean
    public Request.Options requestOptions() {
        // Connection timeout: 5 seconds, Read timeout: 10 seconds
        return new Request.Options(5000, TimeUnit.MILLISECONDS, 10000, TimeUnit.MILLISECONDS, true);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    public static class CustomErrorDecoder implements ErrorDecoder {
        private final ErrorDecoder defaultDecoder = new Default();

        @Override
        public Exception decode(String methodKey, feign.Response response) {
            // Custom error handling based on status codes
            switch (response.status()) {
                case 404:
                    return new ResourceNotFoundException("Resource not found: " + methodKey);
                case 503:
                    return new ServiceUnavailableException("Service temporarily unavailable: " + methodKey);
                default:
                    return defaultDecoder.decode(methodKey, response);
            }
        }
    }

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public static class ServiceUnavailableException extends RuntimeException {
        public ServiceUnavailableException(String message) {
            super(message);
        }
    }
}
