package com.example.kommunal.controllers;

import com.example.kommunal.models.Party;
import com.example.kommunal.repositories.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PartyController {

    @Autowired
    private PartyRepository parties;

    @GetMapping("/parties")
    public List<Party> getParties() {
        return parties.findAll();
    }

    @GetMapping("/parties/{id}")
    public Party getPartyById(@PathVariable Long id) {
        return parties.findById(id).get();
    }

    @PostMapping("/parties")
    public Party addParty(@RequestBody Party newParty) {
        return parties.save(newParty);
    }

    @PatchMapping("/parties/{id}")
    public String patchPartyById(@PathVariable Long id, @RequestBody Party partyToUpdateWith) {
        return parties.findById(id).map(foundParty -> {
            if (partyToUpdateWith.getPartyName() != null) foundParty.setPartyName(partyToUpdateWith.getPartyName());
            if (partyToUpdateWith.getPartyInitial() != null)
                foundParty.setPartyInitial(partyToUpdateWith.getPartyInitial());
            if (partyToUpdateWith.getPartyMembers() != null)
                foundParty.setPartyMembers(partyToUpdateWith.getPartyMembers());
            if (partyToUpdateWith.getPartyVotePercent() != null)
                foundParty.setPartyVotePercent(partyToUpdateWith.getPartyVotePercent());
            if (partyToUpdateWith.getPartyLogo() != null) foundParty.setPartyLogo(partyToUpdateWith.getPartyLogo());
            parties.save(foundParty);
            return "Party has been updated";
        }).orElse("Party could not be found");
    }

    @PutMapping("/parties/{id}")
    public String updatePartyById(@PathVariable Long id, @RequestBody Party partyToUpdate) {
        if (parties.existsById(id)) {
            partyToUpdate.setPartyId(id);
            parties.save(partyToUpdate);
            return "Party was created";
        } else {
            return "Party could not be found";
        }
    }

    @DeleteMapping("/parties/{id}")
    public void deletePartyById(@PathVariable Long id) {
        parties.deleteById(id);
    }
}
