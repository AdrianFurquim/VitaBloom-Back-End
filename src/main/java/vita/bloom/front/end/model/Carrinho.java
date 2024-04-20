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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idCarrinho;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    protected List<ItemCarrinho> itens = new ArrayList<>();

    // Construtor vazio para JDBC.
    public Carrinho() {
    }

    public void adicionarItem(ItemCarrinho item) {
        this.itens.add(item);
        item.setCarrinho(this);
    }

    public List<ItemCarrinho> getItens() {
        return this.itens;
    }

    public Long getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(Long idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public void setItens(List<ItemCarrinho> itens) {
        this.itens = itens;
    }

    public Long getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

}
