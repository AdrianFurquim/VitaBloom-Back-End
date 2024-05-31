package vita.bloom.front.end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vita.bloom.front.end.model.Admin;
import vita.bloom.front.end.repository.AdminRepository;

@RestController
@CrossOrigin
public class ControllerAdmin {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/admin/criar")
    public String criarAdmin(@RequestBody Admin admin){
        admin.setPassword(
            passwordEncoder.encode(admin.getPassword())
        );
        Admin adminCriado = adminRepository.save(admin);

        if (adminCriado != null) {
            return "Admin criado";
        }else{
            return "Erro ao criar admin";
        }
    }

    @GetMapping("/admin/listar")
    public List<Admin> listarAdmins(){
        return (List<Admin>) adminRepository.findAll();
    }
}
