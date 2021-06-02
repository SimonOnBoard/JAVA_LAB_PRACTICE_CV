package com.itis.practice.team123.cvproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CvProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CvProjectApplication.class, args);
    }

    @Bean
    public String okAnswer(){ return "check profile:)"; }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
//        PasswordEncoder passwordEncoder = applicationContext.getBean("passwordEncoder", PasswordEncoder.class);
//        UsersRepository usersRepository = applicationContext.getBean("usersRepository", UsersRepository.class);
//        TeachersRepository teachersRepository = applicationContext.getBean("teachersRepository", TeachersRepository.class);
//        User user = User.builder().username("12345").password(passwordEncoder.encode("12345")).role(Role.ADMIN).email("aaa@aa.ru").build();
//        usersRepository.save(user);
//        String password = passwordEncoder.encode("135");
//        Teacher teacher = new Teacher((Long)null, "135", password, Role.TEACHER, "bbb@bb.ru", "Simple teacher");
//        usersRepository.save(user);
//        teachersRepository.save(teacher);
    }
}
