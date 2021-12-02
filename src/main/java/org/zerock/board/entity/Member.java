package org.zerock.board.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
// 이메일주소를 PK로 하고, FK를 사용하지 않으므로 별도의 참조 필요 없음
public class Member extends BaseEntity {

    @Id
    private String email;

    private String password;

    private String name;
}
