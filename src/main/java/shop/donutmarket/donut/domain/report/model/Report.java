package shop.donutmarket.donut.domain.report.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Report {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reporter_id")
    private Long reporterId; 
    @Column(name = "reported_id")
    private Long reportedId;
    @Column(name = "board_id")
    private Long boardId;
    private String title;
    private String content;
    @Column(name = "report_type")
    private String reportType;
    @Column(name = "status_code")
    private int statusCode;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Report(Long id, Long reporterId, Long reportedId, Long boardId, String title, String content,
            String reportType, int statusCode, LocalDateTime createdAt) {
        this.id = id;
        this.reporterId = reporterId;
        this.reportedId = reportedId;
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.reportType = reportType;
        this.statusCode = statusCode;
        this.createdAt = createdAt;
    }

}
