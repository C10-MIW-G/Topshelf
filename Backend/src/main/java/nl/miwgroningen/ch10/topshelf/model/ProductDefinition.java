package nl.miwgroningen.ch10.topshelf.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * The definition of a product
 */

@Entity
@Getter @Setter @NoArgsConstructor
public class ProductDefinition {

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    List<StockProduct> stockProducts;

    @OneToMany(cascade = CascadeType.ALL)
    List<BasicStockProduct> basicStockProducts;

    @Override
    public String toString() {
        return "Product: " + name;
    }
}
