package com.deloitte.hk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan({"com.deloitte.hk"})
public class MBApp 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(MBApp.class, args);
    }
}
