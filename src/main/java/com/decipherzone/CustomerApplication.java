package com.decipherzone;

import com.decipherzone.config.MongoManaged;
import com.decipherzone.dao.CustomerDao;
import com.decipherzone.dao.impl.CustomerDaoImpl;
import com.decipherzone.health.MongoDBHealthCheck;
import com.decipherzone.resources.CustomerResources;
import com.decipherzone.service.CustomerService;
import com.decipherzone.service.impl.CustomerServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mongodb.MongoClient;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableLookup;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.apache.commons.lang3.text.StrSubstitutor;

/**
 * Created by decipher on 22/9/17 12:16 PM
 * Abhishek Samuel (Software Engineer)
 * abhisheks@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */
public class CustomerApplication extends Application<CustomerConfiguration> {

    public static void main(String[] args) throws Exception {
        new CustomerApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<CustomerConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<CustomerConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(CustomerConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new StrSubstitutor(new EnvironmentVariableLookup())
                )
        );
    }

    @Override
    public void run(CustomerConfiguration configuration, Environment environment) throws Exception {

        MongoClient mongoClient = new MongoClient(configuration.getMongoHost(), configuration.getMongoPort());
        MongoManaged mongoManaged = new MongoManaged(mongoClient);
        environment.lifecycle().manage(mongoManaged);

        Injector injector = createInjector(mongoClient, configuration);

        environment.jersey().register(injector.getInstance(CustomerResources.class));

        environment.healthChecks().register("Customer-dropwizard-db-health-checker",
                new MongoDBHealthCheck(mongoClient));
    }

    private Injector createInjector(MongoClient mongoClient, CustomerConfiguration configuration) {
        return Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(CustomerService.class).to(CustomerServiceImpl.class);
                bind(CustomerDao.class).to(CustomerDaoImpl.class);
                bind(MongoClient.class).toInstance(mongoClient);
                bind(CustomerConfiguration.class).toInstance(configuration);
            }
        });
    }
}
