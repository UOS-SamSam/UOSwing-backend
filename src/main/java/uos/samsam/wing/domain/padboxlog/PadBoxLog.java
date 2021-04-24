package uos.samsam.wing.domain.padboxlog;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uos.samsam.wing.domain.BaseTimeEntity;
import uos.samsam.wing.domain.padbox.PadBox;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class PadBoxLog extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BOX_ID")
    private PadBox padBox;          // 사용된 PadBox

    private Integer usedAmount;         // 사용 갯수

    @Builder
    public PadBoxLog(PadBox padBox, Integer usedAmount) {
        this.padBox = padBox;
        this.usedAmount = usedAmount;
    }
}
