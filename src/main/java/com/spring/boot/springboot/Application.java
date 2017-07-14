package com.spring.boot.springboot;

import com.spring.boot.springboot.db.Customer;
import com.spring.boot.springboot.db.CustomerRepository;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.h2.tools.Server;
import org.hibernate.jpa.internal.EntityManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Configuration
@ImportResource({"./context.xml"})
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	private static JdbcTemplate jdbcTemplate;
	private static ApplicationContext ctx;

	public static void main(String[] args) {
		ctx = SpringApplication.run(Application.class, args);
		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}

		CustomerRepository customer = (CustomerRepository) ctx.getBean("customerRepository");

		List<Customer> danil = (List<Customer>) customer.findAll();

		jdbcTemplate = (JdbcTemplate) ctx.getBean("jbdTemplate");
//		jdbcTemplate.execute("create table customer (id integer, firstName varchar2, lastName varchar2)");
//		jdbcTemplate.execute("insert into abc values('aa', 'bb', 'cc')");
		SqlRowSet set = jdbcTemplate.queryForRowSet("select * from abc");
		while(set.next())
			System.out.println(set.getString(1) + " " + set.getString(2) + " " + set.getString(3));
		danil.forEach(e -> System.out.println(e.toString()));
	}


	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server h2Server() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9093");
	}

//	@Bean
//	@Lazy
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
//			repository.save(new Customer("Jack", "Bauer"));
//			repository.save(new Customer("Chloe", "O'Brian"));
//			repository.save(new Customer("Kim", "Bauer"));
//			repository.save(new Customer("David", "Palmer"));
//			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Customer bauer : repository.findByLastName("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
//			repository.
		};
	}

	public static ApplicationContext getCtx() {
		return ctx;
	}

	public static void setCtx(ApplicationContext ctx) {
		Application.ctx = ctx;
	}
}
