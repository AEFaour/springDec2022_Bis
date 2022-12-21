package sandbox.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("dev")
@PropertySource("classpath:/sandbox/sandbox-dev.properties")
public class SandboxConfiguration_dev {
    public SandboxConfiguration_dev() {
        System.out.println("*** *** SandboxConfiguration_dev() ***");

    }
}
