package uos.samsam.wing.domain.report;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * ReportTag
 * 신고의 분류를 나타내는 Enum 클래스입니다.
 * 모든 신고는 이 중 하나에 해당합니다.
 */
@Getter
@RequiredArgsConstructor
public enum ReportTag {
    KEY_MISSED("TAG_KEY_MISSED", "생리대함 키 분실"),
    BROKEN("TAG_BROKEN", "생리대함 파손"),
    EMPTY("TAG_EMPTY", "생리대가 하나도 없음"),
    WRONG_QUANTITY("TAG_WRONG_QUANTITY", "수량 오차 발생"),
    DEFECT("TAG_DEFECT", "기타 결함");

    private final String key;
    private final String name;
}
