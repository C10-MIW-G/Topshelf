package nl.miwgroningen.ch10.topshelf.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * A productdefinition on the shelf in our pantry is called a stockproduct
 */

@Entity
@Getter @Setter @NoArgsConstructor
public class StockProduct {

    @Id
    @GeneratedValue
    private Long stockProductId;

    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "name", referencedColumnName = "name")
    private ProductDefinition productDefinition;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pantryId", referencedColumnName = "pantryId")
    private Pantry pantry;

    public StockProduct(LocalDate expirationDate, ProductDefinition productDefinition, Pantry pantry) {
        this.expirationDate = expirationDate;
        this.productDefinition = productDefinition;
        this.pantry = pantry;
    }
}
