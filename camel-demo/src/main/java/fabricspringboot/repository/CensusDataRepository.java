package fabricspringboot.repository;

import fabricspringboot.model.CensusData;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CensusDataRepository extends PagingAndSortingRepository<CensusData, Long> {

	public CensusData findByCbsaCode (Integer cbsaCode);
	
}
