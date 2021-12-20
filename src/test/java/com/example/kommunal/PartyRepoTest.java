package com.example.kommunal;

import com.example.kommunal.models.Party;
import com.example.kommunal.models.PartyMember;
import com.example.kommunal.repositories.PartyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class PartyRepoTest {

    @Autowired
    PartyRepository parties;

    @Test
    public void testCreateParty() {
        Party party = new Party();

        party.setPartyName("De unge mødre");
        party.setPartyInitial("M");
        party.setPartyMembers((long)4);
        party.setPartyVotePercent((long)22);

        Party savedParty = parties.save(party);
        Assertions.assertNotEquals(savedParty,null);

    }

    @Test
    public void testDeleteParty() {
        //tjek id'et fra databasen og paste det så derefter ind i partyId (skal gøres med andre)

        Long partyId = (long)7;
        parties.deleteById(partyId);

        Optional<Party> optionalParty = parties.findById(partyId);
        Assertions.assertFalse(optionalParty.isPresent());

    }

    @Test
    public void testUpdateParty() {
        Long partyId = (long)7;

        Optional<Party> optionalParty = parties.findById(partyId);
        Party party = optionalParty.get();

        party.setPartyName("De unge fædre");
        parties.save(party);

        Party updatedparty = parties.findById(partyId).get();
        Assertions.assertEquals(updatedparty.getPartyName(), "De unge fædre");

    }

    @Test
    public void testReadParty() {
        Long partyId = (long)7;

        Optional<Party> optionalParty = parties.findById(partyId);
        Assertions.assertTrue(optionalParty.isPresent());
    }
}
