package com.rameysoft.main;

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Application Configuration!
 *
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.rameysoft.main.repositories")
@EntityScan(basePackages = "com.rameysoft.main.model")
@EnableTransactionManagement
public class ConfigAndBootApplication extends Application{
	private static final Logger mLogger = LoggerFactory.getLogger(ConfigAndBootApplication.class);

	private static Stage mStage;
	private Scene mScene;
    private final double MIN_WIDTH = 800d;
    private final double MIN_HEIGHT = 600d;
	private static ApplicationContext context;
	public ConfigAndBootApplication() {}
	public static void main(String[] args) throws Exception {
		setContext(SpringApplication.run(ConfigAndBootApplication.class, args));
		launch(args);
    }
	
	public static Object load(String url) {
		  try (InputStream fxmlStream = ConfigAndBootApplication.class.getResourceAsStream(url)) {
		 
			   FXMLLoader loader = new FXMLLoader();
			   loader.setControllerFactory(new Callback<Class<?>, Object>() {
				    @Override
				    public Object call(Class<?> clazz) {
				     return getContext().getBean(clazz);
				    }
			   });
			   return loader.load(fxmlStream);
		  } catch (IOException ioException) {
			  throw new RuntimeException(ioException);
		  }
	}
	
	
	public static ApplicationContext getContext() {
		return context;
	}
	private static void setContext(ApplicationContext context) {
		ConfigAndBootApplication.context = context;
	}

	@Override
	public void start(Stage stage) throws Exception {
		mStage = stage;
    	Parent root = (Parent) ConfigAndBootApplication.load("/fxml/main.fxml");
    	mScene = new Scene(root);
    	mScene.getStylesheets().add("/styles/styles.css");  
    	mStage.setTitle("StreamLine POS");
        mStage.getIcons().add(new Image("/images/search.png"));
        mStage.setScene(mScene);
        
        mStage.setMinHeight(MIN_HEIGHT);
        mStage.setMinWidth(MIN_WIDTH);
        
    	mStage.centerOnScreen();
    	mStage.sizeToScene();
    
		mStage.show();
		mLogger.info("Stage Start complete");
	}
}
