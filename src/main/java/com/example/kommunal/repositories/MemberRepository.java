package com.example.kommunal.repositories;

import com.example.kommunal.models.PartyMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<PartyMember, Long> {

    List<PartyMember> findByParty_PartyId(Long id);
}
