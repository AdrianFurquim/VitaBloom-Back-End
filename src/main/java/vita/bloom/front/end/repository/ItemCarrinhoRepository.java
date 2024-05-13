package vita.bloom.front.end.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import vita.bloom.front.end.model.ItemCarrinho;

public interface ItemCarrinhoRepository extends CrudRepository<ItemCarrinho, Long>{

    // Find by Id por der uma junção de duas classes.
    Optional<ItemCarrinho> findById(Long id);
}
