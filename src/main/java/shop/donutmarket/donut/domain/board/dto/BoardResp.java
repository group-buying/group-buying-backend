package shop.donutmarket.donut.domain.board.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Event;
import shop.donutmarket.donut.domain.board.model.Tag;


public class BoardResp {
    
    @Getter
    @Setter
    @AllArgsConstructor
    public static class BoardDetailRespDTO {
        private Board board;
        private List<Tag> tagList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class BoardSaveRespDTO {
        private Board board;
        private Event event;
        private List<Tag> tagList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class BoardUpdateRespDTO {
        private Board board;
        private Event event;
        private List<Tag> tagList;
    }
}

