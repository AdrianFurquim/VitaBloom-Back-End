package vita.bloom.front.end.repository;

import org.springframework.data.repository.CrudRepository;

import vita.bloom.front.end.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Long>{
    Admin findByUsername(String username);
}
