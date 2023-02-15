package nl.miwgroningen.ch10.topshelf.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * A user can manage their minimal stock in their Pantry
 */

@Entity
@Getter @Setter @NoArgsConstructor
public class BasicStockProduct {

    @Id
    @GeneratedValue
    private Long basicStockProductId;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "name", referencedColumnName = "name")
    private ProductDefinition productDefinition;

    @ManyToOne
    @JoinColumn(name = "pantryId", referencedColumnName = "pantryId")
    private Pantry pantry;

    public BasicStockProduct(ProductDefinition productDefinition, Pantry pantry, int amount) {
        this.productDefinition = productDefinition;
        this.pantry = pantry;
        this.amount = amount;
    }
}
