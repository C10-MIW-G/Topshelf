package nl.miwgroningen.ch10.topshelf.mapper;

import nl.miwgroningen.ch10.topshelf.dto.PantryDTO;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author Inge Dikland
 * <p>
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
}
