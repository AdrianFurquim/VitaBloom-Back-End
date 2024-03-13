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

import vita.bloom.front.end.model.Produto;
import vita.bloom.front.end.repository.ProdutoRepository;

@RestController
@CrossOrigin
public class ControllerProduto {

    @Autowired
    ProdutoRepository produtosRepository;

    @GetMapping("/vitabloom/produtos")
    public List<Produto> verProdutos() {
        return (List<Produto>) produtosRepository.findAll();
    }

    @PostMapping("/vitabloom/produto/inserir")
    public List<Produto> inserirProduto(@RequestBody List<Produto> produtosLista){
        return (List<Produto>) produtosRepository.saveAll(produtosLista);
    }
    
    @DeleteMapping("/vitabloom/deletar/{id}")
    public String removeProduto(@PathVariable("id") Long idProduto){

        if(produtosRepository.existsById(idProduto)){
            produtosRepository.deleteById(idProduto);
            return "Produto removido com sucesso!";
        }else{
            return "ID do produto não encontrado, insira outro ID porfavor";
        }
    }
}
