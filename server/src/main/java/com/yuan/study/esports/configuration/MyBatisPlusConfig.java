package com.yuan.study.esports.configuration;

import com.baomidou.mybatisplus.extension.ddl.IDdl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Configuration
@MapperScan("com.yuan.study.esports.repository.mapper")
public class MyBatisPlusConfig {

    @Bean
    public IDdl initDatabase() {
        return new IDdl() {
            @Autowired
            private DataSource dataSource;

            @Override
            public void runScript(Consumer<DataSource> consumer) {
                consumer.accept(this.dataSource);
            }

            @Override
            public List<String> getSqlFiles() {
                return List.of("db/e-sports.sql");
            }
        };
    }
}
