package vita.bloom.front.end.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
    /**
     *  Id unico de cada produto gerado autimaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idProduto;

    /**
     *  Nome do produto.
     */
    protected String nomeProduto;

    /**
     *  Valor do produto.
     */
    protected double valorProduto;

    /**
     *  Descrição do produto.
     */
    protected String descricaoProduto;

    /**
     *  Contrutor vazio para JDBC.
     */
    public Produto(){
    }

    /**
     * @param nomeProduto
     * @param valorProduto
     * @param descricaoProduto
     *   Contrutor do produto.
     */
    public Produto(String nomeProduto, double valorProduto, String descricaoProduto) {
        this.nomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
        this.descricaoProduto = descricaoProduto;
    }

    /**
     * @return
     */
    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     * @param nomeProduto
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /**
     * @return
     */
    public double getValorProduto() {
        return valorProduto;
    }

    /**
     * @param valorProduto
     */
    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }

    /**
     * @return
     */
    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    /**
     * @param descricaoProduto
     */
    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    /**
     * @return
     */
    public Long getIdProduto() {
        return idProduto;
    }

    /**
     * @param idProduto
     */
    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

}
