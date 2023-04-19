package shop.donutmarket.donut.domain.board.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Tag;


public class BoardResp {
    
    @Getter
    @Setter
    @AllArgsConstructor
    public static class BoardDetailRespDTO {
        private Board board;
        private List<Tag> tagList;
    }
}

