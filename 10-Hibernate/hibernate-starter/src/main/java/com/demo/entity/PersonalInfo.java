package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable // встраиваемые компонент
public class PersonalInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String firstname;
    private String lastname;
    //        @Convert(converter = BirthdayConverter.class) // приобразование типов полей таблицы в типы полей класса сущности
    @NotNull
    private LocalDate birthDate;
}