package vita.bloom.front.end.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vita.bloom.front.end.model.ItemCarrinho;
import vita.bloom.front.end.model.Usuarios;
import vita.bloom.front.end.repository.CarrinhoRepository;
import vita.bloom.front.end.repository.UsuarioRepository;

@RestController
@CrossOrigin
public class ControllerUsuario {
    /**
     *  Conexão ao banco de dados de usuários.
     */
    @Autowired
    UsuarioRepository usuarioRepository;
    /**
     *  Conexão ao banco de dados de carrinhos.
     */
    @Autowired
    CarrinhoRepository carrinhoRepository;

    // GET para gerir os usuários
    @GetMapping("/vitabloom/usuarios/listar")
    public List<Usuarios> verUsuarios() {
        // Retorna uma lista do(s) usuário(s) adicionado(s).
        return (List<Usuarios>) usuarioRepository.findAll();
    }

    // GET para verificar se o Email e a Senha são existentes no banco.
    @GetMapping("/vitabloom/usuarios/verificar/{email}/{senha}")
    public Usuarios verificarExistenciaUsuario(@PathVariable("email") @NonNull String email, @PathVariable("senha") @NonNull String senha){
        // Cria uma lista de usuários com todos os usuários do bando de dados.
        Iterable<Usuarios> usuarioExistenteOptional = usuarioRepository.findAll();
        // Roda um looping for para analizar todos os usuários do banco.
        for (Usuarios usuarioVerifica : usuarioExistenteOptional) {
            // If para verificar se o email e a senha realmente existem em algum usuário no banco.
            if (usuarioVerifica.getEmail().equals(email) && usuarioVerifica.getSenha().equals(senha)) {
                // Caso exista, retorna o usuário.
                return usuarioVerifica;
            }
        }
        // Caso não exista, retorna Null.
        return null;
    }

    // GET para verificar se o Email e a Senha são existentes no banco.
    @GetMapping("/vitabloom/usuarios/verificar/email/{email}")
    public Usuarios verificarExistenciaEmail(@PathVariable("email") @NonNull String email){
        // Cria uma lista de usuários com todos os usuários do bando de dados.
        Iterable<Usuarios> usuarioExistenteOptional = usuarioRepository.findAll();
        // Roda um looping for para analizar todos os usuários do banco.
        for (Usuarios usuarioVerifica : usuarioExistenteOptional) {
            // If para verificar se o email desse usuário realmente existem em algum usuário no banco.
            if (usuarioVerifica.getEmail().equals(email)) {
                // Caso exista, retorna o usuário.
                return usuarioVerifica;
            }
        }
        // Caso não exista, retorna Null.
        return null;
    }

    // GET para ver Usuário através do ID.
    @GetMapping("/vitabloom/usuarios/listarid/{idUsuario}")
    public Optional<Usuarios> listarUsuariosId(@PathVariable("idUsuario") @NonNull Long idUsuario){
        // Retorna o usuário de respectivo ID.
        return usuarioRepository.findById(idUsuario);
    }


    // POST para inserir usuário no banco de dados.
    @PostMapping("/vitabloom/usuarios/inserir")
    public Usuarios inserirUsuario(@RequestBody @NonNull Usuarios usuario){
        // Ao incerir um usuário novo, já é criado um carrinho unico do usuário.
        carrinhoRepository.save(usuario.getCarrinho());
        // Retorna o usuário criado.
        return usuarioRepository.save(usuario);
    }

    
    // PUT para realizar a modificação dos dados de algum item através do ID do produto.
    @PutMapping("/vitabloom/usuario/editar/{id}")
    public Usuarios editarUsuarios(@PathVariable("id") @NonNull Long idUsuario, @RequestBody @NonNull Usuarios usuarioAtualizado){
        // Pega o usuário do banco pelo ID do usuário.
        Optional<Usuarios> usuarioExistenteOptional = usuarioRepository.findById(idUsuario);
        // Verifica se o usuário esta presente no banco de dados.
        if (usuarioExistenteOptional.isPresent()) {
            // Se existir irá pegar os valores, e substituir eles para os novos.
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
            // Retorna os valores do usuário que foram modificado ao banco.
            return usuarioRepository.save(usuarioExistente);
        } else {
            // Caso não exista um produto com tal ID, retorna Null.
            return null; 
        }
    }

    // PUT para adicionar ou diminuir itens ao carrinho do usuário pelo ID do usuário e o novo Item.
    @PutMapping("/vitabloom/usuario/adicionaritem/{id}")
    public Usuarios adicionaItemAoCarrinhoUsuario(@PathVariable("id") @NonNull Long idUsuario, @RequestBody @NonNull ItemCarrinho itensAdicionar) {
        // Pega o usuário do banco pelo ID do usuário.
        Optional<Usuarios> usuarioExistenteOptional = usuarioRepository.findById(idUsuario);
        // Verifica se o usuário esta presente no banco de dados.
        if (usuarioExistenteOptional.isPresent()) {
            // Se o usuário existir, irá pegar os valores que já existem neste usuário no banco.
            Usuarios usuarioExistente = usuarioExistenteOptional.get();
            List<ItemCarrinho> itensCarrinho = usuarioExistente.getCarrinho().getItens();
            // Verificar se o produto já está no carrinho.
            boolean produtoExistente = false;
            for (ItemCarrinho itemCarrinho : itensCarrinho) {
                if (itemCarrinho.getProduto().getIdProduto().equals(itensAdicionar.getProduto().getIdProduto())) {
                    // Produto já está no carrinho, incrementar quantidade.
                    itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() + itensAdicionar.getQuantidade());
                    produtoExistente = true;
                    break;
                }
            }
            if (!produtoExistente) {
                // Produto não existe no carrinho, adicionar novo item.
                usuarioExistente.addItemCarrinho(itensAdicionar);
            }
            // Retorna o usuário com os novos valores do seu carrinho.
            return usuarioRepository.save(usuarioExistente);
        }
        // Caso não exista esse usuário, retorna Null.
        return null;
    }

    // PUT para remover itens do carrinho do usuário com o ID do usuário e o ID do Item desejado.
    @PutMapping("/vitabloom/usuario/removeitem/{idUsuario}/{idItem}")
    public Usuarios removeItemAoCarrinhoUsuario(@PathVariable("idUsuario") @NonNull Long idUsuario, @PathVariable("idItem") @NonNull Long idItem){
        // Pega o usuário do banco pelo ID do usuário.
        Optional<Usuarios> usuarioExistenteOptional = usuarioRepository.findById(idUsuario);
        // Verifica se o usuário esta presente no banco de dados.
        if (usuarioExistenteOptional.isPresent()) {
            // Caso esteja presente no banco, remove o Item do carrinho.
            Usuarios usuarioExistente = usuarioExistenteOptional.get();
            usuarioExistente.removeItemCarrinhoById(idItem);
            // Retorna o usuário sem o Item no carrinho.
            return usuarioRepository.save(usuarioExistente);
        } else{
            // Caso o usuário não exista, retorna null.
            return null;
        }
    }
    
    // DELETE para deletar um item do banco de dados através do ID do produto.
    @DeleteMapping("/vitabloom/usuario/delete/{id}")
    public String removeUsuario(@PathVariable("id") @NonNull Long idUsuario){
        // Verifica se o id desse usuário realmente existe.
        if(usuarioRepository.existsById(idUsuario)){
            // Caso existe deleta o usuário do banco de dados.
            usuarioRepository.deleteById(idUsuario);
            // Avisa que o usuário foi removido com sucesso.
            return "Usuario removido com sucesso!";
        }else{
            // Caso não exista nenhum usuário com tal ID, irá retornar avisando.
            return "Usuario de ID não encontrado, insira outro ID porfavor";
        }
    }

}
