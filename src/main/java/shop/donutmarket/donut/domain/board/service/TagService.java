package shop.donutmarket.donut.domain.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import shop.donutmarket.donut.domain.board.model.Tag;
import shop.donutmarket.donut.domain.board.repository.TagRepository;
import shop.donutmarket.donut.global.exception.Exception500;

@Service
@RequiredArgsConstructor
public class TagService {
    
    private final TagRepository tagRepository;

    @Transactional(readOnly = true)
    public List<String> 상세보기(Long id) {
        try {
            List<Tag> tags = tagRepository.findAllByBoardId(id);
            List<String> tagList = new ArrayList<>();
            for (Tag tag : tags) {
                String tagComment = tag.getComment();
                tagList.add(tagComment);
            }
            return tagList;
        } catch (Exception e) {
            throw new Exception500("태그 상세보기 실패 : " + e.getMessage());
        }
    }
}
