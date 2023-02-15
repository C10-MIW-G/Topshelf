package nl.miwgroningen.ch10.topshelf.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "stockProductId")
    private List<StockProduct> stock;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "basicStockProductId")
    private List<BasicStockProduct> basicStock;

}
