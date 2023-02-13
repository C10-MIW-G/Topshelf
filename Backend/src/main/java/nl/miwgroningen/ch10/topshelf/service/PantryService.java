package nl.miwgroningen.ch10.topshelf.service;
import nl.miwgroningen.ch10.topshelf.dto.PantryDTO;
import nl.miwgroningen.ch10.topshelf.exception.PantryNotFoundException;
import nl.miwgroningen.ch10.topshelf.mapper.PantryDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.repository.PantryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Controls the pantryRepository
 */

@Service
public class PantryService {
    private final PantryRepository pantryRepository;
    private final PantryDTOMapper pantryDTOMapper;

    @Autowired
    public PantryService(PantryRepository pantryRepository,
                         PantryDTOMapper pantryDTOMapper) {
        this.pantryRepository = pantryRepository;
        this.pantryDTOMapper = pantryDTOMapper;
    }

    public List<PantryDTO> findAllPantries() {
        return pantryRepository.findAll()
                .stream()
                .map(pantryDTOMapper)
                .collect(Collectors.toList());
    }

    public PantryDTO findPantryDTOByPantryId(Long pantryId) {
        return pantryDTOMapper.apply(findPantryByPantryId(pantryId));
    }

    public Pantry findPantryByPantryId(Long pantryId) {
        return pantryRepository.findPantryByPantryId(pantryId)
                .orElseThrow(() -> new PantryNotFoundException("Pantry with id: " + pantryId + " was not found!"));
    }
}

