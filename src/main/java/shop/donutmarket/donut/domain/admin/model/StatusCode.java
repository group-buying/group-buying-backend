package shop.donutmarket.donut.domain.admin.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class StatusCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long statusNumber;
    private String type;
    private String status;
    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public StatusCode(Long id, Long statusNumber, String type, String status, LocalDateTime createdAt) {
        this.id = id;
        this.statusNumber = statusNumber;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
    }
}
