package vita.bloom.front.end.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vita.bloom.front.end.model.Admin;
import vita.bloom.front.end.repository.AdminRepository;

public class AdminDetailsService implements UserDetailsService{

     @Autowired
    AdminRepository adminRepository;
    @Override
    public UserDetails loadUserByUsername(String adminName) throws UsernameNotFoundException {
        Admin adminLogin = adminRepository.findByUsername(adminName);
        if(adminLogin == null)
            throw new UsernameNotFoundException(adminName);
        return new AdminDetails(adminLogin);
    }
    
}
