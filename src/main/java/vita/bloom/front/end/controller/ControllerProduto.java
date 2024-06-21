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

import vita.bloom.front.end.model.Produto;
import vita.bloom.front.end.repository.ProdutoRepository;

@RestController
@CrossOrigin
public class ControllerProduto {
    /**
     *  Conexão com o Banco de dados de produtos.
     */
    @Autowired
    ProdutoRepository produtosRepository;

    // GET para ver os produtos do banco de dados.
    @GetMapping("/vitabloom/produtos")
    public List<Produto> verProdutos() {
        // Retorna uma lista de Produtos do Banco de dados.
        return (List<Produto>) produtosRepository.findAll();
    }

    // GET para ver produtos através do ID.
    @GetMapping("/vitabloom/produto/{id}")
    public Optional<Produto> verProdutosId(@PathVariable("id") @NonNull Long idProduto) {
        // Retorna o determinado produto com o id fornecido.
        return produtosRepository.findById(idProduto);
    }

    // POST para inserir os produtos no banco de dados.
    @PostMapping("/vitabloom/produto/inserir")
    public List<Produto> inserirProduto(@RequestBody @NonNull List<Produto> produtosLista){
        // Adiciona a lista de produto(s) adicionados ao banco, e retorna os valores adicionados.
        return (List<Produto>) produtosRepository.saveAll(produtosLista);
    }
    
    // DELETE para deletar um item do banco de dados através do ID do produto.
    @DeleteMapping("/vitabloom/deletar/{id}")
    public String removeProduto(@PathVariable("id") @NonNull Long idProduto){
        // Verifica se o produto existe pelo ID.
        if(produtosRepository.existsById(idProduto)){
            // Se o produto existir ele será excluido.
            produtosRepository.deleteById(idProduto);
            return "Produto removido com sucesso!";
        }else{
            // Caso não seja encontrado, irá avisar que o ID não existe.
            return "ID do produto não encontrado, insira outro ID porfavor";
        }
    }
    
    // PUT para realizar a modificação dos dados de algum item através do ID do produto.
    @PutMapping("/vitabloom/produto/editar/{id}")
    public Produto editarProduto(@PathVariable("id") @NonNull Long idProduto, @RequestBody @NonNull Produto produtoAtualizado){
        // Pega o produto do banco pelo ID do produto.
        Optional<Produto> produtoExistenteOptional = produtosRepository.findById(idProduto);
        // Verifica se o produto esta presente no banco de dados.
        if (produtoExistenteOptional.isPresent()) {
            // Se existir irá pegar os valores, e substituir eles para os novos.
            Produto produtoExistente = produtoExistenteOptional.get();
            produtoExistente.setNomeProduto(produtoAtualizado.getNomeProduto());
            produtoExistente.setValorProduto(produtoAtualizado.getValorProduto());
            produtoExistente.setDescricaoProduto(produtoAtualizado.getDescricaoProduto());
            // Retorna os valores do produto que foram modificado ao banco.
            return produtosRepository.save(produtoExistente);
        } else {
            // Caso não exista um produto com tal ID, retorna Null.
            return null; 
        }
    }

}
