package nl.miwgroningen.ch10.topshelf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * The definition of a product
 */

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductDefinition {

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Override
    public String toString() {
        return "Product: " + name;
    }
}
