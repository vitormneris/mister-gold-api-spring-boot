package com.mistergold.mistergold.configuration;

import com.mistergold.mistergold.adapters.persistence.entities.administrator.AdministratorEntity;
import com.mistergold.mistergold.adapters.persistence.repositories.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("teste")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {
    private final MongoTemplate mongoTemplate;
    private final AdministratorRepository administratorRepository;

    @Override
    public void run(String... args) {
        mongoTemplate.getDb().drop();

        AdministratorEntity administrator =
                new AdministratorEntity("admin", "admin@admin.com", new BCryptPasswordEncoder().encode("admin"));
        administratorRepository.save(administrator);
    }
}
