package com.example.kommunal;

import com.example.kommunal.models.PartyMember;
import com.example.kommunal.repositories.MemberRepository;
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

public class MemberRepoTest {

    @Autowired
    private MemberRepository members;

    @Test
    public void testCreateMember() {
        PartyMember pmember = new PartyMember();
        pmember.setMemberName("Maximus Decimus Meridius");
        pmember.setMemberParty("Gladiators");
        pmember.setVoteCount((long) 34);

        PartyMember savedMember = members.save(pmember);
        Assertions.assertNotEquals(savedMember,null);

    }
    @Test
    public void testDeleteMember() {
        //tjek id'et fra databasen og paste det så derefter ind i memberId (skal gøres med andre)

        Long memberId = (long)38;
        members.deleteById(memberId);

        Optional<PartyMember> optionalPartyMember = members.findById(memberId);
        Assertions.assertFalse(optionalPartyMember.isPresent());

    }

    @Test
    public void testUpdateMember() {
        Long memberId = (long)38;

        Optional<PartyMember> optionalPartyMember = members.findById(memberId);
        PartyMember partyMember = optionalPartyMember.get();

        partyMember.setMemberName("Mao Zedong");
        members.save(partyMember);

        PartyMember updatedmember = members.findById(memberId).get();
        Assertions.assertEquals(updatedmember.getMemberName(), "Mao Zedong");

    }

    @Test
    public void testReadMember() {
        Long memberId = (long)38;

        Optional<PartyMember> optionalPartyMember = members.findById(memberId);
        Assertions.assertTrue(optionalPartyMember.isPresent());

    }

}
