package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository //Component Scan 대상이되므로 자동으로 Bean 관리
@RequiredArgsConstructor //3. 최종적으로 PersistenceContext와 Autowired를 전부 생략 가능.
public class MemberRepository {

    // @PersistenceContext //1. JPA가 제공하는 표준 Annotation,
                        //2. Spring Data JPA가 PersistenceContext를 자동으로 해줘서 @Autowired로 변환 가능
    private final EntityManager em; //Spring이 EntityManager를 만들어줘서 주입해줌

    //    @PersistenceUnit //Entity Factrory를 직접 주입받고 싶은 경우
//    private EntityManagerFactory emf;

    public void save(Member member){
        em.persist(member); //이순간에 영속성 Context에 올라감 @Id가 PK값이 됨, 항상 ID에 값을 넣어줌
    }

    public Member findOne(Long id){ //단건 조회
        return em.find(Member.class, id); //type, PK
    }

    public List<Member> findAll() { //전체 조회
        return em.createQuery("select m from Member as m", Member.class) //jpql, 조회 타입은 Member class
                .getResultList();
    }

    public List<Member> findByName(String name){ //이르으로 조회
        return em.createQuery("select m from Member as m where m.name = :name", Member.class)
                .setParameter("name", name) //:name
                .getResultList();
    }
}
