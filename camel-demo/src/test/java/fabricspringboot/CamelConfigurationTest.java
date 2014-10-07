/**
 * 
 */
package fabricspringboot;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fabricspringboot.model.CensusData;
import fabricspringboot.repository.CensusDataRepository;
import fabricspringboot.routes.CensusRouteBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {CamelConfigurationTest.class})
@EnableJpaRepositories
@EntityScan
@EnableAutoConfiguration
public class CamelConfigurationTest extends Assert {

    @Bean
    RouteBuilder routeBuilder() {
        return new CensusRouteBuilder();
    };
    
    @Autowired
    ProducerTemplate producerTemplate;
    
    @Autowired
    CensusDataRepository censusDataRepository;

    @Autowired
    CamelContext camelContext;
    
    @Test
    public void testPersist() {
        producerTemplate.sendBody("direct:persistCensusData", "10180,\"Abilene, TX\",\"166,481\",\"166963\",482,0.3");
        
        CensusData data = censusDataRepository.findByCbsaCode(10180);
        assertNotNull(data);
    } 

}
