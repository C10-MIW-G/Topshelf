package nl.miwgroningen.ch10.topshelf.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * The definition of a pantry
 */

@Entity
@Getter @Setter @NoArgsConstructor
public class Pantry implements Serializable {

    @Id
    @GeneratedValue
    private Long pantryId;

    private String name;

    @ManyToMany(mappedBy = "pantries", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ProductDefinition> stock;

    public void addProductToStock (ProductDefinition productDefinition) {
        stock.add(productDefinition);
        productDefinition.getPantries().add(this);
    }
}
