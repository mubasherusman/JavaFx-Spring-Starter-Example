package com.rameysoft.streamline.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.rameysoft.streamline.main.model.Book;
import com.rameysoft.streamline.main.repositories.BookRepository;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableJpaRepositories
public class ApplicationConfig {
	final static Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);
	private static ApplicationContext context;
	@Autowired
    protected BookRepository bookRepository;
	
	public ApplicationConfig() {}

	public void BookAdd() {
		Book book = new Book();
        book.setAuthor("Mubasher");
        book.setIsbn("1");
        book.setDescription("my book");
        book.setTitle("book");
		bookRepository.save(book);
		Book b = bookRepository.findOne(book.getIsbn());
		System.out.print(b);
		LOGGER.debug("BookInfo {}", b);
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
