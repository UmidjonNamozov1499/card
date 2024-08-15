package card.uz.card.service;

import card.uz.card.dto.CardCreatRequest;
import card.uz.card.dto.CardDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CardService {
    ResponseEntity<CardDtoResponse> create(UUID IdempotencyKey, CardCreatRequest creatRequest);
    ResponseEntity<CardDtoResponse> readCard(Long cardId);
    ResponseEntity<String> blockCard(Long cardId);
    ResponseEntity<String> unBlockCard(Long cardId);
}
