package nl.miwgroningen.ch10.topshelf.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * The definition of a pantry
 */

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Pantry {

    @Id
    @GeneratedValue
    private Long pantryId;

    private String name;

    public Pantry(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pantry")
    private List<StockProduct> stock;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pantry")
    private List<BasicStockProduct> basicStock;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pantry")
    private List<GroceryProduct> groceryProducts;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<User> users;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<User> admins;
}
