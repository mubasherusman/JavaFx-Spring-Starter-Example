package com.rameysoft.streamline.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rameysoft.streamline.main.model.Supplier;
import com.rameysoft.streamline.main.repositories.SupplierRepository;

/**
 * Application Configuration!
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class ApplicationConfig {
	final static Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);
	private static ApplicationContext context;
	@Autowired
    protected SupplierRepository bookRepository;
	
	public ApplicationConfig() {}

	public void BookAdd() {
		Supplier supplier = new Supplier();
		supplier.setName("Mubasher");
		supplier = bookRepository.save(supplier);
		supplier = bookRepository.findOne(supplier.getId());
		System.out.print(supplier);
		LOGGER.debug("Supplier Info {}", supplier);
	}
	public static void main(String[] args) throws Exception {
		setContext(SpringApplication.run(ApplicationConfig.class, args));
        ApplicationConfig app = getContext().getBean(ApplicationConfig.class);
        app.BookAdd();
    }
	
	public static ApplicationContext getContext() {
		return context;
	}
	public static void setContext(ApplicationContext context) {
		ApplicationConfig.context = context;
	}
}
