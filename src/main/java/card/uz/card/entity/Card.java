package card.uz.card.entity;

import card.uz.card.model.CardStatus;
import card.uz.card.model.Currency;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "card")
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users_id;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardStatus status = CardStatus.ACTIVE;
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency = Currency.UZS;
    @Column(nullable = false)
    private UUID idempotencyKey;
}
