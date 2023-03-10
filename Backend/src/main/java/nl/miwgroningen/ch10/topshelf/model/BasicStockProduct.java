package nl.miwgroningen.ch10.topshelf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * The definition of a product that the user wishes to have in stock at all time is called a BasicStockProduct
 */

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
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
}
