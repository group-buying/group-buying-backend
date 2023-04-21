package shop.donutmarket.donut.domain.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.board.model.Tag;
import shop.donutmarket.donut.domain.board.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class TagService {
    
    private final TagRepository tagRepository;

    public List<String> 상세보기(Long id) {

        List<Tag> tags = tagRepository.findAllByBoardId(id);
        List<String> tagList = new ArrayList<>();
        for (Tag tag : tags) {
            String tagcomment = tag.getComment();
            tagList.add(tagcomment);
        }
        return tagList;
    }
}
