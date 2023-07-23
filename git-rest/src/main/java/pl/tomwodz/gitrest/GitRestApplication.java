package pl.tomwodz.gitrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GitRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GitRestApplication.class, args);
    }

}
