<div align="center">
  <img src="https://raw.githubusercontent.com/LikeLionHGU/Campus_Match_Front/main/src/assets/%EA%B8%B4%EB%A1%9C%EA%B3%A0-SB%201.png" alt="Campus Match Logo" width="300" />
  
  # 🏆 캠퍼스 매치 (Campus Match)
  
  **대학 스포츠 동아리들의 지속 가능한 교류 네트워크 플랫폼**
  <br />
</div>

<br />

## 📖 서비스 소개

**캠퍼스 매치**는 대학 스포츠 동아리들이 타 대학 동아리와 공식적으로 교류하고, 경기 및 교류의 기록을 남겨 지속 가능한 동아리 네트워크를 만드는 웹 플랫폼입니다.

---

## 🎯 기획 배경 및 문제 정의

### 🛑 기존의 문제점 (배경)

기존의 대학 스포츠 동아리 간 교류는 주로 회장단 개인의 인맥이나 에브리타임, 인스타그램 등 파편화된 SNS, 그리고 일회성 이벤트에 크게 의존하고 있었습니다.
이로 인해 다음과 같은 세 가지 주요 한계점이 발생했습니다:

1. **휘발성**: 교류가 끝난 뒤에는 승패 기록이나 활동 내역이 어디에도 남지 않음.
2. **불투명성**: 상대 동아리의 실력이나 '초크(비매너 플레이)' 여부 등 신뢰도를 사전에 파악할 수 없음.
3. **단절성**: 개인 인맥에 의존하다 보니 담당자가 바뀌면 교류 네트워크가 완전히 끊어짐.

### 💡 서비스의 전환 (해커톤 주제: '전환')

캠퍼스 매치는 동아리 단위의 공식 매칭 구조를 제공합니다. 경기 및 교류 결과를 기록하고 서로의 매너를 평가함으로써, 기존의 **'휘발되는 일회성 교류'를 '축적 가능하고 신뢰할 수 있는 지속적인 관계'로 전환**하는 것을 핵심 가치로 삼았습니다.

---

## ✨ 주요 기능 및 특징 소개

- **🔍 동아리 탐색 및 조회**: 종목/지역별 대학 스포츠 동아리 탐색 및 온도(매너 점수), 전적 확인
- **🤝 매칭 시스템**: 타 대학 동아리에 매칭을 제안하고, 받은 제안을 수락/거절하며 일정을 조율
- **📝 매치업 히스토리 (기록 관리)**: 교류 완료 후 승/무/패를 기록하고 누적 교류 데이터를 관리
- **⭐ 온도(매너 점수) 시스템**: 교류 후 상대 동아리의 매너를 상호 평가하여 배지 시스템과 연동하는 신뢰도 구축 체계
- **📸 갤러리 아카이빙**: 교류 활동 사진을 기록하여 동아리의 활발한 활동을 증명

---

## 🌟 기대 효과

1. **공정성 보장**: 개인 인맥에 의존하지 않는 누구나 접근 가능한 공정한 교류 시스템 형성
2. **사전 검증된 신뢰도**: 동아리 간 온도(매너 점수)와 뱃지, 히스토리를 기반으로 신뢰할 수 있는 매칭
3. **지속 가능한 아카이빙**: 단발성 만남의 증발을 막고, 스포츠 동아리의 '가치'가 누적되는 네트워킹 구축

---

## 🛠 기술 스택

### Frontend (협업 환경)

<img src="https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB" /> <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" /> <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" />

### Backend

<img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" /> <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />

### Deployment

<img src="https://img.shields.io/badge/Amazon_AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white" />


# API 명세서

## 목차
- [/api/badge](#apibadge)
- [/api/club](#apiclub)
- [/api/matchPost](#apimatchpost)
- [/api/gallery](#apigallery)
- [/api/matchRequest](#apimatchrequest)
- [/api/schedule](#apischedule)
- [/api/univ](#apiuniv)
- [/api/matchHistory](#apimatchhistory)
- [/api/login](#apilogin)
- [/api/auth](#apiauth)

## API 그룹 요약

| 그룹 | 개수 |
|---|---:|
| `/api/badge` | 1 |
| `/api/club` | 17 |
| `/api/matchPost` | 19 |
| `/api/gallery` | 7 |
| `/api/matchRequest` | 9 |
| `/api/schedule` | 5 |
| `/api/univ` | 1 |
| `/api/matchHistory` | 8 |
| `/api/login` | 1 |
| `/api/auth` | 2 |

## 인증 흐름

### 1) Access Token 발급
- `POST /api/auth`
- 아이디/비밀번호로 Access Token 발급

### 2) Refresh Token 발급
- `POST /api/login`
- 아이디/비밀번호로 Refresh Token 발급

### 3) 로그아웃
- `DELETE /api/auth`
- 현재 로그인 세션 로그아웃


## /api/badge

| Method | Path | 기능 | 사용 페이지 | Service Method | 구현 | 담당자 |
|---|---|---|---|---|---|---|
| `GET` | `/api/badge/{clubID}` | - | 메인 페이지 | - | Back | 학부생 조나은 |

<details>
<summary><code>GET /api/badge/{clubID}</code></summary>

- **사용 페이지**: 메인 페이지
- **구현 여부**: Back
- **담당자**: 학부생 조나은

**Response 예시**

```text
[
{
"id": 1,
"title": "게시글 작성왕",
"imageUrl": "https://server.com/images/badges/writer_gold.png",
"isAcquired": true
},
{
"id": 2,
"title": "첫 승리",
"imageUrl": "https://server.com/images/badges/first_win.png",
"isAcquired": true
}
…
]
```

</details>


## /api/club

| Method | Path | 기능 | 사용 페이지 | Service Method | 구현 | 담당자 |
|---|---|---|---|---|---|---|
| `GET` | `/api/club` | 동아리 목록 | 동아리 검색 페이지 | - | Back | 학부생 안광은 |
| `POST` | `/api/club` | 회원가입 | 회원가입 페이지 | - | Back | 학부생 안광은 |
| `POST` | `/api/club/award` | 수상경력 추가 | 대시보드 페이지 | Award Create | Back | 학부생 안광은 |
| `DELETE` | `/api/club/award/{awardId}` | 수상경력 삭제 | 대시보드 페이지 | Award Delete | Back | 학부생 안광은 |
| `GET` | `/api/club/dashboard/{clubId}` | 동아리 대시보드 | 대시보드 페이지 | Dashboard | Back | 학부생 안광은 |
| `GET` | `/api/club/dashboard/{clubId}` | 동아리 대시보드 | 동아리 검색 페이지 | Dashboard | Back | 학부생 안광은 |
| `PUT` | `/api/club/description` | 나의 동아리 소개 수정 | 대시보드 페이지 | Descrtiption Update | Back | 학부생 안광은 |
| `GET` | `/api/club/description/{clubId}` | 나의 동아리 소개 상세 | 대시보드 페이지 | Description | Back | 학부생 안광은 |
| `POST` | `/api/club/email/confirm` | 이메일 코드 검증하기 | 회원가입 페이지 | - | - | - |
| `POST` | `/api/club/email/request` | 이메일 검증 보내기 | 회원가입 페이지 | - | - | - |
| `GET` | `/api/club/info` | 로그인 유저 정보 가져오기 | - | Info | Back | 학부생 안광은 |
| `GET` | `/api/club/isValidId?username=` | 아이디 중복 확인 | 회원가입 페이지 | - | Back | 학부생 안광은 |
| `POST` | `/api/club/search` | 동아리 찾기 | 히스토리 페이지 | - | - | - |
| `DELETE` | `/api/club/setting` | 동아리 삭제 | 설정 페이지 | - | Back | 학부생 안광은 |
| `GET` | `/api/club/setting/{clubId}` | 내 정보 조회 | 설정 페이지 | setttingDetail | Back | 학부생 조나은 |
| `PUT` | `/api/club/setting/{clubId}` | 내 정보 수정 | 설정 페이지 | - | Back | 학부생 조나은 |
| `POST` | `/api/club/signupValid` | 이메일 인증 회원가입 | 회원가입 페이지 | - | - | - |

<details>
<summary><code>GET /api/club — 동아리 목록</code></summary>

- **사용 페이지**: 동아리 검색 페이지
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
"sportCategoryList" : [
"축구", "미식축구"
],
"regionList" : [
"경북", "서울"
],
"keyword" : "멋사"
```

**Response 예시**

```text
{
"List" : [
{
"clubId" : 1,
"region" : "경북",
"university" : "한동대학교",
"clubName" : "멋사",
"sportCategory" : "축구",
"mannerScore" : 55
},
{
"clubId" : 2,
"region" : "경북",
"university" : "한동대학교",
"clubName" : "멋사",
"sportCategory" : "축구",
"mannerScore" : 55
},
{
"clubId" : 3,
"region" : "경북",
"university" : "한동대학교",
"clubName" : "멋사",
"sportCategory" : "축구",
"mannerScore" : 55
},
{
"clubId" : 4,
"region" : "경북",
"university" : "한동대학교",
"clubName" : "멋사",
"sportCategory" : "축구",
"mannerScore" : 55
},
]
```

</details>

<details>
<summary><code>POST /api/club — 회원가입</code></summary>

- **사용 페이지**: 회원가입 페이지
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{ 
"username" : "test",
"password" : "1111",
"name" : "김축구",
"university" : "한동대학교",
"phone" : "010-0000-0000",
"email" : "test@handong.ac.kr",
"clubName" : "멋사",
"description" "우리 멋쟁이 …",
"region" : "경북",
"sportCategory" : "축구"
}
```

**Response 예시**

```text
{
"clubId" : 1
}
```

</details>

<details>
<summary><code>POST /api/club/award — 수상경력 추가</code></summary>

- **사용 페이지**: 대시보드 페이지
- **Service Method**: `Award Create`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{"title" : "2025 해커톤 수상"}
```

**Response 예시**

```text
{
"awardId" : 1,
"title" : "2025 해커톤 수상" }
```

</details>

<details>
<summary><code>DELETE /api/club/award/{awardId} — 수상경력 삭제</code></summary>

- **사용 페이지**: 대시보드 페이지
- **Service Method**: `Award Delete`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"awardId" : 1
}
```

</details>

<details>
<summary><code>GET /api/club/dashboard/{clubId} — 동아리 대시보드</code></summary>

- **사용 페이지**: 대시보드 페이지
- **Service Method**: `Dashboard`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"clubId" : 1,
"clubName" : "멋사",
"description" : "설명",
"totalMatches" : 10,
"totalWins" : 4,
"totalDraws" : 1,
"totalLosses" : 1,
"mannerScore" : 37.5,
"isMine" : false,
"upcomingResDtoList" : [
    {
     "matchPostId" : 1,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    },
    {
     "matchPostId" : 2,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    }
],
"ongoingResDtoList" :  [
    {
     "matchPostId" : 1,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    },
    {
     "matchPostId" : 2,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    }
],
"matchResDtoList" :  [
    {
     "matchPostId" : 1,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    },
    {
     "matchPostId" : 2,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    }
],
"pastResDtoList" :  [
    {
     "matchPostId" : 1,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    },
    {
     "matchPostId" : 2,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    }
],
"receiveResDtoList" : [
    {
     "matchRequestId" : 1,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    },
    {
     "matchRequestId" : 2,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    }
],
"sendResDtoList" : [
    {
     "matchRequestId" : 1,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    },
    {
     "matchRequestId" : 2,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    }
],
"scheduleResDtoList" : [
    {
     "scheduleId" : 1, 
     "title" : "멋사 MT",
     "startDate" : 2026-02-23,
     "endDate" : 2026-02-23
    },
    {
     "scheduleId" : 1, 
     "title" : "멋사 MT",
     "startDate" : 2026-02-23,
     "endDate" : 2026-02-23
    }
]
"galleryResDtoList" : [
    {
      "galleryId" : 1,
      "title" : "밀란과 한판",
      "matchDate" : 2026-04-02,
      "imageUrl" : "urlurl"
    },
    {
      "galleryId" : 1,
      "title" : "밀란과 한판",
      "matchDate" : 2026-04-02,
      "imageUrl" : "urlurl"
    },
]
}
```

</details>

<details>
<summary><code>GET /api/club/dashboard/{clubId} — 동아리 대시보드</code></summary>

- **사용 페이지**: 동아리 검색 페이지
- **Service Method**: `Dashboard`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"clubId" : 1,
"clubName" : "멋사",
"description" : "설명",
"totalMatches" : 10,
"totalWins" : 4,
"totalDraws" : 1,
"totalLosses" : 1,
"mannerScore" : 37.5,
"isMine" : false,
"upcomingResDtoList" : [
    {
     "matchPostId" : 1,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    },
    {
     "matchPostId" : 2,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    }
],
"ongoingResDtoList" :  [
    {
     "matchPostId" : 1,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    },
    {
     "matchPostId" : 2,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    }
],
"receiveResDtoList" : [
    {
     "matchRequestId" : 1,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    },
    {
     "matchRequestId" : 2,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    }
],
"sendResDtoList" : [
    {
     "matchRequestId" : 1,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    },
    {
     "matchRequestId" : 2,
     "matchDate" : 2026-04-12,
     "university" : "한동대학교",
     "clubName" : "밀란"
    }
],
"scheduleResDtoList" : [
    {
     "scheduleId" : 1, 
     "title" : "멋사 MT",
     "startDate" : 2026-02-23,
     "endDate" : 2026-02-23
    },
    {
     "scheduleId" : 1, 
     "title" : "멋사 MT",
     "startDate" : 2026-02-23,
     "endDate" : 2026-02-23
    }
]
"galleryResDtoList" : [
    {
      "galleryId" : 1,
      "title" : "밀란과 한판",
      "matchDate" : 2026-04-02,
      "imageUrl" : "urlurl"
    },
    {
      "galleryId" : 1,
      "title" : "밀란과 한판",
      "matchDate" : 2026-04-02,
      "imageUrl" : "urlurl"
    },
]
}
```

</details>

<details>
<summary><code>PUT /api/club/description — 나의 동아리 소개 수정</code></summary>

- **사용 페이지**: 대시보드 페이지
- **Service Method**: `Descrtiption Update`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"description" : "소개 수정" 
}
```

**Response 예시**

```text
{
"clubId" : 1
}
```

</details>

<details>
<summary><code>GET /api/club/description/{clubId} — 나의 동아리 소개 상세</code></summary>

- **사용 페이지**: 대시보드 페이지
- **Service Method**: `Description`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"mannerScore" : 55,
"description" : "동아리 소개",
"awardResDtoList" : [
{
"awardId" : 1,
"title" : "2025 포항시 대학연합축제 (우승)"
},
{
"awardId" : 2,
"title" : "2025 포항시 대학연합축제 (우승)"
},
{
"awardId" : 3,
"title" : "2025 포항시 대학연합축제 (우승)"
}
],
"isMine" : true
}
```

</details>

<details>
<summary><code>POST /api/club/email/confirm — 이메일 코드 검증하기</code></summary>

- **사용 페이지**: 회원가입 페이지

**Request 예시**

```text
{
"email" : "test@handong.ac.kr",
"code" : "123456"
}
```

**Response 예시**

```text
{
"verificationToken" : "UUID..."
```

</details>

<details>
<summary><code>POST /api/club/email/request — 이메일 검증 보내기</code></summary>

- **사용 페이지**: 회원가입 페이지

**Request 예시**

```text
{
"email" : "test@handong.ac.kr"
}
```

</details>

<details>
<summary><code>GET /api/club/info — 로그인 유저 정보 가져오기</code></summary>

- **Service Method**: `Info`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"clubId" : 1,
"clubName" : 멋사,
"imageUrl" : "urlurl"
}
```

</details>

<details>
<summary><code>GET /api/club/isValidId?username= — 아이디 중복 확인</code></summary>

- **사용 페이지**: 회원가입 페이지
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"isValidId" : true
}
```

</details>

<details>
<summary><code>POST /api/club/search — 동아리 찾기</code></summary>

- **사용 페이지**: 히스토리 페이지

**Request 예시**

```text
{
"keyword" : "검색"
}
```

**Response 예시**

```text
{
"clubId" : 1,
"clubName" : "멋쟁이 사자처럼",
"university" : "한동대학교"
}
```

</details>

<details>
<summary><code>DELETE /api/club/setting — 동아리 삭제</code></summary>

- **사용 페이지**: 설정 페이지
- **구현 여부**: Back
- **담당자**: 학부생 안광은

</details>

<details>
<summary><code>GET /api/club/setting/{clubId} — 내 정보 조회</code></summary>

- **사용 페이지**: 설정 페이지
- **Service Method**: `setttingDetail`
- **구현 여부**: Back
- **담당자**: 학부생 조나은

**Response 예시**

```text
{
"clubName" : "",
"username" : "",
"password" : "",
"name" : "",
"university" : "",
"phone": "",
"email" : ""
}
```

</details>

<details>
<summary><code>PUT /api/club/setting/{clubId} — 내 정보 수정</code></summary>

- **사용 페이지**: 설정 페이지
- **구현 여부**: Back
- **담당자**: 학부생 조나은

**Request 예시**

```text
{
"name" : "",
"username" : "",
"password" : "",
"university" : "",
"clubName" : "",
"phone" : "",
"email" : ""
}
```

**Response 예시**

```text
{
"clubId" : 1
}
```

</details>

<details>
<summary><code>POST /api/club/signupValid — 이메일 인증 회원가입</code></summary>

- **사용 페이지**: 회원가입 페이지

**Request 예시**

```text
{ 
"username" : "test",
"password" : "1111",
"name" : "김축구",
"university" : "한동대학교",
"phone" : "010-0000-0000",
"email" : "test@handong.ac.kr",
"clubName" : "멋사",
"description" "우리 멋쟁이 …",
"region" : "경북",
"sportCategory" : "축구"
"emailVerificationToken": "UUID..."
}
```

**Response 예시**

```text
{
"clubId" : 1
}
```

</details>


## /api/matchPost

| Method | Path | 기능 | 사용 페이지 | Service Method | 구현 | 담당자 |
|---|---|---|---|---|---|---|
| `POST` | `/api/matchPost` | 매치업 게시글 생성 | 매치업 게시판 페이지 | create | Back | 학부생 안광은 |
| `POST` | `/api/matchPost` | 매치업 등록 | 매치업 게시판 페이지 | Create | Back | 학부생 안광은 |
| `GET` | `/api/matchPost/finish` | 종료된 매치업 조회 | 종료된 메치업 페이지 | FinishLIst | Back | 학부생 안광은 |
| `GET` | `/api/matchPost/finish/{matchPostId}` | 종료된 매치업 상세조회 | 종료된 메치업 페이지 | FinishDetail | Back | 학부생 안광은 |
| `GET` | `/api/matchPost/list` | 매치업 게시글 기본 목록 | 매치업 게시판 페이지 | list | Back | 학부생 안광은 |
| `GET` | `/api/matchPost/mine` | 내가 쓴 매치업 목록 | 매치업 게시판 페이지 | mineList | Back | 학부생 안광은 |
| `GET` | `/api/matchPost/ongoing` | 진행중 매치업 | 진행중 매치업 페이지 | OngoingList | Back | 학부생 안광은 |
| `DELETE` | `/api/matchPost/ongoing/{matchPostId}` | 진행중 매치업 종료 | 진행중 매치업 페이지 | OngoingDelete | Back | 학부생 안광은 |
| `GET` | `/api/matchPost/ongoing/{matchPostId}` | 진행중 매치업 상세조회 | 진행중 매치업 페이지 | OngoingDetail | Back | 학부생 안광은 |
| `GET` | `/api/matchPost/other` | 다른 사람이 쓴 매치업 목록 | 매치업 게시판 페이지 | otherList | Back | 학부생 안광은 |
| `GET` | `/api/matchPost/schedule/detail/{clubId}?matchPostId=` | 매치업 스케줄 상세조회 | 스케줄 페이지 | schedule detail | Back | 학부생 안광은 |
| `GET` | `/api/matchPost/upcoming` | 예정 매치업 | 예정된 매치업 페이지 | UpcomingLIst | Back | 학부생 안광은 |
| `DELETE` | `/api/matchPost/upcoming/{matchPostId}` | 예정 매치업 취소 | 예정된 매치업 페이지 | upcomingDelete | Back | 학부생 안광은 |
| `GET` | `/api/matchPost/upcoming/{matchPostId}` | 예정 매치업 상세조회 | 예정된 매치업 페이지 | UpcomingDetail | Back | 학부생 안광은 |
| `DELETE` | `/api/matchPost/{matchPostId}` | 매치업 게시글 삭제 | 매치업 게시판 페이지 | delete | Back | 학부생 안광은 |
| `DELETE` | `/api/matchPost/{matchPostId}` | 매치업 삭제 | 매치업 게시판 페이지 | Delete | Back | 학부생 안광은 |
| `GET` | `/api/matchPost/{matchPostId}` | 매치업 게시글 상세내용 | 매치업 게시판 페이지 | detail | Back | 학부생 안광은 |
| `PUT` | `/api/matchPost/{matchPostId}` | 매치업 게시글 수정 | 매치업 게시판 페이지 | update | Back | 학부생 안광은 |
| `PUT` | `/api/matchPost/{matchPostId}` | 매치업 수정 | 매치업 게시판 페이지 | Update | Back | 학부생 안광은 |

<details>
<summary><code>POST /api/matchPost — 매치업 게시글 생성</code></summary>

- **사용 페이지**: 매치업 게시판 페이지
- **Service Method**: `create`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"sportCategory" : "축구",
"matchDate" : 2026-01-31,
"location" : "포항체육센터",
"locationDetail" : "상세주소",
"startTime" : 14:00,
"endTime" : 17:00,
"content" : "상세내용입니다."
}
```

**Response 예시**

```text
{
"matchPostId" : 1
}
```

</details>

<details>
<summary><code>POST /api/matchPost — 매치업 등록</code></summary>

- **사용 페이지**: 매치업 게시판 페이지
- **Service Method**: `Create`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"sportCategory" : "미식축구",
"matchDate" : "2026-01-01",
"location" : "매치문화센터",
"startTime" : "11:00",
"endTime" : "17:00",
"content" : "화이팅입니다!"
}
```

**Response 예시**

```text
{
"matchPostId" : 1
}
```

</details>

<details>
<summary><code>GET /api/matchPost/finish — 종료된 매치업 조회</code></summary>

- **사용 페이지**: 종료된 메치업 페이지
- **Service Method**: `FinishLIst`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
List : [
{
"matchPostId" : 1,
"region" : "경북",
"sportCategory" : "미식축구",
"matchDate" : "2026-01-01",
"location" : "매치문화센터",
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "홀램",
"university" : "한동대학교",
"mannerScore" : 50
},
{
{
"matchPostId" : 2,
"region" : "경북",
"sportCategory" : "미식축구",
"matchDate" : "2026-01-01",
"location" : "매치문화센터",
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "홀램",
"university" : "한동대학교",
"mannerScore" : 50
},
]
```

</details>

<details>
<summary><code>GET /api/matchPost/finish/{matchPostId} — 종료된 매치업 상세조회</code></summary>

- **사용 페이지**: 종료된 메치업 페이지
- **Service Method**: `FinishDetail`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"clubId" : 1,
"matchDate" :2026-02-04,
"startTime" : 14:00,
"endTime" : 16:00,
"oppositionClubId" : 2,
"phone" : "010-0000-0000",
"location" : "장소"
"ocationDetail" : "주소 상세"
"content" : "상세내용"
 }
```

</details>

<details>
<summary><code>GET /api/matchPost/list — 매치업 게시글 기본 목록</code></summary>

- **사용 페이지**: 매치업 게시판 페이지
- **Service Method**: `list`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
"sportCategoryList" : [
"축구", "미식축구"
],
"regionList" : [
"경북", "서울"
],
"startDate" : 2026-02-03,
"endDate" : 2026-03-02,
"keyword" : "멋사"
```

**Response 예시**

```text
List [
{
"matchPostId" : 1,
"matchDate" : 2026-01-31,
"sportCategory" : 축구,
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "밀란",
"university" : "한동대학교",
"region" : "경북",
"location" : "포항체육센터",
"mannerScore" : 55
},
{
"matchPostId" : 1,
"matchDate" : 2026-01-31,
"sportCategory" : 축구,
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "밀란",
"university" : "한동대학교",
"region" : "경북",
"location" : "포항체육센터",
"mannerScore" : 55
},
{
"matchPostId" : 1,
"matchDate" : 2026-01-31,
"sportCategory" : 축구,
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "밀란",
"university" : "한동대학교",
"region" : "경북",
"location" : "포항체육센터",
"mannerScore" : 55
}
]
```

</details>

<details>
<summary><code>GET /api/matchPost/mine — 내가 쓴 매치업 목록</code></summary>

- **사용 페이지**: 매치업 게시판 페이지
- **Service Method**: `mineList`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
"sportCategoryList" : [
"축구", "미식축구"
],
"regionList" : [
"경북", "서울"
],
"startDate" : 2026-02-03,
"endDate" : 2026-03-02,
"keyword" : "멋사"
```

**Response 예시**

```text
List [
{
"matchPostId" : 1,
"matchDate" : 2026-01-31,
"sportCategory" : 축구,
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "밀란",
"university" : "한동대학교",
"region" : "경북",
"location" : "포항체육센터",
"mannerScore" : 55
},
{
"matchPostId" : 1,
"matchDate" : 2026-01-31,
"sportCategory" : 축구,
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "밀란",
"university" : "한동대학교",
"region" : "경북",
"location" : "포항체육센터",
"mannerScore" : 55
},
{
"matchPostId" : 1,
"matchDate" : 2026-01-31,
"sportCategory" : 축구,
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "밀란",
"university" : "한동대학교",
"region" : "경북",
"location" : "포항체육센터",
"mannerScore" : 55
}
]
```

</details>

<details>
<summary><code>GET /api/matchPost/ongoing — 진행중 매치업</code></summary>

- **사용 페이지**: 진행중 매치업 페이지
- **Service Method**: `OngoingList`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
List : [
{
"matchPostId" : 1,
"region" : "경북",
"sportCategory" : "미식축구",
"matchDate" : "2026-01-01",
"location" : "매치문화센터",
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "홀램",
"university" : "한동대학교",
"mannerScore" : 50
},
{
{
"matchPostId" : 2,
"region" : "경북",
"sportCategory" : "미식축구",
"matchDate" : "2026-01-01",
"location" : "매치문화센터",
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "홀램",
"university" : "한동대학교",
"mannerScore" : 50
},
]
```

</details>

<details>
<summary><code>DELETE /api/matchPost/ongoing/{matchPostId} — 진행중 매치업 종료</code></summary>

- **사용 페이지**: 진행중 매치업 페이지
- **Service Method**: `OngoingDelete`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"matchPostId" : 1
}
```

</details>

<details>
<summary><code>GET /api/matchPost/ongoing/{matchPostId} — 진행중 매치업 상세조회</code></summary>

- **사용 페이지**: 진행중 매치업 페이지
- **Service Method**: `OngoingDetail`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"startTime" : "14:00",
"endTime" : "17:00",
"location" : "매치문화센터",
"locationDetail" : "상세주소",
"phone" : "010-0000-0000",
"content" : "…"
 }
```

</details>

<details>
<summary><code>GET /api/matchPost/other — 다른 사람이 쓴 매치업 목록</code></summary>

- **사용 페이지**: 매치업 게시판 페이지
- **Service Method**: `otherList`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
"sportCategoryList" : [
"축구", "미식축구"
],
"regionList" : [
"경북", "서울"
],
"startDate" : 2026-02-03,
"endDate" : 2026-03-02,
"keyword" : "멋사"
```

**Response 예시**

```text
List [
{
"matchPostId" : 1,
"matchDate" : 2026-01-31,
"sportCategory" : 축구,
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "밀란",
"university" : "한동대학교",
"region" : "경북",
"location" : "포항체육센터",
"mannerScore" : 55
},
{
"matchPostId" : 1,
"matchDate" : 2026-01-31,
"sportCategory" : 축구,
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "밀란",
"university" : "한동대학교",
"region" : "경북",
"location" : "포항체육센터",
"mannerScore" : 55
},
{
"matchPostId" : 1,
"matchDate" : 2026-01-31,
"sportCategory" : 축구,
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "밀란",
"university" : "한동대학교",
"region" : "경북",
"location" : "포항체육센터",
"mannerScore" : 55
}
]
```

</details>

<details>
<summary><code>GET /api/matchPost/schedule/detail/{clubId}?matchPostId= — 매치업 스케줄 상세조회</code></summary>

- **사용 페이지**: 스케줄 페이지
- **Service Method**: `schedule detail`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"matchPostId" : 2
"sportCategory" : "축구"
"matchDate" : 2026-02-01
"startTime" : 14:00,
"endTime" : 18:00,
"location" : "주소",
"locationDetail" : "상세주소",
"university" : "한동대학교",
"clubName" : "멋사",
"phone" : "010-0000-0000",
"content" : "상세내용", 
"status" : true
}
```

</details>

<details>
<summary><code>GET /api/matchPost/upcoming — 예정 매치업</code></summary>

- **사용 페이지**: 예정된 매치업 페이지
- **Service Method**: `UpcomingLIst`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
List : [
{
"matchPostId" : 1,
"region" : "경북",
"sportCategory" : "미식축구",
"matchDate" : "2026-01-01",
"location" : "매치문화센터",
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "홀램",
"university" : "한동대학교",
"mannerScore" : 50
},
{
{
"matchPostId" : 2,
"region" : "경북",
"sportCategory" : "미식축구",
"matchDate" : "2026-01-01",
"location" : "매치문화센터",
"clubId" : 1,
"imageUrl" : "urlurl",
"clubName" : "홀램",
"university" : "한동대학교",
"mannerScore" : 50
},
]
```

</details>

<details>
<summary><code>DELETE /api/matchPost/upcoming/{matchPostId} — 예정 매치업 취소</code></summary>

- **사용 페이지**: 예정된 매치업 페이지
- **Service Method**: `upcomingDelete`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"content" : "…"
}
```

**Response 예시**

```text
{
"matchPostId" : 1
}
```

</details>

<details>
<summary><code>GET /api/matchPost/upcoming/{matchPostId} — 예정 매치업 상세조회</code></summary>

- **사용 페이지**: 예정된 매치업 페이지
- **Service Method**: `UpcomingDetail`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"startTime" : "14:00",
"endTime" : "17:00",
"location" : "매치문화센터",
"locationDetail" : "상세주소",
"phone" : "010-0000-0000",
"content" : "…"
 }
```

</details>

<details>
<summary><code>DELETE /api/matchPost/{matchPostId} — 매치업 게시글 삭제</code></summary>

- **사용 페이지**: 매치업 게시판 페이지
- **Service Method**: `delete`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

</details>

<details>
<summary><code>DELETE /api/matchPost/{matchPostId} — 매치업 삭제</code></summary>

- **사용 페이지**: 매치업 게시판 페이지
- **Service Method**: `Delete`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"matchPostId" : 1
}
```

</details>

<details>
<summary><code>GET /api/matchPost/{matchPostId} — 매치업 게시글 상세내용</code></summary>

- **사용 페이지**: 매치업 게시판 페이지
- **Service Method**: `detail`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"location" : "포항체육센터",
"locationDetail" : "상세주소",
"startTime" : 14:00,
"endTime" : 17:00,
"phone" : "010-0000-0000"
"content" : "상세내용입니다.",
"isMine" : false
}
```

</details>

<details>
<summary><code>PUT /api/matchPost/{matchPostId} — 매치업 게시글 수정</code></summary>

- **사용 페이지**: 매치업 게시판 페이지
- **Service Method**: `update`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"sportCategory" : "축구",
"matchDate" : 2026-01-31,
"location" : "포항체육센터",
"locationDetail" : "상세주소",
"startTime" : 14:00,
"endTime" : 17:00,
"content" : "상세내용입니다."
}
```

**Response 예시**

```text
{
"matchPostId" : 1
}
```

</details>

<details>
<summary><code>PUT /api/matchPost/{matchPostId} — 매치업 수정</code></summary>

- **사용 페이지**: 매치업 게시판 페이지
- **Service Method**: `Update`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"sportCategory" : "미식축구",
"matchDate" : "2026-01-01",
"location" : "매치문화센터",
"startTime" : "11:00",
"endTime" : "17:00",
"content" : "화이팅입니다!"
}
```

**Response 예시**

```text
{
"matchPostId" : 1
}
```

</details>


## /api/gallery

| Method | Path | 기능 | 사용 페이지 | Service Method | 구현 | 담당자 |
|---|---|---|---|---|---|---|
| `POST` | `/api/gallery` | 갤러리 추가 | 갤러리 페이지 | create | Back | 학부생 안광은 |
| `GET` | `/api/gallery/detail/{galleryId}` | 갤러리 상세보기 | 갤러리 페이지 | - | Back | 학부생 안광은 |
| `POST` | `/api/gallery/match/{clubId}` | 매치업 갤러리 목록 | 갤러리 페이지 | - | Back | 학부생 안광은 |
| `POST` | `/api/gallery/myClub/{clubId}` | 우리들의 갤러리 목록 | 갤러리 페이지 | - | Back | 학부생 안광은 |
| `POST` | `/api/gallery/{clubId}` | 갤러리 기본 목록 | 갤러리 페이지 | - | Back | 학부생 안광은 |
| `DELETE` | `/api/gallery/{galleryId}` | 갤러리 삭제 | 갤러리 페이지 | - | Back | 학부생 안광은 |
| `PUT` | `/api/gallery/{galleryId}` | 갤러리 수정 | 갤러리 페이지 | - | Back | 학부생 안광은 |

<details>
<summary><code>POST /api/gallery — 갤러리 추가</code></summary>

- **사용 페이지**: 갤러리 페이지
- **Service Method**: `create`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
RequestPart
"request" {
"title" : "한마지로와 첫 매치"
"matchDate" : "2026-02-03"
}
 "images" : [
{
imageFile1.jpg,
imageFile2.jpg,
}
```

**Response 예시**

```text
{
"galleryId" : 1
}
```

</details>

<details>
<summary><code>GET /api/gallery/detail/{galleryId} — 갤러리 상세보기</code></summary>

- **사용 페이지**: 갤러리 페이지
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"title" : "멋사 mt",
"matchDate" : 2026-02-03,
"isOfficial" : true,
"isMine" : true,
"imageUrls" : [
"urlurl11111",
"urlurl"22222,
"urlurl3333",
"urlurl4444",
"urlurl55555",
]
}
```

</details>

<details>
<summary><code>POST /api/gallery/match/{clubId} — 매치업 갤러리 목록</code></summary>

- **사용 페이지**: 갤러리 페이지
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"keyword"
}
```

**Response 예시**

```text
List : [
{
"galleryId" : 1,
"title" : "한마지로와의 첫 매칭",
"matchDate" : "2026-02-03",
"imageUrl" : "https://s3.amazonaws.com/example/image1.jpg",
"isOfficial" : true
},
{
"galleryId" : 2,
"title" : "멋사와의 첫 매칭",
"matchDate" : "2026-02-03",
"imageUrl" : "https://s3.amazonaws.com/example/image1.jpg",
"isOfficial" : true
}
]
```

</details>

<details>
<summary><code>POST /api/gallery/myClub/{clubId} — 우리들의 갤러리 목록</code></summary>

- **사용 페이지**: 갤러리 페이지
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"keyword"
}
```

**Response 예시**

```text
List : [
{
"galleryId" : 1,
"title" : "한마지로와의 첫 매칭",
"matchDate" : "2026-02-03",
"imageUrl" : "https://s3.amazonaws.com/example/image1.jpg",
"isOfficial" : false
},
{
"galleryId" : 2,
"title" : "멋사와의 첫 매칭",
"matchDate" : "2026-02-03",
"imageUrl" : "https://s3.amazonaws.com/example/image1.jpg",
"isOfficial" : false
}
]
```

</details>

<details>
<summary><code>POST /api/gallery/{clubId} — 갤러리 기본 목록</code></summary>

- **사용 페이지**: 갤러리 페이지
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"keyword"
}
```

**Response 예시**

```text
List : [
{
"galleryId" : 1,
"title" : "한마지로와의 첫 매칭",
"matchDate" : "2026-02-03",
"imageUrl" : "https://s3.amazonaws.com/example/image1.jpg",
"isOfficial" : true
},
{
"galleryId" : 2,
"title" : "멋사와의 첫 매칭",
"matchDate" : "2026-02-03",
"imageUrl" : "https://s3.amazonaws.com/example/image1.jpg",
"isOfficial" : false
}
]
```

</details>

<details>
<summary><code>DELETE /api/gallery/{galleryId} — 갤러리 삭제</code></summary>

- **사용 페이지**: 갤러리 페이지
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"galleryId" : 1
}
```

</details>

<details>
<summary><code>PUT /api/gallery/{galleryId} — 갤러리 수정</code></summary>

- **사용 페이지**: 갤러리 페이지
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
RequestPart
"request" {
"title" : "한마지로와 첫 매치"
"matchDate" : "2026-02-03"
}
 "images" : [
{
imageFile1.jpg,
imageFile2.jpg,
}
```

**Response 예시**

```text
{
"galleryId" : 1
}
```

</details>


## /api/matchRequest

| Method | Path | 기능 | 사용 페이지 | Service Method | 구현 | 담당자 |
|---|---|---|---|---|---|---|
| `GET` | `/api/matchRequest/receive` | 제안 받은 매치업 | 제안 받은 매치업 | ReceiveList | Back | 학부생 안광은 |
| `DELETE` | `/api/matchRequest/receive/{matchRequestId}` | 제안 받은 매치업 삭제 | 제안 받은 매치업 | ReceiveDelete | Back | 학부생 안광은 |
| `GET` | `/api/matchRequest/receive/{matchRequestId}` | 제안 받은 매치업 상세 조회 | 제안 받은 매치업 | ReceiveDetail | Back | 학부생 안광은 |
| `PUT` | `/api/matchRequest/receive/{matchRequestId}` | 제안 받은 매치업 수락하기 | 제안 받은 매치업 | ReceiveUpdate | Back | 학부생 안광은 |
| `GET` | `/api/matchRequest/send` | 제안한 매치업 | 제안한 매치업 페이지 | SendLIst | Back | 학부생 안광은 |
| `DELETE` | `/api/matchRequest/send/{matchRequestId}` | 제안한 매치업 취소 | 제안한 매치업 페이지 | SendDelete | Back | 학부생 안광은 |
| `GET` | `/api/matchRequest/send/{matchRequestId}` | 제안한 매치업 상세 조회 | 제안한 매치업 페이지 | SendDetail | Back | 학부생 안광은 |
| `POST` | `/api/matchRequest/{matchPostId}` | 매치업 신청 | 매치업 게시판 페이지 | create | Back | 학부생 안광은 |
| `POST` | `/api/matchRequest/{matchPostId}` | 매치업 신청 | 스케줄 페이지 | - | - | - |

<details>
<summary><code>GET /api/matchRequest/receive — 제안 받은 매치업</code></summary>

- **사용 페이지**: 제안 받은 매치업
- **Service Method**: `ReceiveList`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
List : [
{
"matchRequestId" : 1,
"region" : "경북"
"sportCategory" : "축구",
"matchDate" : "2026-02-01",
"location" : 매치문화센터",
"clubId" : 1,
"imageUrl" : 1,
"university" : "한동대학교",
"clubName" : "밀란",
"mannerScore" : 55,
},
{
"matchRequestId" : 2,
"region" : "경북"
"sportCategory" : "축구",
"matchDate" : "2026-02-01",
"location" : 매치문화센터",
"clubId" : 1,
"imageUrl" : 1,
"university" : "한동대학교",
"clubName" : "밀란",
"mannerScore" : 55,
}
]
```

</details>

<details>
<summary><code>DELETE /api/matchRequest/receive/{matchRequestId} — 제안 받은 매치업 삭제</code></summary>

- **사용 페이지**: 제안 받은 매치업
- **Service Method**: `ReceiveDelete`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"content" : "거절사유"
}
```

**Response 예시**

```text
{
"matchRequestId" : 1
}
```

</details>

<details>
<summary><code>GET /api/matchRequest/receive/{matchRequestId} — 제안 받은 매치업 상세 조회</code></summary>

- **사용 페이지**: 제안 받은 매치업
- **Service Method**: `ReceiveDetail`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"startTime" : "14:00",
"endTime" : "17:00",
"location" : "매치문화센터",
"locationDetail" : "상세주소",
"phone" : "010-0000-0000",
"content" : "…"
 }
```

</details>

<details>
<summary><code>PUT /api/matchRequest/receive/{matchRequestId} — 제안 받은 매치업 수락하기</code></summary>

- **사용 페이지**: 제안 받은 매치업
- **Service Method**: `ReceiveUpdate`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"matchRequestId" : 1
}
```

</details>

<details>
<summary><code>GET /api/matchRequest/send — 제안한 매치업</code></summary>

- **사용 페이지**: 제안한 매치업 페이지
- **Service Method**: `SendLIst`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
List : [
{
"matchRequestId" : 1,
"region" : "경북"
"sportCategory" : "축구",
"matchDate" : "2026-02-01",
"location" : 매치문화센터",
"clubId" : 1,
"imageUrl" : 1,
"university" : "한동대학교",
"clubName" : "밀란",
"mannerScore" : 55,
},
{
"matchRequestId" : 2,
"region" : "경북"
"sportCategory" : "축구",
"matchDate" : "2026-02-01",
"location" : 매치문화센터",
"clubId" : 1,
"imageUrl" : 1,
"university" : "한동대학교",
"clubName" : "밀란",
"mannerScore" : 55,
}
]
```

</details>

<details>
<summary><code>DELETE /api/matchRequest/send/{matchRequestId} — 제안한 매치업 취소</code></summary>

- **사용 페이지**: 제안한 매치업 페이지
- **Service Method**: `SendDelete`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"content" : "취소사유"
}
```

**Response 예시**

```text
{
"matchRequestId" :  1
}
```

</details>

<details>
<summary><code>GET /api/matchRequest/send/{matchRequestId} — 제안한 매치업 상세 조회</code></summary>

- **사용 페이지**: 제안한 매치업 페이지
- **Service Method**: `SendDetail`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"startTime" : "14:00",
"endTime" : "17:00",
"location" : "매치문화센터",
"locationDetail" : "상세주소",
"phone" : "010-0000-0000",
"content" : "…"
 }
```

</details>

<details>
<summary><code>POST /api/matchRequest/{matchPostId} — 매치업 신청</code></summary>

- **사용 페이지**: 매치업 게시판 페이지
- **Service Method**: `create`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"startTime" : "15:00",
"endTime" : "17:00"
}
```

**Response 예시**

```text
{
"matchRequestId" : 1
}
```

</details>

<details>
<summary><code>POST /api/matchRequest/{matchPostId} — 매치업 신청</code></summary>

- **사용 페이지**: 스케줄 페이지

**Request 예시**

```text
{
"startTime" : 14:00,
"endTime" : 18:00
}
```

**Response 예시**

```text
{
"matchRequestId" : 2
}
```

</details>


## /api/schedule

| Method | Path | 기능 | 사용 페이지 | Service Method | 구현 | 담당자 |
|---|---|---|---|---|---|---|
| `POST` | `/api/schedule` | 스케줄 생성 | 스케줄 페이지 | list | Back | 학부생 안광은 |
| `GET` | `/api/schedule/detail/{scheduleId}` | 스케줄 상세 조회 | 스케줄 페이지 | detail | Back | 학부생 안광은 |
| `GET` | `/api/schedule/{clubId}` | 스케줄 페이지 조회 | 스케줄 페이지 | list | Back | 학부생 안광은 |
| `DELETE` | `/api/schedule/{scheduleId}` | 스케줄 삭제 | 스케줄 페이지 | delete | Back | 학부생 안광은 |
| `PUT` | `/api/schedule/{scheduleId}` | 스케줄 수정 | 스케줄 페이지 | update | Back | 학부생 안광은 |

<details>
<summary><code>POST /api/schedule — 스케줄 생성</code></summary>

- **사용 페이지**: 스케줄 페이지
- **Service Method**: `list`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"title" : "동아리 MT",
"startDate" : "2026-02-03",
"endDate" : "2026-02-03",
"startTime" : "14:00",
"endTime" : "16:00"
}
```

**Response 예시**

```text
{
"scheduleId" : 1
}
```

</details>

<details>
<summary><code>GET /api/schedule/detail/{scheduleId} — 스케줄 상세 조회</code></summary>

- **사용 페이지**: 스케줄 페이지
- **Service Method**: `detail`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"scheduleId" : 1,
"title" : "멋사mt",
"startDate" : 2026-01-02,
"endDate" : 2026-01-04,
"startTime" : 14:00,
"endTime" : 18:00
}
```

</details>

<details>
<summary><code>GET /api/schedule/{clubId} — 스케줄 페이지 조회</code></summary>

- **사용 페이지**: 스케줄 페이지
- **Service Method**: `list`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"isMine" : true,
"scheduleResDtoList" : [
{
"scheduleId" : 1,
"title" : "멋사mt",
"startDate" : 2026-01-02,
"endDate" : 2026-01-04
},
{
"scheduleId" : 2,
"title" : "멋사mt",
"startDate" : 2026-01-02,
"endDate" : 2026-01-04
}
]
"matchPostResDtoList" : [
{
"matchPostId" : 2,
"matchDate" : 2026-03-09,
"university" : "한동대학교",
"clubName" : "멋사",
"status" : true
},
{
"matchPostId" : 3,
"matchDate" : 2026-03-09,
"university" : "한동대학교",
"clubName" : "멋사",
"status" : false
}
]
```

</details>

<details>
<summary><code>DELETE /api/schedule/{scheduleId} — 스케줄 삭제</code></summary>

- **사용 페이지**: 스케줄 페이지
- **Service Method**: `delete`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Response 예시**

```text
{
"scheduleId" : 1
}
```

</details>

<details>
<summary><code>PUT /api/schedule/{scheduleId} — 스케줄 수정</code></summary>

- **사용 페이지**: 스케줄 페이지
- **Service Method**: `update`
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"title" : "멋사 mt",
"startDate" : 2026-02-01,
"endDate" : 2026-02-05,
"startTime" : 14:00,
"endTime" : 19:00
}
```

**Response 예시**

```text
{
"scheduleId" : 1
}
```

</details>


## /api/univ

| Method | Path | 기능 | 사용 페이지 | Service Method | 구현 | 담당자 |
|---|---|---|---|---|---|---|
| `POST` | `/api/univ` | 학교 찾기 | 회원가입 페이지 | - | - | - |

<details>
<summary><code>POST /api/univ — 학교 찾기</code></summary>

- **사용 페이지**: 회원가입 페이지

**Request 예시**

```text
{
"keyword" : "한동대학교"
}
```

**Response 예시**

```text
{
"univId" : 1
"name" : "한동대학교"
"address" : "handong.ac.kr"
}
```

</details>


## /api/matchHistory

| Method | Path | 기능 | 사용 페이지 | Service Method | 구현 | 담당자 |
|---|---|---|---|---|---|---|
| `POST` | `/api/matchHistory` | 히스토리 생성 | 히스토리 페이지 | - | - | - |
| `POST` | `/api/matchHistory/addList/{clubId}` | 추가된 히스토리 목록 | 히스토리 페이지 | - | - | - |
| `POST` | `/api/matchHistory/createList/{clubId}` | 생성된 히스토리 목록 | 히스토리 페이지 | - | - | - |
| `POST` | `/api/matchHistory/finish/{matchPostId}` | 히스토리 작성 | 종료된 메치업 페이지 | - | Back | 학부생 안광은 |
| `POST` | `/api/matchHistory/{clubId}` | 기본 히스토리 목록 | 히스토리 페이지 | - | - | - |
| `DELETE` | `/api/matchHistory/{matchHistoryId}` | 히스토리 삭제 | 히스토리 페이지 | - | - | - |
| `GET` | `/api/matchHistory/{matchHistoryId}` | 히스토리 수정 조회 | 히스토리 페이지 | - | - | - |
| `PUT` | `/api/matchHistory/{matchHistoryId}` | 히스토리 수정 | 히스토리 페이지 | - | - | - |

<details>
<summary><code>POST /api/matchHistory — 히스토리 생성</code></summary>

- **사용 페이지**: 히스토리 페이지

**Request 예시**

```text
{
"matchDate" : 2026-03-01,
"location" : "매치 장소",
"awayClubId" : 1,
"matchType" : true,
"result" : "승"
}
```

**Response 예시**

```text
{
"matchHistoryId" : 1
}
```

</details>

<details>
<summary><code>POST /api/matchHistory/addList/{clubId} — 추가된 히스토리 목록</code></summary>

- **사용 페이지**: 히스토리 페이지

**Request 예시**

```text
{
"keyword" : "검색"
}
```

**Response 예시**

```text
{
"isMine" : true,
"matchHistoryList" : [
{
"matchHistoryId" : 1,
"matchDate" : 2026-01-03,
"awayClubId" : 2,
"imageUrl" : "urlurl",
"clubName" : "멋사",
"university" : "한동대학교",
"location" : "장소",
"matchType" : true,
"result" : "승"
},
{
"matchHistoryId" : 2,
"matchDate" : 2026-01-03,
"awayClubId" : 2,
"imageUrl" : "urlurl",
"clubName" : "멋사",
"university" : "한동대학교",
"location" : "장소",
"matchType" : false,
"result" : ""
},
{
"matchHistoryId" : 3,
"matchDate" : 2026-01-03,
"awayClubId" : 2,
"imageUrl" : "urlurl",
"clubName" : "멋사",
"university" : "한동대학교",
"location" : "장소",
"matchType" : true,
"result" : "승"
},
]
}
```

</details>

<details>
<summary><code>POST /api/matchHistory/createList/{clubId} — 생성된 히스토리 목록</code></summary>

- **사용 페이지**: 히스토리 페이지

**Request 예시**

```text
{
"keyword" : "검색"
}
```

**Response 예시**

```text
{
"isMine" : true,
"matchHistoryList" : [
{
"matchHistoryId" : 1,
"matchDate" : 2026-01-03,
"awayClubId" : 2,
"imageUrl" : "urlurl",
"clubName" : "멋사",
"university" : "한동대학교",
"location" : "장소",
"matchType" : true,
"result" : "승"
},
{
"matchHistoryId" : 2,
"matchDate" : 2026-01-03,
"awayClubId" : 2,
"imageUrl" : "urlurl",
"clubName" : "멋사",
"university" : "한동대학교",
"location" : "장소",
"matchType" : false,
"result" : ""
},
{
"matchHistoryId" : 3,
"matchDate" : 2026-01-03,
"awayClubId" : 2,
"imageUrl" : "urlurl",
"clubName" : "멋사",
"university" : "한동대학교",
"location" : "장소",
"matchType" : true,
"result" : "승"
},
]
}
```

</details>

<details>
<summary><code>POST /api/matchHistory/finish/{matchPostId} — 히스토리 작성</code></summary>

- **사용 페이지**: 종료된 메치업 페이지
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
RequestPart
"request" {
"title" : "한마지로와 첫 매치"
"matchType" : "true",
"result" : "승",
"mannerScore" : "true",
"rematch" : "true"
}
 "images" : [
{
imageFile1.jpg,
imageFile2.jpg,
}

matchType이 
true면 경기 이고 
false면 교류

mannerScore가 
true면 업,
false면 다운
```

**Response 예시**

```text
{
"matchHistoryId" : 1
}
```

</details>

<details>
<summary><code>POST /api/matchHistory/{clubId} — 기본 히스토리 목록</code></summary>

- **사용 페이지**: 히스토리 페이지

**Request 예시**

```text
{
"keyword" : "검색"
}
```

**Response 예시**

```text
{
"isMine" : true,
"matchHistoryList" : [
{
"matchHistoryId" : 1,
"matchDate" : 2026-01-03,
"awayClubId" : 2,
"imageUrl" : "urlurl",
"clubName" : "멋사",
"university" : "한동대학교",
"location" : "장소",
"matchType" : true,
"result" : "승"
},
{
"matchHistoryId" : 2,
"matchDate" : 2026-01-03,
"awayClubId" : 2,
"imageUrl" : "urlurl",
"clubName" : "멋사",
"university" : "한동대학교",
"location" : "장소",
"matchType" : false,
"result" : ""
},
{
"matchHistoryId" : 3,
"matchDate" : 2026-01-03,
"awayClubId" : 2,
"imageUrl" : "urlurl",
"clubName" : "멋사",
"university" : "한동대학교",
"location" : "장소",
"matchType" : true,
"result" : "승"
},
]
}
```

</details>

<details>
<summary><code>DELETE /api/matchHistory/{matchHistoryId} — 히스토리 삭제</code></summary>

- **사용 페이지**: 히스토리 페이지

**Response 예시**

```text
{
"matchHistoryId" : 1
}
```

</details>

<details>
<summary><code>GET /api/matchHistory/{matchHistoryId} — 히스토리 수정 조회</code></summary>

- **사용 페이지**: 히스토리 페이지

**Response 예시**

```text
{
"matchDate" : 2026-03-1,
"location" : "장소",
"awayClubId" : 1,
"awayClubName" : "멋사",
"matchType" : true,
"result" : "승"
}
```

</details>

<details>
<summary><code>PUT /api/matchHistory/{matchHistoryId} — 히스토리 수정</code></summary>

- **사용 페이지**: 히스토리 페이지

**Request 예시**

```text
{
"matchDate" : 2026-03-01,
"location" : "매치 장소",
"awayClubId" : 1,
"matchType" : true,
"result" : "승"
}
```

**Response 예시**

```text
{
"matchHistoryId" : 1
}
```

</details>


## /api/login

| Method | Path | 기능 | 사용 페이지 | Service Method | 구현 | 담당자 |
|---|---|---|---|---|---|---|
| `POST` | `/api/login` | 로그인 (RefreshToken) | 로그인 페이지 | - | Back | 학부생 안광은 |

<details>
<summary><code>POST /api/login — 로그인 (RefreshToken)</code></summary>

- **사용 페이지**: 로그인 페이지
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"username" : "test",
"password" : "1111"
}
```

**Response 예시**

```text
{
"RefreshToken" : "Bearer …"
}
```

</details>


## /api/auth

| Method | Path | 기능 | 사용 페이지 | Service Method | 구현 | 담당자 |
|---|---|---|---|---|---|---|
| `DELETE` | `/api/auth` | 로그아웃 | 설정 페이지 | - | Back | 학부생 안광은 |
| `POST` | `/api/auth` | 로그인 (AccessToken) | 로그인 페이지 | - | Back | 학부생 안광은 |

<details>
<summary><code>DELETE /api/auth — 로그아웃</code></summary>

- **사용 페이지**: 설정 페이지
- **구현 여부**: Back
- **담당자**: 학부생 안광은

</details>

<details>
<summary><code>POST /api/auth — 로그인 (AccessToken)</code></summary>

- **사용 페이지**: 로그인 페이지
- **구현 여부**: Back
- **담당자**: 학부생 안광은

**Request 예시**

```text
{
"username" : "test",
"password" : "1111"
}
```

**Response 예시**

```text
{
"Authorization : "Bearer …"
}
```

</details>

