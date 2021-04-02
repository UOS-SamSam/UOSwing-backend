package uos.samsam.wing.domain.padbox;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import uos.samsam.wing.domain.report.Report;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class PadBox {

    @Id @Column(name = "BOX_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double latitude;    // 위도

    @Column(nullable = false)
    private Double longitude;   // 경도

    @Column(nullable = false)
    private String address;     // 주소

    @Column(nullable = false)
    private String name;        // 이름

    @Column(nullable = false)
    private Integer padAmount;      // 남은 수량

    private Double temperature; // 온도

    private Double humidity;    // 습도

    private LocalDateTime updatedStateDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "padBox", cascade = CascadeType.ALL)
    private List<Report> reportList = new ArrayList<>();

    @Builder
    public PadBox(Double latitude, Double longitude, String address, String name, int padAmount, Double temperature, Double humidity) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.name = name;
        this.padAmount = padAmount;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public void update(Double latitude, Double longitude, String address, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.name = name;
        this.updatedStateDate = LocalDateTime.now();
    }

    public Integer updateState(Integer padAmount, Double temperature, Double humidity) {
        Integer diff = padAmount - this.padAmount;
        this.padAmount = padAmount;
        this.temperature = temperature;
        this.humidity = humidity;
        this.updatedStateDate = LocalDateTime.now();
        return diff;
    }
}
