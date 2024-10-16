//package webapp.user;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class UserConfig {
//
//	@Bean(name = "UserConfig")
//	CommandLineRunner userConfig(UserRepository repository) {
//		return args -> {
//			User keith = new User("Keith", "Charlton", "charltonkeith8@gmail.com",
//					"$2y$10$5123vMOESmRSoDnmZveRmusIHeeNWFYjd5b.GLHWUMfPr2CfM7VQ2", LocalDate.of(2002, Month.JUNE, 1));
//			User alex = new User("alex", "Smith", "alex@gmail.com",
//					"$2y$10$5123vMOESmRSoDnmZveRmusIHeeNWFYjd5b.GLHWUMfPr2CfM7VQ2",
//					LocalDate.of(2004, Month.JANUARY, 1));
//
//			repository.saveAll(List.of(keith, alex));
//		};
//	}
//}
