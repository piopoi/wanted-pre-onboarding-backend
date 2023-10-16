# 프리온보딩 백엔드 인턴십 2023년 10월 선발과제

## 1. 요구사항

### 서비스 개요
본 서비스는 기업의 채용을 위한 웹 서비스입니다.  
회사는 채용공고를 생성하고, 이에 사용자는 지원합니다.

### 상세 요구사항
- 채용공고를 등록할 수 있다.
    ```
    Example)
    # 데이터 예시이며, 필드명은 임의로 설정가능합니다.
    {
      "회사_id":회사_id,
      "채용포지션":"백엔드 주니어 개발자",
      "채용보상금":1000000,
      "채용내용":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
      "사용기술":"Python"
    }
    ```
  
- 채용공고를 수정할 수 있다.
    - 회사 id 이외 모두 수정 가능하다.
    ``` 
    Example)
    # 데이터 예시이며, 필드명은 임의로 설정가능합니다.
    {
    "채용포지션":"백엔드 주니어 개발자",
    "채용보상금":1500000, # 변경됨
    "채용내용":"원티드랩에서 백엔드 주니어 개발자를 '적극' 채용합니다. 자격요건은..", # 변경됨
    "사용기술":"Python"
    }
    
    or
    
    {
    "채용포지션":"백엔드 주니어 개발자",
    "채용보상금":1000000,
    "채용내용":"원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
    "사용기술":"Django" # 변경됨
    }
    ```
  
- 채용공고를 삭제할 수 있다.  

- 채용공고 목록을 조회할 수 있다.
    ```
    Example)
    [
        {
          "채용공고_id": 채용공고_id,
          "회사명":"원티드랩",
          "국가":"한국",
          "지역":"서울",
          "채용포지션":"백엔드 주니어 개발자",
          "채용보상금":1500000,
          "사용기술":"Python"
        },
        {
          "채용공고_id": 채용공고_id,
          "회사명":"네이버",
          "국가":"한국",
          "지역":"판교",
          "채용포지션":"Django 백엔드 개발자",
          "채용보상금":1000000,
          "사용기술":"Django"
        },
      ...
    ]
    ```

- 채용공고를 검색하여 목록을 조회할 수 있다. (선택사항)
    ```
    # Example - 1) some/url?search=원티드
    [
        {
          "채용공고_id": 채용공고_id,
          "회사명":"원티드랩",
          "국가":"한국",
          "지역":"서울",
          "채용포지션":"백엔드 주니어 개발자",
          "채용보상금":1500000,
          "사용기술":"Python"
        },
        {
          "채용공고_id": 채용공고_id,
          "회사명":"원티드코리아",
          "국가":"한국",
          "지역":"부산",
          "채용포지션":"프론트엔드 개발자",
          "채용보상금":500000,
          "사용기술":"javascript"
        }
    ]
    
    # Example - 2) some/url?search=Django
    [
        {
          "채용공고_id": 채용공고_id,
          "회사명":"네이버",
          "국가":"한국",
          "지역":"판교",
          "채용포지션":"Django 백엔드 개발자",
          "채용보상금":1000000,
          "사용기술":"Django"
        },
        {
            "채용공고_id": 채용공고_id,
          "회사명":"카카오",
          "국가":"한국",
          "지역":"판교",
          "채용포지션":"Django 백엔드 개발자",
          "채용보상금":500000,
          "사용기술":"Python"
        }
      ...
    ]
    ```

- 채용공고 1건을 상세조회할 수 있다.
    - 채용내용이 포함되어야 한다.
    - 해당 회사가 올린 다른 채용공고 리스트가 포함되어야 한다. (선택사항)
    ```
    Example)
    {
      "채용공고_id": 채용공고_id,
      "회사명":"원티드랩",
      "국가":"한국",
      "지역":"서울",
      "채용포지션":"백엔드 주니어 개발자",
      "채용보상금":1500000,
      "사용기술":"Python",
        "채용내용": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
        "회사가올린다른채용공고":[채용공고_id, 채용공고_id, ..] # id List (선택사항 및 가산점요소).
    }
    ```

- 사용자가 채용공고에 지원할 수 있다. (선택사항)
    - 사용자 1명 당 채용공고 1건에만 지원 가능하다.
    ```
    Example)
    {
      "채용공고_id": 채용공고_id,
      "사용자_id": 사용자_id
    }
    ```

## 필수 기술요건
- ORM
- RDBMS: MySQL 8.1.0

## 참고사항
- Unit Test 구현
- 요청/응답 예시 관련
  - 요구사항(의도)를 만족시킨다면 다른 형태의 요청/응답을 사용해도 된다.
  - 제공된 필드명은 임의로 변경 가능하다.
- 필요한 모델
  - 회사
  - 사용자
  - 채용공고
  - 지원내역 (선택사항)
  - DB에 임의로 생성하여 진행한다.
- 제외할 내용
  - Frontend 요소
  - 회사/사용자 등록 절차
  - 사용자 인증절차: 로그인, 토큰 등

# 2. 구현

## 구현 API
- [x] 채용공고를 등록할 수 있다.
- [ ] 채용공고를 수정할 수 있다.
- [ ] 채용공고를 삭제할 수 있다.
- [ ] 채용공고 목록을 조회할 수 있다.
- [ ] 채용공고를 검색하여 목록을 조회할 수 있다. (선택사항)
- [ ] 채용공고 1건을 상세조회할 수 있다.
  - [ ] 채용내용이 포함되어야 한다.
  - [ ] 해당 회사가 올린 다른 채용공고 리스트가 포함되어야 한다. (선택사항)
- [ ] 사용자가 채용공고에 지원할 수 있다. (선택사항)
  - [ ] 사용자 1명 당 채용공고 1건에만 지원 가능하다.

## API 명세
- file : [src/main/resources/static/docs/index.html](src/main/resources/static/docs/index.html)
- app 실행 후 url: [http://localhost:8080/docs/index.html](http://localhost:8080/docs/index.html)

## 사용기술
- Spring Boot 3.1.4
- MySQL 8.1.0
- Spring REST Docs 3.0.0

## 데이터베이스 테이블 구조
