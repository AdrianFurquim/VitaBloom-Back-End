package vita.bloom.front.end.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vita.bloom.front.end.model.AdicionarItemRequest;
import vita.bloom.front.end.model.Carrinho;
import vita.bloom.front.end.model.ItemCarrinho;
import vita.bloom.front.end.model.ItemCarrinhoDTO;
import vita.bloom.front.end.repository.CarrinhoRepository;
import vita.bloom.front.end.repository.ItemCarrinhoRepository;

@RestController
@CrossOrigin
public class ControllerCarrinho {
    
    @Autowired
    CarrinhoRepository carrinhoRepository;
    @Autowired
    CarrinhoService carrinhoService;    
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

    // POST para incerir um item ao carrinho no banco de dados.
    @PostMapping("/carrinho/adicionar-item")
    public ResponseEntity<?> adicionarItemAoCarrinho(@RequestBody AdicionarItemRequest requestDTO) {
        carrinhoService.adicionarItemAoCarrinho(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // POST para remover item do carrinho.
    @PostMapping("/carrinho/remover-item")
    public ResponseEntity<?> removerItemAoCarrinho(@RequestBody AdicionarItemRequest requestDTO) {
        carrinhoService.removerItemAoCarrinho(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Atualização.
    public void CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }
    
    // Teste 2 para POST para incerir um item ao carrinho no banco de dados.
    @PostMapping("/carrinho/adicionar-itemm")
    public void adicionarItemAoCarrinhoo(@RequestBody AdicionarItemRequest request) {
        carrinhoService.adicionarItemAoCarrinho(request);
    }

    // PUT para modificar os itens que um carrinho possui.
    @PutMapping("/carrinho/modificar/{id}")
    public void editarQuantiaItemCarrinho(@PathVariable("id") Long idItem, @RequestBody AdicionarItemRequest request) {
        carrinhoService.editarQuantiaItemCarrinho(idItem, request);
    }

    // DELETE para deletar item.
    @DeleteMapping("/carrinho/item/delete/{id}")
    public Optional<ItemCarrinho> deletaItem(@PathVariable("id") Long idItem){
        return carrinhoService.removeItem(idItem);
    }

    // GET para ver os dados do carrinho.
    @GetMapping("/vitabloom/carrinho/ver")
    public List<ItemCarrinhoDTO> verItensCarrinho(){
        return carrinhoService.findAllItems();
    }

}
