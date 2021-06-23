package uos.samsam.wing.domain.report;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.BaseTimeEntity;
import uos.samsam.wing.domain.padbox.PadBox;

import javax.persistence.*;

/**
 * Report
 * 신고를 나타내는 도메인 클래스입니다.
 * DB에 접근할 때 사용하는 클래스입니다.
 *
 * BastTimeEntity를 상속받습니다.
 */
@Getter
@NoArgsConstructor
@Entity
public class Report extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 어떤 생리대함에 대한 신고인지 구분하기 위해 PadBox 클래스와 연관관계를 형성합니다.
     */
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="BOX_ID")
    private PadBox padBox;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportTag tag;


    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isResolved;

    @Builder
    public Report(Long id, PadBox padBox, ReportTag tag, String content, Boolean isResolved) {
        this.id = id;
        this.padBox = padBox;
        this.tag = tag;
        this.content = content;
        this.isResolved = isResolved;
    }

    public void update(Boolean isResolved) {
        this.isResolved = isResolved;
    }
}
