package sandbox;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		BeanFactory spring = new AnnotationConfigApplicationContext(SandboxConfiguration.class);
	
		System.out.println("*********** je demarre l'appli ***********");
		UnClient unClient = spring.getBean(UnClient.class); //new UnClient();
		unClient.faireQQChose();
		
	}
}
