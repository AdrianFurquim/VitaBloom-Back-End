package vita.bloom.front.end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vita.bloom.front.end.model.Carrinho;
import vita.bloom.front.end.repository.CarrinhoRepository;
import vita.bloom.front.end.repository.ItemCarrinhoRepository;

@RestController
@CrossOrigin
public class ControllerCarrinho {
    
    @Autowired
    CarrinhoRepository carrinhoRepository;
    @Autowired
    ItemCarrinhoRepository itemCarrinhoRepository;

    // GET para listar todos os itens do carrinho.
    @GetMapping("/vitabloom/carrinho")
    public List<Carrinho> verCarrinho(){
        return (List<Carrinho>) carrinhoRepository.findAll();
    }

    // POST para inserir um carrinho no banco de dados.
    @PostMapping("/vitabloom/carrinho/inserir")
    public List<Carrinho> inserirCarrinho(@RequestBody List<Carrinho> itensCarrinho){
        return (List<Carrinho>) carrinhoRepository.saveAll(itensCarrinho);
    }

    // Delete para deletar um carrinho do banco de dados através de ID do carrinho.
    @DeleteMapping("/vitabloom/carrinho/deletar/{id}")
    public String deletaCarrinho(@PathVariable("id") Long idCarrinho){
        if(carrinhoRepository.existsById(idCarrinho)){
            carrinhoRepository.deleteById(idCarrinho);
            return "Carrinho removido com sucesso!";
        }else{
            return "ID do Carrinho não encontrado, insira outro ID porfavor";
        }
    }
}
