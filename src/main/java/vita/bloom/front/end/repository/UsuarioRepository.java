package vita.bloom.front.end.repository;

import org.springframework.data.repository.CrudRepository;

import vita.bloom.front.end.model.Usuarios;

public interface UsuarioRepository extends CrudRepository<Usuarios, Long> {
    
}
