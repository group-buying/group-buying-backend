package shop.donutmarket.donut.domain.board.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.repository.EventRepository;

@Service
@RequiredArgsConstructor
public class EventService {    
    private final EventRepository eventRepository;
}
