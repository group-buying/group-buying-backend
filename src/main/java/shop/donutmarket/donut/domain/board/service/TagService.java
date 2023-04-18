package shop.donutmarket.donut.domain.board.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class TagService {
    
    private final TagRepository tagRepository;
}
