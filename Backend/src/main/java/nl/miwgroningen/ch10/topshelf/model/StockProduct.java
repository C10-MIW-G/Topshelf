package nl.miwgroningen.ch10.topshelf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * A productdefinition on the shelf in our pantry is called a stockproduct
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockProduct {

    @Id
    @GeneratedValue
    private Long stockProductId;

    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "name", referencedColumnName = "name")
    private ProductDefinition productDefinition;

    @ManyToOne
    @JoinColumn(name = "pantryId", referencedColumnName = "pantryId")
    private Pantry pantry;


    private boolean stockStatus;

    public StockProduct(
            Long stockProductId, LocalDate expirationDate, ProductDefinition productByName, Pantry pantryByPantryId) {
        this.stockProductId = stockProductId;
        this.expirationDate = expirationDate;
        this.productDefinition = productByName;
        this.pantry = pantryByPantryId;
    }
}
