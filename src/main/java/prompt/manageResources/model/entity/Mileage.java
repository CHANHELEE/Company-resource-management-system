package prompt.manageResources.model.entity;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "mileage")
@Getter
@Setter
@NoArgsConstructor
public class Mileage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    @UpdateTimestamp
    private Instant updateDt;

    @CreationTimestamp
    private Instant createDt;

    //lazy 설정을 해도 , eager로 동작함
    //-> OneToOne 관계에서 연관관계의 주인이 아니기 때문.
    @NotNull
    @OneToOne(mappedBy = "mileage", optional = false)
    private Account account;

}
