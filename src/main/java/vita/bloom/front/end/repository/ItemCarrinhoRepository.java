package vita.bloom.front.end.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import vita.bloom.front.end.model.ItemCarrinho;

public interface ItemCarrinhoRepository extends CrudRepository<ItemCarrinho, Long>{

    Optional<ItemCarrinho> findById(Long id);
}
