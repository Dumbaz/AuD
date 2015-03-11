package kassandraApp.model;

import java.util.Optional;

import org.salespointframework.core.SalespointRepository;

public interface EnsembleRepository extends SalespointRepository<Ensemble, Long> {

	Optional<Ensemble> findByName(String name);
}
