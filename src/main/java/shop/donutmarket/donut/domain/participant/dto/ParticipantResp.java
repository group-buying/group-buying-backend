package shop.donutmarket.donut.domain.participant.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.donutmarket.donut.domain.participant.model.Participant;

public class ParticipantResp {
    
    @Getter
    @Setter
    public static class ParticipantListRespDTO {
        private List<Participant> participant;

        public ParticipantListRespDTO(List<Participant> participant) {
            this.participant = participant;
        }
    }

    @Getter
    @Setter
    public static class ParticipantSaveRespDTO {
        private Participant participant;

        public ParticipantSaveRespDTO(Participant participant) {
            this.participant = participant;
        }
    }

    
    @Getter
    @Setter
    public static class ParticipantSelectRespDTO {
        private Participant participant;

        public ParticipantSelectRespDTO(Participant participant) {
            this.participant = participant;
        }
    }

    @Getter
    @Setter
    public static class ParticipantCancelRespDTO {
        private Participant participant;

        public ParticipantCancelRespDTO(Participant participant) {
            this.participant = participant;
        }
    }

    @Getter
    @Setter
    public static class ParticipantDropRespDTO {
        private Participant participant;

        public ParticipantDropRespDTO(Participant participant) {
            this.participant = participant;
        }
    }

}
