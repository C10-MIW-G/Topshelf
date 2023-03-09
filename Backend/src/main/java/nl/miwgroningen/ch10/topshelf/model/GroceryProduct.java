package nl.miwgroningen.ch10.topshelf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Auteur Jessica Schouten.
 * <p>
 * The definition of a product that is on a GroceryList
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroceryProduct {

    @Id
    @GeneratedValue
    private Long groceryProductId;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "name", referencedColumnName = "name")
    private ProductDefinition productDefinition;

    @ManyToOne
    @JoinColumn(name = "pantryId", referencedColumnName = "pantryId")
    private Pantry pantry;
}
