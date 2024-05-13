package vita.bloom.front.end.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Carrinho {
    /**
     *  Gerando o ID unico do carrinho.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idCarrinho;
    
    /**
     *  Um para muitos pois um carrinho pode ter vários Itens.
     */
    @OneToMany(cascade = CascadeType.ALL)
    protected List<ItemCarrinho> itens = new ArrayList<>();

    /**
     *  Contrutor vazio para JDBC.
     */
    public Carrinho() {
    }

    /**
     * @param itens
     *  Contrutor para o carrinho.
     */
    public Carrinho(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    /**
     * @return
     */
    public Long getIdCarrinho() {
        return idCarrinho;
    }

    /**
     * @param idCarrinho
     */
    public void setIdCarrinho(Long idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    /**
     * @return
     */
    public List<ItemCarrinho> getItens() {
        return itens;
    }

    /**
     * @param itens
     */
    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    /**
     * @param item
     *  Função para adicionar um item ao array do carrinho.
     */
    public void addItem(ItemCarrinho item){
        this.itens.add(item);
    }

    /**
     * @param item
     *  Função para remover o item do carrinho pelo próprio item.
     */
    public void removeItem(ItemCarrinho item){
        this.itens.remove(item);
    }
    
    /**
     * @param idItem
     *  Função para remover o item do array do carrinho pelo ID do item.
     */
    public void removeItemById(Long idItem){
        this.itens.removeIf(itens -> itens.getId().equals(idItem));
    }

    /**
     * @param quantidade
     *  Função para adicionar ou diminuir a quantidade de item do produto no carrinho.
     */
    public void addQuantidadeProduto(int quantidade){
        ((Carrinho) this.itens).addQuantidadeProduto(quantidade);
    }

}
