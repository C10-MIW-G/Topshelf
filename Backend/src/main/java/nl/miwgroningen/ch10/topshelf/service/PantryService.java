package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.dto.PantryDTO;
import nl.miwgroningen.ch10.topshelf.exception.PantryNotFoundException;
import nl.miwgroningen.ch10.topshelf.mapper.PantryDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.repository.PantryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * Controls the pantryRepository
 */

@Service
public class PantryService {
    private final PantryRepository pantryRepository;
    private final PantryDTOMapper pantryDTOMapper;
    private final UserService userService;

    @Autowired
    public PantryService(PantryRepository pantryRepository, PantryDTOMapper pantryDTOMapper, UserService userService) {
        this.pantryRepository = pantryRepository;
        this.pantryDTOMapper = pantryDTOMapper;
        this.userService = userService;
    }

    public List<PantryDTO> findAllPantries() {
        return pantryRepository.findAll()
                .stream()
                .map(pantryDTOMapper)
                .toList();
    }

    public Pantry findPantryByPantryId(Long pantryId) {
        return pantryRepository.findPantryByPantryId(pantryId)
                .orElseThrow(() -> new PantryNotFoundException("Pantry with id: " + pantryId + " was not found!"));
    }

    public Pantry addPantry(PantryDTO pantryDTO) {
        Pantry pantry = pantryDTOMapper.toPantry(pantryDTO);
        return pantryRepository.save(pantry);
    }

    public void addAdminToPantry(String username, Pantry pantry) {
        User newPantryAdmin = userService.findUserByUsername(username);
        pantry.getAdmins().add(newPantryAdmin);
        pantryRepository.save(pantry);
    }

    public void addUserToPantry(String username, Pantry pantry) {
        User userToBeAdded = userService.findUserByUsername(username);
        pantry.getUsers().add(userToBeAdded);
        pantryRepository.save(pantry);
    }

    public List<PantryDTO> findPantriesByUser(String username) {
        User userToBeSearched = userService.findUserByUsername(username);
        List<PantryDTO> newlist = pantryRepository.findPantriesByUsers(userToBeSearched)
                .stream()
                .map(pantryDTOMapper)
                .toList();
        return newlist;
    }

    public boolean checkIfUserIsPartOfPantry(String username, Pantry pantry) {
        List<PantryDTO> pantries = findPantriesByUser(username)
                .stream()
                .filter(p -> pantry.getPantryId().equals(p.pantryId()))
                .toList();
        return pantries.isEmpty();
    }
}

