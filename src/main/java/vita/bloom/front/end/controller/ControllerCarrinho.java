package vita.bloom.front.end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
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
    /**
     *  Conexão ao Banco de dados do Carrinho.
     */
    @Autowired
    CarrinhoRepository carrinhoRepository;
    /**
     *  Conexão ao Banco de dados do Item Carrinho.
     */
    @Autowired
    ItemCarrinhoRepository itemCarrinhoRepository;

    // GET para listar todos os itens do carrinho.
    @GetMapping("/vitabloom/carrinho")
    public List<Carrinho> verCarrinho(){
        // Retorna uma lista de carrinhos que estão no banco.
        return (List<Carrinho>) carrinhoRepository.findAll();
    }

    // POST para inserir um carrinho no banco de dados.
    @PostMapping("/vitabloom/carrinho/inserir")
    public List<Carrinho> inserirCarrinho(@RequestBody @NonNull List<Carrinho> itensCarrinho){
        // Adiciona uma lista de carrinho adicionado ao banco, e retorna este(s) carrinho(s).
        return (List<Carrinho>) carrinhoRepository.saveAll(itensCarrinho);
    }

    // Delete para deletar um carrinho do banco de dados através de ID do carrinho.
    @DeleteMapping("/vitabloom/carrinho/deletar/{id}")
    public String deletaCarrinho(@PathVariable("id") @NonNull Long idCarrinho){
        // Verifica se o carrinho existe pelo ID.
        if(carrinhoRepository.existsById(idCarrinho)){
            // Apaga o carrinho do ID correspondente.
            carrinhoRepository.deleteById(idCarrinho);
            return "Carrinho removido com sucesso!";
        }else{
            // Retorna avisando que o ID do carrinho não existe.
            return "ID do Carrinho não encontrado, insira outro ID porfavor";
        }
    }

}
