package sandbox.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("prod")
@PropertySource("classpath:/sandbox/sandbox-prod.properties")
public class SandboxConfiguration_prod {
    public SandboxConfiguration_prod() {
        System.out.println("*** *** SandboxConfiguration_prod() ***");

    }
}
