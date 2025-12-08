package com.main.entity;

import com.main.core.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@AllArgsConstructor
@Builder
@Data
@Audited
public class Transaction extends BaseEntity {

    private Account senderAccount;
    private Account receiverAccount;

}
