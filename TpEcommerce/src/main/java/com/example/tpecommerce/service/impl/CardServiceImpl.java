package com.example.tpecommerce.service.impl;

import com.example.tpecommerce.model.Card;
import com.example.tpecommerce.model.ProductLine;
import com.example.tpecommerce.repository.ICardRepository;
import com.example.tpecommerce.service.ICardService;
import dto.CardDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CardServiceImpl implements ICardService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final ICardRepository cardRepository;

    @Override
    public CardDTO create(CardDTO cardDTO) {
        Card card = modelMapper.map(cardDTO, Card.class);
        return modelMapper.map(cardRepository.save(card), CardDTO.class);
    }

    @Override
    public CardDTO findById(Integer id) {
        Card card = cardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Card not found")
        );
        return modelMapper.map(card, CardDTO.class);
    }

    @Override
    public CardDTO update(Integer id, CardDTO cardDTO) {
        Card card = cardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Card not found")
        );

        List<ProductLine> productLines = cardDTO.getProductLines().stream().map(productLineDTO -> modelMapper.map(productLineDTO, ProductLine.class)).collect(Collectors.toList());
        card.setDate(cardDTO.getDate());
        card.setProductLines(productLines);
        return modelMapper.map(cardRepository.save(card), CardDTO.class);
    }

    @Override
    public CardDTO deleteById(Integer id) {

        Card card = cardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Card not found")
        );
        cardRepository.deleteById(id);
        return modelMapper.map(card, CardDTO.class);
    }


    @Override
    public List<CardDTO> findAll() {
        List<Card> cards = cardRepository.findAll();
        return modelMapper.map(cards, List.class);
    }
}
