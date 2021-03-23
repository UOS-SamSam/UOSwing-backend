package uos.samsam.wing.domain.report;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReportTag {
    MISSED_KEY("TAG_KEY", "키 분실"),
    BROKEN("TAG_BROKEN", "파손");

    private final String key;
    private final String name;
}
