package com.main.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.main.core.entity.BaseEntity;
import com.main.core.serializer.IdLabelSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Audited
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Account extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonSerialize(using = IdLabelSerializer.class)
    private User user;

    @Column(nullable = false)
    private double amount;

}

