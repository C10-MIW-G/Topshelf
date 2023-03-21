package nl.miwgroningen.ch10.topshelf.mapper;

import nl.miwgroningen.ch10.topshelf.dto.PantryDTO;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * @author Inge Dikland
 * This class makes it possible to make use of DTO's
 */

@Service
public class PantryDTOMapper implements Function<Pantry, PantryDTO> {
    @Override
    public PantryDTO apply(Pantry pantry) {
        return new PantryDTO(
                pantry.getPantryId(),
                pantry.getName()
        );
    }

    public Pantry toPantry(PantryDTO pantryDTO){
        Pantry pantry = new Pantry();
        pantry.setPantryId(pantryDTO.pantryId());
        pantry.setName(pantryDTO.name());
        pantry.setUsers(new ArrayList<>());
        pantry.setAdmins(new ArrayList<>());
        return pantry;
    }
}
