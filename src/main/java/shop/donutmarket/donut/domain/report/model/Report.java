package shop.donutmarket.donut.domain.report.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.donutmarket.donut.domain.admin.model.StatusCode;
import shop.donutmarket.donut.domain.board.model.Board;
import shop.donutmarket.donut.domain.user.model.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporter;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_id")
    private User reported;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;
    private String title;
    private String content;
    @Column(name = "report_type")
    private String reportType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_code")
    private StatusCode statusCode;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public Report(Long id, User reporter, User reported, Board board, String title, String content, String reportType, StatusCode statusCode, LocalDateTime createdAt) {
        this.id = id;
        this.reporter = reporter;
        this.reported = reported;
        this.board = board;
        this.title = title;
        this.content = content;
        this.reportType = reportType;
        this.statusCode = statusCode;
        this.createdAt = createdAt;
    }
}
