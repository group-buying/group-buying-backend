package shop.donutmarket.donut.domain.board.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.board.model.Tag;


public class BoardResp {
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardDetailRespDTO {
        private Board board;
        private List<String> tagList;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardSaveRespDTO {
        private Board board;
        private List<Tag> tagList;
    }

    @Getter
    @Setter
    public static class BoardUpdateRespDTO {
        private Board board;
        private List<Tag> tagList;

        public BoardUpdateRespDTO(Board board, List<Tag> tagList) {
            this.board = board;
            this.tagList = tagList;
        }
    }
}

