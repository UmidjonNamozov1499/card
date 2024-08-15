package card.uz.card.dto;

import card.uz.card.model.CardStatus;
import card.uz.card.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDtoResponse {
private UUID card_id;
private Long user_id;
private CardStatus cardStatus;
private BigDecimal balance;
private Currency currency;
}
