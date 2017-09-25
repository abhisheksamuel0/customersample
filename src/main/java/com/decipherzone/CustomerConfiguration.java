package com.decipherzone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by decipher on 22/9/17 12:53 PM
 * Abhishek Samuel (Software Engineer)
 * abhisheks@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerConfiguration extends Configuration {

    @JsonProperty
    @NotEmpty
    public String mongoHost;

    @JsonProperty
    public int mongoPort;

    @JsonProperty
    @NotEmpty
    public String mongoDB;

    @JsonProperty
    @NotEmpty
    public String collectionName;

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    public String getMongoHost() {
        return mongoHost;
    }

    public int getMongoPort() {
        return mongoPort;
    }

    public String getMongoDB() {
        return mongoDB;
    }

    public String getCollectionName() {
        return collectionName;
    }
}
