package vita.bloom.front.end.repository;

import org.springframework.data.repository.CrudRepository;

import vita.bloom.front.end.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long>{
    
}
