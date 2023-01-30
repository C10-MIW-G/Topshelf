package nl.miwgroningen.ch10.topshelf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * The definition of a pantry
 */

@Entity
@Getter @Setter @NoArgsConstructor
public class Pantry {

    @Id
    @GeneratedValue
    private Long pantryId;

    private String name;

    @ManyToMany
    private Set<ProductDefinition> stock;

    public void addProductToStock (ProductDefinition productDefinition) {
        stock.add(productDefinition);
        productDefinition.getPantries().add(this);
    }
}
