package shop.donutmarket.donut.domain.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.model.Tag;
import shop.donutmarket.donut.domain.board.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class TagService {
    
    private final TagRepository tagRepository;

    public List<Tag> 상세보기(Long id) {

        List<Tag> tags = tagRepository.findAllByBoardId(id);
        
        return tags;
    }
}
