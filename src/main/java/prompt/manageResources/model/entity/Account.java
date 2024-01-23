package prompt.manageResources.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import prompt.manageResources.model.enums.Auth;
import prompt.manageResources.model.enums.Dept;
import prompt.manageResources.model.enums.Position;
import prompt.manageResources.util.BooleanToYNConverter;
import java.time.Instant;

@Entity
@Table(name = "account")
@Getter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE account SET del_yn = 'Y' WHERE id=?")
public class Account {

    public static final String ANONYMOUS = "ANONYMOUS";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @UpdateTimestamp
    private Instant updateDt;

    @CreationTimestamp
    private Instant createDt;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "author")
    private Auth auth;

    @Enumerated(EnumType.STRING)
    @Column(name = "position")
    private Position position;

    @Enumerated(EnumType.STRING)
    @Column(name = "dept")
    private Dept dept;

    @Column(name = "is_deleted")
    @Convert(converter = BooleanToYNConverter.class)
    private boolean is_deleted = false;

    public void setPassword(String pw) {
        this.password = pw;
    }

}
