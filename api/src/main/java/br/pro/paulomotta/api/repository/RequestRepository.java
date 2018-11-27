package br.pro.paulomotta.api.repository;

import br.pro.paulomotta.api.model.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository to persist the Requests
 * 
 * @author paulo
 */
@Repository
public interface RequestRepository extends CrudRepository<Request, Long>{
}
