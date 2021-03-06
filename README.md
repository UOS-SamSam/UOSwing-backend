# 날개를 달다 - API 서버

![image](https://user-images.githubusercontent.com/44166353/135643126-54def7bb-52a7-4f16-a994-ed532de1d85c.png)

2021-1학기 서울시립대학교 컴퓨터과학종합설계 팀 프로젝트 **날개를 달다**의 API 서버입니다.


<br />

## 개요

**날개를 달다**는 교내 운영 중인 `양심생리대함`의 불편점을 해소하고자 시작한 프로젝트입니다. 

주요 제공 기능
- 각 생리대함의 위치 정보 제공
- 각 생리대함의 잔고 정보 제공
- 생리대함 별 신고 기능 제공
- 관리측 공지 게시판 기능 제공
- 생리대함 설치, 철거 시 앱 상에서도 유연하게 추가/삭제 가능


<br />

## 프로젝트 구조

![image](https://user-images.githubusercontent.com/44166353/135641213-b7e2e684-1530-4535-bca0-68dde97be532.png)

본 레포지토리는 API 서버 + DB에 해당됩니다.


<br />

## ERD
![ERD](https://user-images.githubusercontent.com/44166353/135648408-9655bda5-316e-49c4-a187-7277b59068de.png)


<br />

## 기타 세부 사항
- 개발 기간: 2021.03. ~
- 기술스택
  - Spring Boot + JPA
  - MariaDB
  - AWS EC2
