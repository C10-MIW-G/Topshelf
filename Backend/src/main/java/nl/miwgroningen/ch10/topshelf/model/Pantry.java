package nl.miwgroningen.ch10.topshelf.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "pantries")
    public Set<ProductDefinition> stock;

    public void addProductToStock (ProductDefinition productDefinition) {
        stock.add(productDefinition);
        productDefinition.getPantries().add(this);
    }
}
