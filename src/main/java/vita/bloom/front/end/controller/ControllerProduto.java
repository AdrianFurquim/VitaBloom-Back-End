package vita.bloom.front.end.controller;


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

import vita.bloom.front.end.model.Produto;
import vita.bloom.front.end.repository.ProdutoRepository;

@RestController
@CrossOrigin
public class ControllerProduto {

    @Autowired
    ProdutoRepository produtosRepository;

    // GET para ver os produtos do banco de dados.
    @GetMapping("/vitabloom/produtos")
    public List<Produto> verProdutos() {
        return (List<Produto>) produtosRepository.findAll();
    }

    // POST para inserir os produtos no banco de dados.
    @PostMapping("/vitabloom/produto/inserir")
    public List<Produto> inserirProduto(@RequestBody List<Produto> produtosLista){
        return (List<Produto>) produtosRepository.saveAll(produtosLista);
    }
    
    // DELETE para deletar um item do banco de dados através do ID do produto.
    @DeleteMapping("/vitabloom/deletar/{id}")
    public String removeProduto(@PathVariable("id") Long idProduto){

        if(produtosRepository.existsById(idProduto)){
            produtosRepository.deleteById(idProduto);
            return "Produto removido com sucesso!";
        }else{
            return "ID do produto não encontrado, insira outro ID porfavor";
        }
    }
    
    // PUT para realizar a modificação dos dados de algum item através do ID do produto.
    @PutMapping("/vitabloom/produto/editar/{id}")
    public Produto editarProduto(@PathVariable("id") Long idProduto, @RequestBody Produto produtoAtualizado){
        Optional<Produto> produtoExistenteOptional = produtosRepository.findById(idProduto);
        if (produtoExistenteOptional.isPresent()) {
        Produto produtoExistente = produtoExistenteOptional.get();
        produtoExistente.setNomeProduto(produtoAtualizado.getNomeProduto());
        produtoExistente.setValorProduto(produtoAtualizado.getValorProduto());
        produtoExistente.setDescricaoProduto(produtoAtualizado.getDescricaoProduto());

        return produtosRepository.save(produtoExistente);
        } else {
            return null; 
        }

    }

}
