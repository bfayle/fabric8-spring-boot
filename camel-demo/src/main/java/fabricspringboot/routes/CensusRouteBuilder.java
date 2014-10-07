/**
 * 
 */
package fabricspringboot.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;

import fabricspringboot.model.CensusData;

/**
 * @author benjaminfayle
 *
 */
public class CensusRouteBuilder extends RouteBuilder {

	/* (non-Javadoc)
	 * @see org.apache.camel.builder.RouteBuilder#configure()
	 */
	@Override
	public void configure() throws Exception {
		
		BindyCsvDataFormat bindy = new BindyCsvDataFormat(CensusData.class);		 
		bindy.setLocale("us");
		
		// parse the csv record and persist it to the database
		from("direct:persistCensusData")
		.routeId("PersistCensusData")
        .setHeader("census_csv_data",simple("${body}"))
//        .onException(Exception.class)
//        	.maximumRedeliveries(1)
//        	.backOffMultiplier(2)
//        	.handled(true)
//        	.transform().simple("${header.census_csv_data}")
//        	.log(LoggingLevel.INFO,"Error persisting ${body}")
//        	.to("mock:CensusDataErrorQueue")
//    	.end()
	    .log(LoggingLevel.INFO,"Persisting ${body}")
	    .unmarshal(bindy)
	    .log(LoggingLevel.INFO,"CensusData ${body}")
		.to("log:fabricspringboot.model.CensusData?level=INFO&groupInterval=10000&groupDelay=60000&groupActiveOnly=true")
        .to("jpa://fabricspringboot.model.CensusData?flushOnSend=true");
	}

}
