package webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// Main Pages
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/profile").setViewName("profile");

		// Student Pages
		registry.addViewController("/student/dashboard").setViewName("student/dashboard");
		registry.addViewController("/student/enroll").setViewName("student/enroll");
		registry.addViewController("/student/courseDetail").setViewName("student/courseDetail");

		// Teacher Pages
		registry.addViewController("/teacher/dashboard").setViewName("teacher/dashboard");
		registry.addViewController("/teacher/editCourse").setViewName("teacher/editCourse");
		registry.addViewController("/teacher/newCourse").setViewName("teacher/newCourse");

	}

}