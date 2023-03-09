package nl.miwgroningen.ch10.topshelf.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * The definition of a pantry
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pantry {

    @Id
    @GeneratedValue
    private Long pantryId;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "stockProductId")
    private List<StockProduct> stock;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "basicStockProductId")
    private List<BasicStockProduct> basicStock;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<User> users;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<User> admins;

}
