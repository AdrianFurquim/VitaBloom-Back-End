package vita.bloom.front.end.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import vita.bloom.front.end.model.AdicionarItemRequest;
import vita.bloom.front.end.model.Carrinho;
import vita.bloom.front.end.model.ItemCarrinho;
import vita.bloom.front.end.model.ItemCarrinhoDTO;
import vita.bloom.front.end.model.Produto;
import vita.bloom.front.end.repository.CarrinhoRepository;
import vita.bloom.front.end.repository.ItemCarrinhoRepository;
import vita.bloom.front.end.repository.ProdutoRepository;

@Service
public class CarrinhoService {

    // Classe feita para armazenar funções que modifiquem as tabelas que são conectadas.

    private final CarrinhoRepository carrinhoRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, ItemCarrinhoRepository itemCarrinhoRepository, ProdutoRepository produtoRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.itemCarrinhoRepository = itemCarrinhoRepository;
        this.produtoRepository = produtoRepository;
    }

    // Adicionar Item Ao Carrinho.
    public void adicionarItemAoCarrinho(AdicionarItemRequest request) {
        Carrinho carrinho = carrinhoRepository.findById(request.getIdCarrinho())
                .orElseThrow(() -> new IllegalArgumentException("Carrinho não encontrado: " + request.getIdCarrinho()));
    
        for (ItemCarrinhoDTO itemRequest : request.getItens()) {
            Produto produto = produtoRepository.findById(itemRequest.getProdutoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + itemRequest.getProdutoId()));
    
            // Verifica se o produto já existe no carrinho.
            boolean produtoExistente = false;
            for (ItemCarrinho itemCarrinho : carrinho.getItens()) {
                if (itemCarrinho.getProduto().getIdProduto().equals(itemRequest.getProdutoId())) {
                    // O produto já existe no carrinho, então atualiza a quantidade.
                    itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() + 1);
                    produtoExistente = true;
                    break;
                }
            }
    
            // Se o produto não existir no carrinho, adiciona um novo item.
            if (!produtoExistente) {
                ItemCarrinho novoItem = new ItemCarrinho();
                novoItem.setProduto(produto);
                novoItem.setQuantidade(itemRequest.getQuantidade());
                // novoItem.setCarrinho(carrinho);
    
                carrinho.getItens().add(novoItem);
            }
        }
    
        carrinhoRepository.save(carrinho);
    }

    // Remove o item através da classe AdicionarItemRequest.
    public void removerItemAoCarrinho(AdicionarItemRequest request) {
        Carrinho carrinho = carrinhoRepository.findById(request.getIdCarrinho())
                .orElseThrow(() -> new IllegalArgumentException("Carrinho não encontrado: " + request.getIdCarrinho()));
    
        for (ItemCarrinhoDTO itemRequest : request.getItens()) {
            Produto produto = produtoRepository.findById(itemRequest.getProdutoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + itemRequest.getProdutoId()));
    
            // Verifica se o produto já existe no carrinho.
            boolean produtoExistente = false;
            for (ItemCarrinho itemCarrinho : carrinho.getItens()) {
                if (itemCarrinho.getProduto().getIdProduto().equals(itemRequest.getProdutoId())) {
                    // O produto já existe no carrinho, então atualiza a quantidade.
                    itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() - 1);
                    produtoExistente = true;
                    break;
                }
            }
            // Se o produto não existir no carrinho, adiciona um novo item.
            if (!produtoExistente) {
                ItemCarrinho novoItem = new ItemCarrinho();
                novoItem.setProduto(produto);
                novoItem.setQuantidade(itemRequest.getQuantidade());
                // novoItem.setCarrinho(carrinho);
                carrinho.getItens().add(novoItem);
            }
        }
        carrinhoRepository.save(carrinho);
    }

    // Remover Item do Carrinho por ID.
    public Optional<ItemCarrinho> removeItem(Long itemId){
        itemCarrinhoRepository.deleteById(itemId);
        return itemCarrinhoRepository.findById(itemId);
    }

    // Pegar Item do Carrinho Pelo ID.
    public Optional<ItemCarrinho> getItemDoCarrinho(Long itemId) {
        return itemCarrinhoRepository.findById(itemId);
    }

    // Pegar todos os Item do Carrinho 1.
    public Iterable<ItemCarrinho> findAll() {
        return itemCarrinhoRepository.findAll();
    }

    // Pegar todos os Item do Carrinho sem ciclo infinito 2.
    public List<ItemCarrinhoDTO> findAllItems() {
        List<ItemCarrinho> itensCarrinho = (List<ItemCarrinho>) itemCarrinhoRepository.findAll();
        List<ItemCarrinhoDTO> itensCarrinhoDTO = new ArrayList<>();

        for (ItemCarrinho itemCarrinho : itensCarrinho) {
            ItemCarrinhoDTO itemCarrinhoDTO = new ItemCarrinhoDTO();
            //itemCarrinhoDTO.setProdutoId(itemCarrinho.getId());
            itemCarrinhoDTO.setQuantidade(itemCarrinho.getQuantidade());
            itemCarrinhoDTO.setProduto(produtoRepository.findById(itemCarrinho.getProduto().getIdProduto()));
            //itemCarrinhoDTO.setCarrinho(null);
            itensCarrinhoDTO.add(itemCarrinhoDTO);
        }

        return itensCarrinhoDTO;
    }

    // Teste para tentativa de editar quantia de item no carrinho.
    public void editarQuantiaItemCarrinho(Long idItem, AdicionarItemRequest request) {
        // Procura pelo item de carrinho com base no ID.
        Optional<ItemCarrinhoDTO> itemOptional = request.getItens().stream()
                .filter(item -> item.getProdutoId().equals(idItem))
                .findFirst();
    
        if (itemOptional.isPresent()) {
            ItemCarrinhoDTO itemCarrinhoDTO = itemOptional.get();
    
            // Altera a quantidade do item com base no request.
            int novaQuantidade = itemCarrinhoDTO.getQuantidade();
            // Atualiza a quantidade do item no carrinho.
            itemCarrinhoRepository.findById(idItem).ifPresent(item -> {
                item.setQuantidade(novaQuantidade);
                itemCarrinhoRepository.save(item);
            });
        } else {
            throw new IllegalArgumentException("Item de carrinho não encontrado no request: " + idItem);
        }

    }

}
