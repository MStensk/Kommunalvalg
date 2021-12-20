package com.example.kommunal.controllers;

import com.example.kommunal.models.PartyMember;
import com.example.kommunal.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MemberController {

    @Autowired
    MemberRepository members;

    @GetMapping("/members")
    public List<PartyMember> getMembers() {
        return members.findAll();
    }

    @GetMapping("/members/{id}")
    public PartyMember getMemberById(@PathVariable Long id) {
        return members.findById(id).get();
    }

    @PostMapping("/members")
    public PartyMember addMember(@RequestBody PartyMember newMember) {
        return members.save(newMember);
    }

    @PatchMapping("/members/{id}")
    public String patchMemberById(@PathVariable Long id, @RequestBody PartyMember memberToUpdateWith) {
        return members.findById(id).map(foundMember -> {
            if (memberToUpdateWith.getMemberName() != null) foundMember.setMemberName(memberToUpdateWith.getMemberName());
            if (memberToUpdateWith.getMemberParty() != null)
                foundMember.setMemberParty(memberToUpdateWith.getMemberParty());
            if (memberToUpdateWith.getVoteCount() != null)
                foundMember.setVoteCount(memberToUpdateWith.getVoteCount());

            return "Party member has been updated";
        }).orElse("Party member could not be found");
    }

    @PutMapping("/members/{id}")
    public String updateMemberById(@PathVariable Long id, @RequestBody PartyMember memberToUpdate) {
        if (members.existsById(id)) {
            memberToUpdate.setPartyMemberId(id);
            members.save(memberToUpdate);
            return "Party member was created";
        } else {
            return "Party member could not be found";
        }
    }

    @DeleteMapping("/members/{id}")
    public void deleteMemberById(@PathVariable Long id) {
        members.deleteById(id);
    }

    @GetMapping("/members/party/{id}")
    public List<PartyMember> getPartyMemberByParty(@PathVariable Long id){

    return members.findByParty_PartyId(id);

    }
}
