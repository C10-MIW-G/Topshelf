package nl.miwgroningen.ch10.topshelf.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.Set;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * The definition of a product
 */

@Entity
@Getter @Setter @NoArgsConstructor
public class ProductDefinition {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private LocalDate expirationDate;
    private int productAmount;

    @ManyToMany
    public Set<Pantry> pantries;

    @Override
    public String toString() {
        return "Product: " + name + " Amount: " + productAmount + " Expiration date: " + expirationDate;
    }
}
