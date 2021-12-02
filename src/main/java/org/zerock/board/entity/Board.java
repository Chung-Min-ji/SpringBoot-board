package org.zerock.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
// Member.email(PK) 을 FK로 참조하는 구조.
// 초기 설정에서는 우선 연관관계 작성하지 않고 순수하게 작성함.
// 나중에 회원과의 연관관계를 고려해서 작성자에 해당하는 필드는 작성하지 않는다.
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    @ManyToOne(fetch=FetchType.LAZY)  // N:1 (Board N : Writer 1)
                // DB에서 외래키의 관계로 연결된 엔티티 클래스에 설정
                // 명시적으로 Lazy로딩 지정
    private Member writer; // 연관관계 지정
}
