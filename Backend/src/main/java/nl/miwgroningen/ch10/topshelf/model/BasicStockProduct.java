package nl.miwgroningen.ch10.topshelf.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * The definition of a product that the user wishes to have in stock at all time is called a BasicStockProduct
 */

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class BasicStockProduct {

    @Id
    @GeneratedValue
    private Long basicStockProductId;

    private int amount;

    // The cascade type will assure entities will be persisted, merged, and removed when the BasicStockProduct instance
    // is persisted,merged, and removed.
    // The associated entities will be lazily loaded when loading BasicStockProduct instance.
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "name", referencedColumnName = "name")
    private ProductDefinition productDefinition;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "pantryId", referencedColumnName = "pantryId")
    private Pantry pantry;
}
