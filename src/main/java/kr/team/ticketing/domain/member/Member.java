package kr.team.ticketing.domain.member;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "MEMBERS")
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;
}
