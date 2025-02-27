# 헤이 나의 중고차

## 기능 목록
1. 이벤트 응모 기능
   * 동시성 이슈 (redis + kafka)
2. 커뮤니티 기능 
3. 중고차 판매
4. 자동차 용품 판매
   * 커머스 기능
   * 재고 및 차감 : 동시성 이슈
   * 배치 활용


## 학습 목표
* JPA, QueryDSL, Spring Batch, Kafka 학습
* 의미있는 단위, 통홥, 인수 테스트 작성
* TDD로 개발
* 대용량 트랙픽 상황으로 가정하여 아키텍쳐 설계  
* 무중단 배포
* 멀티 모듈
* MR 및 commit 작성하기 (master 바로 merge 금지!) 
* 문서 작성하기


## 구현 목표
* caffeine cache + redis cache로  하이브리드 캐시 구현하고 성능 테스트
* redis lock (스핀락), redisson (pub-sub), redlock 성능 비교
* kafka 메세지 발행 실패시 복구 기능 구현
* kafka transaction 설정
