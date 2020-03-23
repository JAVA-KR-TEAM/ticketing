package kr.team.ticketing.domain.member;

import kr.team.ticketing.domain.BaseEntity;
import kr.team.ticketing.domain.object.Email;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{
    @Column
    private String name;
    @Embedded
    private Email email;

    @Builder
    public Member(Long id, String name, Email email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
