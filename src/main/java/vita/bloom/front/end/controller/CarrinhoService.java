package vita.bloom.front.end.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    private final CarrinhoRepository carrinhoRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, ItemCarrinhoRepository itemCarrinhoRepository, ProdutoRepository produtoRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.itemCarrinhoRepository = itemCarrinhoRepository;
        this.produtoRepository = produtoRepository;
    }

    public void adicionarItemAoCarrinho(AdicionarItemRequest request) {
        Carrinho carrinho = carrinhoRepository.findById(request.getIdCarrinho())
                .orElseThrow(() -> new IllegalArgumentException("Carrinho não encontrado: " + request.getIdCarrinho()));

        for (ItemCarrinhoDTO itemRequest : request.getItens()) {
            Produto produto = produtoRepository.findById(itemRequest.getProdutoId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + itemRequest.getProdutoId()));

            ItemCarrinho novoItem = new ItemCarrinho();
            novoItem.setProduto(produto);
            novoItem.setQuantidade(itemRequest.getQuantidade());
            novoItem.setCarrinho(carrinho);

            carrinho.getItens().add((ItemCarrinho) novoItem);
        }

        carrinhoRepository.save(carrinho);
    }

    public Optional<ItemCarrinho> getItemDoCarrinho(Long itemId) {
        return itemCarrinhoRepository.findById(itemId);
    }
    public Iterable<ItemCarrinho> findAll() {
        return itemCarrinhoRepository.findAll();
    }

    public List<ItemCarrinhoDTO> findAllItems() {
        List<ItemCarrinho> itensCarrinho = (List<ItemCarrinho>) itemCarrinhoRepository.findAll();
        List<ItemCarrinhoDTO> itensCarrinhoDTO = new ArrayList<>();

        for (ItemCarrinho itemCarrinho : itensCarrinho) {
            ItemCarrinhoDTO itemCarrinhoDTO = new ItemCarrinhoDTO();
            itemCarrinhoDTO.setProdutoId(itemCarrinho.getId());
            itemCarrinhoDTO.setQuantidade(itemCarrinho.getQuantidade());
            // Preencher o produto
            itemCarrinhoDTO.setProduto(produtoRepository.findById(itemCarrinho.getProduto().getIdProduto()));
            // Preencher o carrinho
            itemCarrinhoDTO.setCarrinho(null);

            itensCarrinhoDTO.add(itemCarrinhoDTO);
        }

        return itensCarrinhoDTO;
    }
}