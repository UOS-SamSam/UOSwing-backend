package uos.samsam.wing.domain.report;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportTag tag;


    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public Report(Long id, ReportTag tag, String content) {
        this.id = id;
        this.tag = tag;
        this.content = content;
    }

    public void update(ReportTag tag, String content) {
        this.tag = tag;
        this.content = content;
    }
}
