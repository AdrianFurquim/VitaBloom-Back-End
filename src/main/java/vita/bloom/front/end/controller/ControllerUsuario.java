package vita.bloom.front.end.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.common.lang.NonNull;
import vita.bloom.front.end.model.Carrinho;
import vita.bloom.front.end.model.ItemCarrinho;
import vita.bloom.front.end.model.Usuarios;
import vita.bloom.front.end.repository.CarrinhoRepository;
import vita.bloom.front.end.repository.UsuarioRepository;

@RestController
@CrossOrigin
public class ControllerUsuario {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CarrinhoRepository carrinhoRepository;

    // GET para gerir os usuários
    @GetMapping("/vitabloom/usuarios/listar")
    public List<Usuarios> verUsuarios() {
        return (List<Usuarios>) usuarioRepository.findAll();
    }

    // GET para verificar se o Email e a Senha são existentes no banco.
    @GetMapping("/vitabloom/usuarios/verificar/{email}/{senha}")
    public Usuarios verificarExistenciaUsuario(@PathVariable("email") String email, @PathVariable("senha") String senha){
        Iterable<Usuarios> usuarioExistenteOptional = usuarioRepository.findAll();
        for (Usuarios usuarioVerifica : usuarioExistenteOptional) {
            if (usuarioVerifica.getEmail().equals(email) && usuarioVerifica.getSenha().equals(senha)) {
                return usuarioVerifica;
            }
        }
        System.out.println("Usuário inexistenete");
        return null;
    }

    // GET para ver Usuário através do ID.
    @GetMapping("/vitabloom/usuarios/listarid/{idUsuario}")
    public Optional<Usuarios> listarUsuariosId(@PathVariable("idUsuario") Long idUsuario){
        return usuarioRepository.findById(idUsuario);
    }


    // POST para inserir usuário no banco de dados.
    @PostMapping("/vitabloom/usuarios/inserir")
    public Usuarios inserirUsuario(@RequestBody Usuarios usuario){
        carrinhoRepository.save(usuario.getCarrinho());
        return usuarioRepository.save(usuario);
    }

    // DELETE para deletar um item do banco de dados através do ID do produto.
    @DeleteMapping("/vitabloom/usuario/delete/{id}")
    public String removeUsuario(@PathVariable("id") Long idUsuario){
        if(usuarioRepository.existsById(idUsuario)){
            usuarioRepository.deleteById(idUsuario);
            return "Usuario removido com sucesso!";
        }else{
            return "Usuario de ID não encontrado, insira outro ID porfavor";
        }
    }
    
    // PUT para realizar a modificação dos dados de algum item através do ID do produto.
    @PutMapping("/vitabloom/usuario/editar/{id}")
    public Usuarios editarUsuarios(@PathVariable("id") Long idUsuario, @RequestBody Usuarios usuarioAtualizado){
        Optional<Usuarios> usuarioExistenteOptional = usuarioRepository.findById(idUsuario);
        if (usuarioExistenteOptional.isPresent()) {
            Usuarios usuarioExistente = usuarioExistenteOptional.get();
            usuarioExistente.setCarrinho(usuarioAtualizado.getCarrinho());
            usuarioExistente.setCep(usuarioAtualizado.getCep());
            usuarioExistente.setCidade(usuarioAtualizado.getCidade());
            usuarioExistente.setCpf(usuarioAtualizado.getCpf());
            usuarioExistente.setEmail(usuarioAtualizado.getEmail());
            usuarioExistente.setEstado(usuarioAtualizado.getEstado());
            usuarioExistente.setIdUsuario(usuarioAtualizado.getIdUsuario());
            usuarioExistente.setNome(usuarioAtualizado.getNome());
            usuarioExistente.setSenha(usuarioAtualizado.getSenha());

            return usuarioRepository.save(usuarioExistente);
        } else {
            return null; 
        }
    }

    @PutMapping("/vitabloom/usuario/adicionaritem/{id}")
    public Usuarios adicionaItemAoCarrinhoUsuario(@PathVariable("id") Long idUsuario, @RequestBody ItemCarrinho itensAdicionar) {
        Optional<Usuarios> usuarioExistenteOptional = usuarioRepository.findById(idUsuario);
    
        if (usuarioExistenteOptional.isPresent()) {
            Usuarios usuarioExistente = usuarioExistenteOptional.get();
            List<ItemCarrinho> itensCarrinho = usuarioExistente.getCarrinho().getItens();
            
            // Verificar se o produto já está no carrinho
            boolean produtoExistente = false;
            for (ItemCarrinho itemCarrinho : itensCarrinho) {
                if (itemCarrinho.getProduto().getIdProduto().equals(itensAdicionar.getProduto().getIdProduto())) {
                    // Produto já está no carrinho, incrementar quantidade
                    itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() + itensAdicionar.getQuantidade());
                    produtoExistente = true;
                    break;
                }
            }
            
            if (!produtoExistente) {
                // Produto não existe no carrinho, adicionar novo item
                usuarioExistente.addItemCarrinho(itensAdicionar);
            }
    
            return usuarioRepository.save(usuarioExistente);
        }
        return null;
    }

    @PutMapping("/vitabloom/usuario/removeitem/{idUsuario}/{idItem}")
    public Usuarios removeItemAoCarrinhoUsuario(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idItem") Long idItem){
        Optional<Usuarios> usuarioExistenteOptional = usuarioRepository.findById(idUsuario);
        if (usuarioExistenteOptional.isPresent()) {
            Usuarios usuarioExistente = usuarioExistenteOptional.get();
            usuarioExistente.removeItemCarrinhoById(idItem);
            return usuarioRepository.save(usuarioExistente);
        } else{
            return null;
        }
    }
    

    
}
