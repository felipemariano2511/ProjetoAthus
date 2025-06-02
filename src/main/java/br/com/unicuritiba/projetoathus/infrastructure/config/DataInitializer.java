package br.com.unicuritiba.projetoathus.infrastructure.config;


import br.com.unicuritiba.projetoathus.domain.repositories.CategoriasRepository;
import org.hibernate.validator.internal.constraintvalidators.bv.time.pastorpresent.PastOrPresentValidatorForLocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private CategoriasRepository categoriasRepository;

    private final DataSource dataSource;

    public DataInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void run(String... args) throws Exception {
        try(Connection connection = dataSource.getConnection()) {
            var keyOptional = categoriasRepository.findAll();
            if(keyOptional.isEmpty()) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("sql/data.sql"));
                System.out.println("Arquivo data.sql iniciado com sucesso!");
            }
        }
    }
}
