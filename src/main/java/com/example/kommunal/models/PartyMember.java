package com.example.kommunal.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "members")
public class PartyMember {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long partyMemberId;
    @Column
    private String memberName;
    @Column
    private String memberParty;
    @Column
    private Long voteCount;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Party party;
}
