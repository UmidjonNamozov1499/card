package card.uz.card.service.serviceImpl;

import card.uz.card.dto.CardCreatRequest;
import card.uz.card.dto.CardDtoResponse;
import card.uz.card.entity.Card;
import card.uz.card.entity.Users;
import card.uz.card.repository.CardRepository;
import card.uz.card.repository.UsersRepository;
import card.uz.card.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public class CardServiceImpl implements CardService {
    private final UsersRepository usersRepository;

    private final CardRepository cardRepository;
    private final CardService cardService;

    public CardServiceImpl(UsersRepository usersRepository,
                           CardRepository cardRepository,
                           CardService cardService) {
        this.usersRepository = usersRepository;
        this.cardRepository = cardRepository;
        this.cardService = cardService;
    }

    @Override
    public ResponseEntity<CardDtoResponse> create(UUID idempotencyKey, CardCreatRequest creatRequest) {
        try {
            if (idempotencyKey == null)
                return ResponseEntity.status(HttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE).body(null);
            if (creatRequest == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            Users users = usersRepository.findById(creatRequest.getUser_id()).orElse(null);
            if (users == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            List<Card> cardList = users.getCards();
            if (cardList.size() >= 3)
                return ResponseEntity.status(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED).body(null);
            Card card = new Card();
            card.setUsers_id(users);
            card.setStatus(creatRequest.getCardStatus());
            card.setBalance(creatRequest.getBalance());
            card.setCurrency(creatRequest.getCurrency());
            card.setIdempotencyKey(idempotencyKey);
            Card save = cardRepository.save(card);
            CardDtoResponse dtoResponse = new CardDtoResponse(save.getId(),
                    save.getUsers_id().getUser_id(),
                    save.getStatus(),
                    save.getBalance(),
                    save.getCurrency());
            return ResponseEntity.status(HttpStatus.OK).body(dtoResponse);
        } catch (Exception e) {
            String error = "Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<CardDtoResponse> readCard(Long cardId) {
        return null;
    }

    @Override
    public ResponseEntity<String> blockCard(Long cardId) {
        return null;
    }

    @Override
    public ResponseEntity<String> unBlockCard(Long cardId) {
        return null;
    }
}
