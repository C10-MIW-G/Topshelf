package nl.miwgroningen.ch10.topshelf.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch10.topshelf.dto.PantryDTO;
import nl.miwgroningen.ch10.topshelf.dto.PantryUsersDTO;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.security.config.JwtService;
import nl.miwgroningen.ch10.topshelf.service.PantryService;
import nl.miwgroningen.ch10.topshelf.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * Talks with the frontend
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pantry")
@RequiredArgsConstructor
public class PantryController {

    private final JwtService jwtService;
    private final PantryService pantryService;
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<PantryDTO>> getAllPantries() {
        List<PantryDTO> pantries = pantryService.findAllPantries();

        return new ResponseEntity<>(pantries, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addPantry(@RequestBody PantryDTO pantryDTO,
                                            @RequestHeader(name = "Authorization") String jwt) {
        Pantry savedPantry = pantryService.addPantry(pantryDTO);
        String username = jwtService.extractUsername(jwt.substring(7));
        pantryService.addUserToPantry(username, savedPantry);
        pantryService.addAdminToPantry(username, savedPantry);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all/user")
    public ResponseEntity<List<PantryDTO>> getPantriesOfUser(@RequestHeader(name = "Authorization") String jwt) {
        String username = jwtService.extractUsername(jwt.substring(7));
        List<PantryDTO> pantries = pantryService.findPantriesByUser(username);

        return new ResponseEntity<>(pantries, HttpStatus.CREATED);
    }

    @GetMapping("/findusers/{pantryId}")
    public ResponseEntity<List<PantryUsersDTO>> getUsersByPantry(@PathVariable("pantryId") Long pantryId) {
        List<PantryUsersDTO> users = userService.checkIfUserIsAdmin(pantryId);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/findadmins/{pantryId}")
    public ResponseEntity<List<PantryUsersDTO>> getAdminsByPantry(@PathVariable("pantryId") Long pantryId) {
        List<PantryUsersDTO> admins = userService.findAdminsByPantryId(pantryId);

        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{pantryId}")
    public ResponseEntity<String> deletePantry(
            @PathVariable("pantryId") Long pantryId) {
        pantryService.deletePantry(pantryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}