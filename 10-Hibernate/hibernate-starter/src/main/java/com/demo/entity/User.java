package com.demo.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.demo.util.StringUtils.SPACE;

@NamedEntityGraph(
        name = "WithCompany",
        attributeNodes = {
                @NamedAttributeNode(value = "company")
        }
)
@NamedEntityGraph(
        name = "WithCompanyAndChat",
        attributeNodes = {
                @NamedAttributeNode("company"),
                @NamedAttributeNode(value = "userChats", subgraph = "chats")
        },
        subgraphs = {
                @NamedSubgraph(name = "chats", attributeNodes = @NamedAttributeNode("chat"))
        }
)
@FetchProfile(name = "withCompanyAndPayment", fetchOverrides = {
        @FetchProfile.FetchOverride(
                entity = User.class, association = "company", mode = FetchMode.JOIN
        ),
        @FetchProfile.FetchOverride(
                entity = User.class, association = "payments", mode = FetchMode.JOIN
        )
})
@NamedQuery(name = "findUserByName", query = "select u from User u " +
        "left join u.company c " +
        "where u.personalInfo.firstname = :firstname and c.name = :companyName " +
        "order by u.personalInfo.lastname desc")
@Data // equals, hashCode, getters & setters methods
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username")
@ToString(exclude = {"company", "userChats", "payments"}) // исключить вывод поля company из вывода метода toString()
@Builder
@Entity // указанный POJO class - сущность Hibernate
@Table(name = "users", schema = "public")
// по умолчанию Hibernate берет название полей/класса в качестве названия таблицы/колонок [ (SQL не чувствителен к регистру))
@TypeDef(name = "demo", typeClass = JsonBinaryType.class) // псевдоним для пользовательского типа
@Inheritance(strategy = InheritanceType.JOINED)
@Audited
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Users")
public class User implements Comparable<User>, BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  сентетический идетификатор, который генерируется БД
    private Long id;

    @Valid
    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;
    // columnDefinition жестко задает DDL команду при автогенерации таблиц inMemory DB
    @NotNull
    @Column(unique = true) // уникальная колонка
    private String username;

    @Type(type = "demo") // указать новый тип и зарегистрировать его в Hibernate, type - полный путь к классу
    private String info;

    @Enumerated(EnumType.STRING)
    // убрать использование ordinal() как цифровой идентификатор типа Enum (по порядку при инициализации) и задать текстовый формат полем name (имя ENUM, указанное при инициализации)
    private Role role;
// fetch = FetchType.LAZY - ленивое инициализя подобъектов (по умолчанию для коллекций), FetchType.EAGER(жадня иницализация подобъектов)

//    @OneToOne(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id") // company_id
//    @Fetch(FetchMode.JOIN)
    private Company company;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    @NotAudited
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<UserChat> userChats = new ArrayList<>();

    @NotAudited
    @Builder.Default
//    @BatchSize(size = 3)
//    1 + N -> 1 + 500 -> 1 + 500/3 -> 3
//    1 + N -> 1 + 1 -> 2
//    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "receiver")
    private List<Payment> payments = new ArrayList<>();

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.username);
    }

    public String fullName() {
        return getPersonalInfo().getFirstname() + SPACE + getPersonalInfo().getLastname();
    }
}




