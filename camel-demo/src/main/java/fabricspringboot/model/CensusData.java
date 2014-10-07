/**
 * 
 */
package fabricspringboot.model;

import static javax.persistence.GenerationType.AUTO;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * This is a domain object to persist the US government census population change data
 * found here: https://www.census.gov/popest/data/maps/2012/maps_table_CBSA2012.csv
 * 
 * @author benjaminfayle
 *
 */
@CsvRecord(separator = ",")
@Entity
public class CensusData {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    
	@DataField(pos=1)
    @Column(nullable=false)  
    private Integer cbsaCode;
    
	@DataField(pos=2)
    private String region;

	@DataField(pos=3, pattern="#,###")
    private Integer oldPopulation;

	@DataField(pos=4, pattern="#,###")
    private Integer newPopulation;

	@DataField(pos=5, pattern="#,###")
    private Integer populationChange;

	@DataField(pos=6, impliedDecimalSeparator=true)
    @Column(nullable=false, precision=6, scale=2)  
    private BigDecimal percentChange;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getCbsaCode() {
		return cbsaCode;
	}

	public void setCbsaCode(Integer cbsaCode) {
		this.cbsaCode = cbsaCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getOldPopulation() {
		return oldPopulation;
	}

	public void setOldPopulation(Integer oldPopulation) {
		this.oldPopulation = oldPopulation;
	}

	public Integer getNewPopulation() {
		return newPopulation;
	}

	public void setNewPopulation(Integer newPopulation) {
		this.newPopulation = newPopulation;
	}

	public Integer getPopulationChange() {
		return populationChange;
	}

	public void setPopulationChange(Integer populationChange) {
		this.populationChange = populationChange;
	}

	public BigDecimal getPercentChange() {
		return percentChange;
	}

	public void setPercentChange(BigDecimal percentChange) {
		this.percentChange = percentChange;
	}

	@Override
	public String toString() {
		return "CensusData [id=" + id + ", cbsaCode=" + cbsaCode + ", region="
				+ region + ", oldPopulation=" + oldPopulation
				+ ", newPopulation=" + newPopulation + ", populationChange="
				+ populationChange + ", percentChange=" + percentChange + "]";
	}
    
}
