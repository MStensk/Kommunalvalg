package com.example.kommunal.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "parties")
public class Party {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long partyId;
    @Column
    private String partyInitial;
    @Column
    private String partyName;
    @Column
    private Long partyMembers;
    @Column
    private Long partyVotePercent;
    @Column
    private String partyLogo;

}
