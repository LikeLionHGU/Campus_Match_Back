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


분류,엔드포인트,Method,사용 페이지,기능,Request,Response,Service Method,구현여부,담당자
/api/badge,/{clubID} (1),GET,메인 페이지,,,"[
{
""id"": 1,
""title"": ""게시글 작성왕"",
""imageUrl"": ""https://server.com/images/badges/writer_gold.png"",
""isAcquired"": true
},
{
""id"": 2,
""title"": ""첫 승리"",
""imageUrl"": ""https://server.com/images/badges/first_win.png"",
""isAcquired"": true
}
…
]",,Back,학부생 조나은
/api/club,/info,GET,,로그인 유저 정보 가져오기,,"{
”clubId” : 1,
”clubName” : 멋사,
”imageUrl” : “urlurl”
}",Info,Back,학부생 안광은
/api/club,,POST,회원가입 페이지,회원가입,"{ 
”username” : “test”,
”password” : “1111”,
”name” : “김축구”,
”university” : “한동대학교”,
”phone” : “010-0000-0000”,
”email” : “test@handong.ac.kr”,
”clubName” : “멋사”,
”description” “우리 멋쟁이 …”,
”region” : “경북”,
”sportCategory” : “축구”
}","{
”clubId” : 1
}",,Back,학부생 안광은
/api/club,/dashboard/{clubId},GET,대시보드 페이지,동아리 대시보드,,"{
”clubId” : 1,
”clubName” : “멋사”,
”description” : “설명”,
”totalMatches” : 10,
”totalWins” : 4,
”totalDraws” : 1,
”totalLosses” : 1,
”mannerScore” : 37.5,
“isMine” : false,
”upcomingResDtoList” : [
    {
     ”matchPostId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchPostId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”ongoingResDtoList” :  [
    {
     ”matchPostId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchPostId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”matchResDtoList” :  [
    {
     ”matchPostId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchPostId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”pastResDtoList” :  [
    {
     ”matchPostId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchPostId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”receiveResDtoList” : [
    {
     ”matchRequestId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchRequestId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”sendResDtoList” : [
    {
     ”matchRequestId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchRequestId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”scheduleResDtoList” : [
    {
     “scheduleId” : 1, 
     ”title” : “멋사 MT”,
     “startDate” : 2026-02-23,
     “endDate” : 2026-02-23
    },
    {
     “scheduleId” : 1, 
     ”title” : “멋사 MT”,
     “startDate” : 2026-02-23,
     “endDate” : 2026-02-23
    }
]
”galleryResDtoList” : [
    {
      “galleryId” : 1,
      “title” : “밀란과 한판”,
      “matchDate” : 2026-04-02,
      “imageUrl” : “urlurl”
    },
    {
      “galleryId” : 1,
      “title” : “밀란과 한판”,
      “matchDate” : 2026-04-02,
      “imageUrl” : “urlurl”
    },
]
}",Dashboard,Back,학부생 안광은
/api/club,/dashboard/{clubId},GET,동아리 검색 페이지,동아리 대시보드,,"{
”clubId” : 1,
”clubName” : “멋사”,
”description” : “설명”,
”totalMatches” : 10,
”totalWins” : 4,
”totalDraws” : 1,
”totalLosses” : 1,
”mannerScore” : 37.5,
“isMine” : false,
”upcomingResDtoList” : [
    {
     ”matchPostId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchPostId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”ongoingResDtoList” :  [
    {
     ”matchPostId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchPostId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”receiveResDtoList” : [
    {
     ”matchRequestId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchRequestId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”sendResDtoList” : [
    {
     ”matchRequestId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchRequestId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”scheduleResDtoList” : [
    {
     “scheduleId” : 1, 
     ”title” : “멋사 MT”,
     “startDate” : 2026-02-23,
     “endDate” : 2026-02-23
    },
    {
     “scheduleId” : 1, 
     ”title” : “멋사 MT”,
     “startDate” : 2026-02-23,
     “endDate” : 2026-02-23
    }
]
”galleryResDtoList” : [
    {
      “galleryId” : 1,
      “title” : “밀란과 한판”,
      “matchDate” : 2026-04-02,
      “imageUrl” : “urlurl”
    },
    {
      “galleryId” : 1,
      “title” : “밀란과 한판”,
      “matchDate” : 2026-04-02,
      “imageUrl” : “urlurl”
    },
]
}",Dashboard,Back,학부생 안광은
/api/matchPost,,POST,매치업 게시판 페이지,매치업 게시글 생성,"{
”sportCategory” : “축구”,
”matchDate” : 2026-01-31,
”location” : “포항체육센터”,
”locationDetail” : “상세주소”,
”startTime” : 14:00,
”endTime” : 17:00,
”content” : “상세내용입니다.”
}","{
”matchPostId” : 1
}",create,Back,학부생 안광은
/api/gallery,/{galleryId},DELETE,갤러리 페이지,갤러리 삭제,,"{
”galleryId” : 1
}",,Back,학부생 안광은
/api/gallery,"

/{galleryId}",PUT,갤러리 페이지,갤러리 수정,"RequestPart
”request” {
”title” : “한마지로와 첫 매치”
”matchDate” : “2026-02-03”
}
 “images” : [
{
imageFile1.jpg,
imageFile2.jpg,
}","{
”galleryId” : 1
}",,Back,학부생 안광은
/api/gallery,"

/detail/{galleryId}",GET,갤러리 페이지,갤러리 상세보기,,"{
”title” : “멋사 mt”,
”matchDate” : 2026-02-03,
”isOfficial” : true,
”isMine” : true,
”imageUrls” : [
”urlurl11111”,
”urlurl”22222,
”urlurl3333”,
”urlurl4444”,
”urlurl55555”,
]
}",,Back,학부생 안광은
/api/gallery,/myClub/{clubId},POST,갤러리 페이지,우리들의 갤러리 목록,"{
”keyword”
}","List : [
{
”galleryId” : 1,
”title” : “한마지로와의 첫 매칭”,
”matchDate” : “2026-02-03”,
”imageUrl” : “https://s3.amazonaws.com/example/image1.jpg”,
”isOfficial” : false
},
{
”galleryId” : 2,
”title” : “멋사와의 첫 매칭”,
”matchDate” : “2026-02-03”,
”imageUrl” : “https://s3.amazonaws.com/example/image1.jpg”,
”isOfficial” : false
}
]",,Back,학부생 안광은
/api/gallery,/match/{clubId},POST,갤러리 페이지,매치업 갤러리 목록,"{
”keyword”
}","List : [
{
”galleryId” : 1,
”title” : “한마지로와의 첫 매칭”,
”matchDate” : “2026-02-03”,
”imageUrl” : “https://s3.amazonaws.com/example/image1.jpg”,
”isOfficial” : true
},
{
”galleryId” : 2,
”title” : “멋사와의 첫 매칭”,
”matchDate” : “2026-02-03”,
”imageUrl” : “https://s3.amazonaws.com/example/image1.jpg”,
”isOfficial” : true
}
]",,Back,학부생 안광은
/api/gallery,/{clubId},POST,갤러리 페이지,갤러리 기본 목록,"{
”keyword”
}","List : [
{
”galleryId” : 1,
”title” : “한마지로와의 첫 매칭”,
”matchDate” : “2026-02-03”,
”imageUrl” : “https://s3.amazonaws.com/example/image1.jpg”,
”isOfficial” : true
},
{
”galleryId” : 2,
”title” : “멋사와의 첫 매칭”,
”matchDate” : “2026-02-03”,
”imageUrl” : “https://s3.amazonaws.com/example/image1.jpg”,
”isOfficial” : false
}
]",,Back,학부생 안광은
/api/gallery,,POST,갤러리 페이지,갤러리 추가,"RequestPart
”request” {
”title” : “한마지로와 첫 매치”
”matchDate” : “2026-02-03”
}
 “images” : [
{
imageFile1.jpg,
imageFile2.jpg,
}","{
”galleryId” : 1
}",create,Back,학부생 안광은
/api/matchRequest,/{matchPostId},POST,스케줄 페이지,매치업 신청,"{
”startTime” : 14:00,
""endTime” : 18:00
}","{
”matchRequestId” : 2
}",,,
/api/matchPost,/schedule/detail/{clubId}?matchPostId=,GET,스케줄 페이지,매치업 스케줄 상세조회,,"{
”matchPostId” : 2
”sportCategory” : “축구”
”matchDate” : 2026-02-01
”startTime” : 14:00,
”endTime” : 18:00,
”location” : “주소”,
“locationDetail” : “상세주소”,
”university” : “한동대학교”,
”clubName” : “멋사”,
”phone” : “010-0000-0000”,
”content” : “상세내용”, 
”status” : true
}",schedule detail,Back,학부생 안광은
/api/schedule,/{scheduleId},DELETE,스케줄 페이지,스케줄 삭제,,"{
”scheduleId” : 1
}",delete,Back,학부생 안광은
/api/schedule,/{scheduleId},PUT,스케줄 페이지,스케줄 수정,"{
”title” : “멋사 mt”,
”startDate” : 2026-02-01,
”endDate” : 2026-02-05,
”startTime” : 14:00,
”endTime” : 19:00
}","{
”scheduleId” : 1
}",update,Back,학부생 안광은
/api/schedule,/detail/{scheduleId},GET,스케줄 페이지,스케줄 상세 조회,,"{
”scheduleId” : 1,
”title” : “멋사mt”,
”startDate” : 2026-01-02,
”endDate” : 2026-01-04,
”startTime” : 14:00,
”endTime” : 18:00
}",detail,Back,학부생 안광은
/api/schedule,/{clubId},GET,스케줄 페이지,스케줄 페이지 조회,,"{
”isMine” : true,
”scheduleResDtoList” : [
{
”scheduleId” : 1,
”title” : “멋사mt”,
”startDate” : 2026-01-02,
”endDate” : 2026-01-04
},
{
”scheduleId” : 2,
”title” : “멋사mt”,
”startDate” : 2026-01-02,
”endDate” : 2026-01-04
}
]
”matchPostResDtoList” : [
{
”matchPostId” : 2,
”matchDate” : 2026-03-09,
”university” : “한동대학교”,
”clubName” : “멋사”,
”status” : true
},
{
”matchPostId” : 3,
”matchDate” : 2026-03-09,
”university” : “한동대학교”,
”clubName” : “멋사”,
”status” : false
}
]",list,Back,학부생 안광은
/api/club,,GET,동아리 검색 페이지,동아리 목록,"“sportCategoryList” : [
”축구”, “미식축구”
],
”regionList” : [
”경북”, “서울”
],
”keyword” : “멋사”","{
”List” : [
{
”clubId” : 1,
”region” : “경북”,
”university” : “한동대학교”,
”clubName” : “멋사”,
”sportCategory” : “축구”,
”mannerScore” : 55
},
{
”clubId” : 2,
”region” : “경북”,
”university” : “한동대학교”,
”clubName” : “멋사”,
”sportCategory” : “축구”,
”mannerScore” : 55
},
{
”clubId” : 3,
”region” : “경북”,
”university” : “한동대학교”,
”clubName” : “멋사”,
”sportCategory” : “축구”,
”mannerScore” : 55
},
{
”clubId” : 4,
”region” : “경북”,
”university” : “한동대학교”,
”clubName” : “멋사”,
”sportCategory” : “축구”,
”mannerScore” : 55
},
]",,Back,학부생 안광은
/api/club,/award/{awardId},DELETE,대시보드 페이지,수상경력 삭제,,"{
”awardId” : 1
}",Award Delete,Back,학부생 안광은
/api/club,/award,POST,대시보드 페이지,수상경력 추가,{”title” : “2025 해커톤 수상”},"{
”awardId” : 1,
”title” : “2025 해커톤 수상” }",Award Create,Back,학부생 안광은
/api/club,/description,PUT,대시보드 페이지,나의 동아리 소개 수정,"{
”description” : “소개 수정” 
}","{
”clubId” : 1
}",Descrtiption Update,Back,학부생 안광은
/api/club,/description/{clubId},GET,대시보드 페이지,나의 동아리 소개 상세,,"{
”mannerScore” : 55,
”description” : “동아리 소개”,
”awardResDtoList” : [
{
”awardId” : 1,
”title” : “2025 포항시 대학연합축제 (우승)”
},
{
”awardId” : 2,
”title” : “2025 포항시 대학연합축제 (우승)”
},
{
”awardId” : 3,
”title” : “2025 포항시 대학연합축제 (우승)”
}
],
”isMine” : true
}",Description,Back,학부생 안광은
/api/matchRequest,/{matchPostId},POST,매치업 게시판 페이지,매치업 신청,"{
”startTime” : “15:00”,
”endTime” : “17:00”
}","{
”matchRequestId” : 1
}",create,Back,학부생 안광은
/api/matchPost,,POST,매치업 게시판 페이지,매치업 등록,"{
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”startTime” : “11:00”,
”endTime” : “17:00”,
”content” : “화이팅입니다!”
}","{
”matchPostId” : 1
}",Create,Back,학부생 안광은
/api/matchPost,/{matchPostId},DELETE,매치업 게시판 페이지,매치업 게시글 삭제,,,delete,Back,학부생 안광은
/api/matchPost,/{matchPostId},PUT,매치업 게시판 페이지,매치업 게시글 수정,"{
”sportCategory” : “축구”,
”matchDate” : 2026-01-31,
”location” : “포항체육센터”,
”locationDetail” : “상세주소”,
”startTime” : 14:00,
”endTime” : 17:00,
”content” : “상세내용입니다.”
}","{
”matchPostId” : 1
}",update,Back,학부생 안광은
/api/matchPost,/other,GET,매치업 게시판 페이지,다른 사람이 쓴 매치업 목록,"“sportCategoryList” : [
”축구”, “미식축구”
],
”regionList” : [
”경북”, “서울”
],
”startDate” : 2026-02-03,
”endDate” : 2026-03-02,
”keyword” : “멋사”","List [
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
},
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
},
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
}
]",otherList,Back,학부생 안광은
/api/matchPost,/mine,GET,매치업 게시판 페이지,내가 쓴 매치업 목록,"“sportCategoryList” : [
”축구”, “미식축구”
],
”regionList” : [
”경북”, “서울”
],
”startDate” : 2026-02-03,
”endDate” : 2026-03-02,
”keyword” : “멋사”","List [
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
},
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
},
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
}
]",mineList,Back,학부생 안광은
/api/matchPost,/{matchPostId},GET,매치업 게시판 페이지,매치업 게시글 상세내용,,"{
”location” : “포항체육센터”,
”locationDetail” : “상세주소”,
”startTime” : 14:00,
”endTime” : 17:00,
”phone” : “010-0000-0000”
”content” : “상세내용입니다.”,
”isMine” : false
}",detail,Back,학부생 안광은
/api/matchPost,/list,GET,매치업 게시판 페이지,매치업 게시글 기본 목록,"“sportCategoryList” : [
”축구”, “미식축구”
],
”regionList” : [
”경북”, “서울”
],
”startDate” : 2026-02-03,
”endDate” : 2026-03-02,
”keyword” : “멋사”","List [
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
},
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
},
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
}
]",list,Back,학부생 안광은
/api/matchPost,/{matchPostId} (1),PUT,매치업 게시판 페이지,매치업 수정,"{
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”startTime” : “11:00”,
”endTime” : “17:00”,
”content” : “화이팅입니다!”
}","{
”matchPostId” : 1
}",Update,Back,학부생 안광은
/api/matchPost,/{matchPostId} (1),DELETE,매치업 게시판 페이지,매치업 삭제,,"{
”matchPostId” : 1
}",Delete,Back,학부생 안광은
/api/matchRequest,/receive,GET,제안 받은 매치업,제안 받은 매치업,,"List : [
{
”matchRequestId” : 1,
”region” : “경북”
”sportCategory” : “축구”,
”matchDate” : “2026-02-01”,
”location” : 매치문화센터”,
”clubId” : 1,
”imageUrl” : 1,
”university” : “한동대학교”,
”clubName” : “밀란”,
”mannerScore” : 55,
},
{
”matchRequestId” : 2,
”region” : “경북”
”sportCategory” : “축구”,
”matchDate” : “2026-02-01”,
”location” : 매치문화센터”,
”clubId” : 1,
”imageUrl” : 1,
”university” : “한동대학교”,
”clubName” : “밀란”,
”mannerScore” : 55,
}
]",ReceiveList,Back,학부생 안광은
/api/matchRequest,/receive/{matchRequestId},GET,제안 받은 매치업,제안 받은 매치업 상세 조회,,"{
”startTime” : “14:00”,
”endTime” : “17:00”,
”location” : “매치문화센터”,
”locationDetail” : “상세주소”,
”phone” : “010-0000-0000”,
”content” : “…”
 }",ReceiveDetail,Back,학부생 안광은
/api/matchRequest,/receive/{matchRequestId},DELETE,제안 받은 매치업,제안 받은 매치업 삭제,"{
”content” : “거절사유”
}","{
”matchRequestId” : 1
}",ReceiveDelete,Back,학부생 안광은
/api/matchRequest,/receive/{matchRequestId},PUT,제안 받은 매치업,제안 받은 매치업 수락하기,,"{
”matchRequestId” : 1
}",ReceiveUpdate,Back,학부생 안광은
/api/matchRequest,/send,GET,제안한 매치업 페이지,제안한 매치업,,"List : [
{
”matchRequestId” : 1,
”region” : “경북”
”sportCategory” : “축구”,
”matchDate” : “2026-02-01”,
”location” : 매치문화센터”,
”clubId” : 1,
”imageUrl” : 1,
”university” : “한동대학교”,
”clubName” : “밀란”,
”mannerScore” : 55,
},
{
”matchRequestId” : 2,
”region” : “경북”
”sportCategory” : “축구”,
”matchDate” : “2026-02-01”,
”location” : 매치문화센터”,
”clubId” : 1,
”imageUrl” : 1,
”university” : “한동대학교”,
”clubName” : “밀란”,
”mannerScore” : 55,
}
]",SendLIst,Back,학부생 안광은
/api/matchRequest,/send/{matchRequestId},GET,제안한 매치업 페이지,제안한 매치업 상세 조회,,"{
”startTime” : “14:00”,
”endTime” : “17:00”,
”location” : “매치문화센터”,
”locationDetail” : “상세주소”,
”phone” : “010-0000-0000”,
”content” : “…”
 }",SendDetail,Back,학부생 안광은
/api/matchRequest,/send/{matchRequestId},DELETE,제안한 매치업 페이지,제안한 매치업 취소,"{
”content” : “취소사유”
}","{
”matchRequestId” :  1
}",SendDelete,Back,학부생 안광은
/api/matchPost,/upcoming,GET,예정된 매치업 페이지,예정 매치업,,"List : [
{
”matchPostId” : 1,
”region” : “경북”,
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “홀램”,
”university” : “한동대학교”,
”mannerScore” : 50
},
{
{
”matchPostId” : 2,
”region” : “경북”,
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “홀램”,
”university” : “한동대학교”,
”mannerScore” : 50
},
]",UpcomingLIst,Back,학부생 안광은
/api/matchPost,/upcoming/{matchPostId},GET,예정된 매치업 페이지,예정 매치업 상세조회,,"{
”startTime” : “14:00”,
”endTime” : “17:00”,
”location” : “매치문화센터”,
”locationDetail” : “상세주소”,
”phone” : “010-0000-0000”,
”content” : “…”
 }",UpcomingDetail,Back,학부생 안광은
/api/matchPost,/upcoming/{matchPostId},DELETE,예정된 매치업 페이지,예정 매치업 취소,"{
”content” : “…”
}","{
”matchPostId” : 1
}",upcomingDelete,Back,학부생 안광은
/api/matchPost,/ongoing,GET,진행중 매치업 페이지,진행중 매치업,,"List : [
{
”matchPostId” : 1,
”region” : “경북”,
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “홀램”,
”university” : “한동대학교”,
”mannerScore” : 50
},
{
{
”matchPostId” : 2,
”region” : “경북”,
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “홀램”,
”university” : “한동대학교”,
”mannerScore” : 50
},
]",OngoingList,Back,학부생 안광은
/api/matchPost,/ongoing/{matchPostId},GET,진행중 매치업 페이지,진행중 매치업 상세조회,,"{
”startTime” : “14:00”,
”endTime” : “17:00”,
”location” : “매치문화센터”,
”locationDetail” : “상세주소”,
”phone” : “010-0000-0000”,
”content” : “…”
 }",OngoingDetail,Back,학부생 안광은
/api/matchPost,/ongoing/{matchPostId},DELETE,진행중 매치업 페이지,진행중 매치업 종료,,"{
”matchPostId” : 1
}",OngoingDelete,Back,학부생 안광은
/api/univ,,POST,회원가입 페이지,학교 찾기,"{
”keyword” : “한동대학교”
}","{
”univId” : 1
”name” : “한동대학교”
”address” : “handong.ac.kr”
}",,,
/api/club,/email/confirm,POST,회원가입 페이지,이메일 코드 검증하기,"{
”email” : “test@handong.ac.kr”,
”code” : “123456”
}","{
“verificationToken"" : ""UUID...”",,,
/api/club,/email/request,POST,회원가입 페이지,이메일 검증 보내기,"{
”email” : “test@handong.ac.kr”
}",,,,
/api/club,/signupValid,POST,회원가입 페이지,이메일 인증 회원가입,"{ 
”username” : “test”,
”password” : “1111”,
”name” : “김축구”,
”university” : “한동대학교”,
”phone” : “010-0000-0000”,
”email” : “test@handong.ac.kr”,
”clubName” : “멋사”,
”description” “우리 멋쟁이 …”,
”region” : “경북”,
”sportCategory” : “축구”
""emailVerificationToken"": ""UUID...”
}","{
”clubId” : 1
}",,,
/api/club,/isValidId?username=,GET,회원가입 페이지,아이디 중복 확인,,"{
”isValidId” : true
}",,Back,학부생 안광은
/api/club,/setting,DELETE,설정 페이지,동아리 삭제,,,,Back,학부생 안광은
/api/club,/search,POST,히스토리 페이지,동아리 찾기,"{
”keyword” : “검색”
}","{
”clubId” : 1,
”clubName” : “멋쟁이 사자처럼”,
”university” : “한동대학교”
}",,,
/api/matchHistory,/createList/{clubId},POST,히스토리 페이지,생성된 히스토리 목록,"{
”keyword” : “검색”
}","{
”isMine” : true,
”matchHistoryList” : [
{
”matchHistoryId” : 1,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : true,
”result” : “승”
},
{
”matchHistoryId” : 2,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : false,
”result” : “”
},
{
”matchHistoryId” : 3,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : true,
”result” : “승”
},
]
}",,,
/api/login,,POST,로그인 페이지,로그인 (RefreshToken),"{
”username” : “test”,
”password” : “1111”
}","{
”RefreshToken” : “Bearer …”
}",,Back,학부생 안광은
/api/auth,,POST,로그인 페이지,로그인 (AccessToken),"{
”username” : “test”,
”password” : “1111”
}","{
”Authorization : “Bearer …”
}",,Back,학부생 안광은
/api/auth,,DELETE,설정 페이지,로그아웃,,,,Back,학부생 안광은
/api/club,/setting/{clubId} (1),PUT,설정 페이지,내 정보 수정,"{
”name” : “”,
”username” : “”,
”password” : “”,
”university” : “”,
”clubName” : “”,
”phone” : “”,
”email” : “”
}","{
”clubId” : 1
}",,Back,학부생 조나은
/api/club,/setting/{clubId} (1),GET,설정 페이지,내 정보 조회,,"{
”clubName” : “”,
”username” : “”,
”password” : “”,
”name” : “”,
”university” : “”,
”phone”: “”,
”email” : “”
}",setttingDetail,Back,학부생 조나은
/api/matchHistory,/addList/{clubId},POST,히스토리 페이지,추가된 히스토리 목록,"{
”keyword” : “검색”
}","{
”isMine” : true,
”matchHistoryList” : [
{
”matchHistoryId” : 1,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : true,
”result” : “승”
},
{
”matchHistoryId” : 2,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : false,
”result” : “”
},
{
”matchHistoryId” : 3,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : true,
”result” : “승”
},
]
}",,,
/api/matchHistory,/{clubId},POST,히스토리 페이지,기본 히스토리 목록,"{
”keyword” : “검색”
}","{
”isMine” : true,
”matchHistoryList” : [
{
”matchHistoryId” : 1,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : true,
”result” : “승”
},
{
”matchHistoryId” : 2,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : false,
”result” : “”
},
{
”matchHistoryId” : 3,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : true,
”result” : “승”
},
]
}",,,
/api/matchHistory,/{matchHistoryId},DELETE,히스토리 페이지,히스토리 삭제,,"{
”matchHistoryId"" : 1
}",,,
/api/matchHistory,/{matchHistoryId},PUT,히스토리 페이지,히스토리 수정,"{
“matchDate” : 2026-03-01,
”location” : “매치 장소”,
”awayClubId” : 1,
”matchType” : true,
”result” : “승”
}","{
”matchHistoryId"" : 1
}",,,
/api/matchHistory,/{matchHistoryId},GET,히스토리 페이지,히스토리 수정 조회,,"{
”matchDate” : 2026-03-1,
”location” : “장소”,
”awayClubId” : 1,
”awayClubName” : “멋사”,
”matchType” : true,
”result” : “승”
}",,,
/api/matchHistory,,POST,히스토리 페이지,히스토리 생성,"{
“matchDate” : 2026-03-01,
”location” : “매치 장소”,
”awayClubId” : 1,
”matchType” : true,
”result” : “승”
}","{
”matchHistoryId"" : 1
}",,,
/api/matchHistory,/finish/{matchPostId},POST,종료된 메치업 페이지,히스토리 작성,"RequestPart
”request” {
”title” : “한마지로와 첫 매치”
”matchType” : “true”,
”result” : “승”,
”mannerScore” : “true”,
”rematch” : “true”
}
 “images” : [
{
imageFile1.jpg,
imageFile2.jpg,
}

matchType이 
true면 경기 이고 
false면 교류

mannerScore가 
true면 업,
false면 다운","{
”matchHistoryId” : 1
}",,Back,학부생 안광은
/api/schedule,,POST,스케줄 페이지,스케줄 생성,"{
”title” : “동아리 MT”,
”startDate” : “2026-02-03”,
”endDate” : “2026-02-03”,
”startTime” : “14:00”,
”endTime” : “16:00”
}","{
”scheduleId” : 1
}",list,Back,학부생 안광은
/api/matchPost,/finish/{matchPostId},GET,종료된 메치업 페이지,종료된 매치업 상세조회,,"{
”clubId” : 1,
”matchDate” :2026-02-04,
”startTime” : 14:00,
”endTime” : 16:00,
”oppositionClubId” : 2,
”phone” : “010-0000-0000”,
”location” : “장소”
”ocationDetail” : “주소 상세”
”content” : “상세내용”
 }",FinishDetail,Back,학부생 안광은
/api/matchPost,/finish,GET,종료된 메치업 페이지,종료된 매치업 조회,,"List : [
{
”matchPostId” : 1,
”region” : “경북”,
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “홀램”,
”university” : “한동대학교”,
”mannerScore” : 50
},
{
{
”matchPostId” : 2,
”region” : “경북”,
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “홀램”,
”university” : “한동대학교”,
”mannerScore” : 50
},
]",FinishLIst,Back,학부생 안광은
엔드포인트,Method,Request,Response,Service Method,구현여부,기능,담당자,분류,사용 페이지
/info,GET,,"{
”clubId” : 1,
”clubName” : 멋사,
”imageUrl” : “urlurl”
}",Info,Back,로그인 유저 정보 가져오기,학부생 안광은,/api/club,
/description/{clubId},GET,,"{
”mannerScore” : 55,
”description” : “동아리 소개”,
”awardResDtoList” : [
{
”awardId” : 1,
”title” : “2025 포항시 대학연합축제 (우승)”
},
{
”awardId” : 2,
”title” : “2025 포항시 대학연합축제 (우승)”
},
{
”awardId” : 3,
”title” : “2025 포항시 대학연합축제 (우승)”
}
],
”isMine” : true
}",Description,Back,나의 동아리 소개 상세,학부생 안광은,/api/club,대시보드 페이지
/{matchPostId},GET,,"{
”location” : “포항체육센터”,
”locationDetail” : “상세주소”,
”startTime” : 14:00,
”endTime” : 17:00,
”phone” : “010-0000-0000”
”content” : “상세내용입니다.”,
”isMine” : false
}",detail,Back,매치업 게시글 상세내용,학부생 안광은,/api/matchPost,매치업 게시판 페이지
/dashboard/{clubId},GET,,"{
”clubId” : 1,
”clubName” : “멋사”,
”description” : “설명”,
”totalMatches” : 10,
”totalWins” : 4,
”totalDraws” : 1,
”totalLosses” : 1,
”mannerScore” : 37.5,
“isMine” : false,
”upcomingResDtoList” : [
    {
     ”matchPostId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchPostId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”ongoingResDtoList” :  [
    {
     ”matchPostId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchPostId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”matchResDtoList” :  [
    {
     ”matchPostId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchPostId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”pastResDtoList” :  [
    {
     ”matchPostId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchPostId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”receiveResDtoList” : [
    {
     ”matchRequestId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchRequestId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”sendResDtoList” : [
    {
     ”matchRequestId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchRequestId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”scheduleResDtoList” : [
    {
     “scheduleId” : 1, 
     ”title” : “멋사 MT”,
     “startDate” : 2026-02-23,
     “endDate” : 2026-02-23
    },
    {
     “scheduleId” : 1, 
     ”title” : “멋사 MT”,
     “startDate” : 2026-02-23,
     “endDate” : 2026-02-23
    }
]
”galleryResDtoList” : [
    {
      “galleryId” : 1,
      “title” : “밀란과 한판”,
      “matchDate” : 2026-04-02,
      “imageUrl” : “urlurl”
    },
    {
      “galleryId” : 1,
      “title” : “밀란과 한판”,
      “matchDate” : 2026-04-02,
      “imageUrl” : “urlurl”
    },
]
}",Dashboard,Back,동아리 대시보드,학부생 안광은,/api/club,대시보드 페이지
/other,GET,"“sportCategoryList” : [
”축구”, “미식축구”
],
”regionList” : [
”경북”, “서울”
],
”startDate” : 2026-02-03,
”endDate” : 2026-03-02,
”keyword” : “멋사”","List [
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
},
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
},
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
}
]",otherList,Back,다른 사람이 쓴 매치업 목록,학부생 안광은,/api/matchPost,매치업 게시판 페이지
/{matchPostId},POST,"{
”startTime” : “15:00”,
”endTime” : “17:00”
}","{
”matchRequestId” : 1
}",create,Back,매치업 신청,학부생 안광은,/api/matchRequest,매치업 게시판 페이지
/{matchPostId},PUT,"{
”sportCategory” : “축구”,
”matchDate” : 2026-01-31,
”location” : “포항체육센터”,
”locationDetail” : “상세주소”,
”startTime” : 14:00,
”endTime” : 17:00,
”content” : “상세내용입니다.”
}","{
”matchPostId” : 1
}",update,Back,매치업 게시글 수정,학부생 안광은,/api/matchPost,매치업 게시판 페이지
/description,PUT,"{
”description” : “소개 수정” 
}","{
”clubId” : 1
}",Descrtiption Update,Back,나의 동아리 소개 수정,학부생 안광은,/api/club,대시보드 페이지
/mine,GET,"“sportCategoryList” : [
”축구”, “미식축구”
],
”regionList” : [
”경북”, “서울”
],
”startDate” : 2026-02-03,
”endDate” : 2026-03-02,
”keyword” : “멋사”","List [
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
},
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
},
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
}
]",mineList,Back,내가 쓴 매치업 목록,학부생 안광은,/api/matchPost,매치업 게시판 페이지
/send,GET,,"List : [
{
”matchRequestId” : 1,
”region” : “경북”
”sportCategory” : “축구”,
”matchDate” : “2026-02-01”,
”location” : 매치문화센터”,
”clubId” : 1,
”imageUrl” : 1,
”university” : “한동대학교”,
”clubName” : “밀란”,
”mannerScore” : 55,
},
{
”matchRequestId” : 2,
”region” : “경북”
”sportCategory” : “축구”,
”matchDate” : “2026-02-01”,
”location” : 매치문화센터”,
”clubId” : 1,
”imageUrl” : 1,
”university” : “한동대학교”,
”clubName” : “밀란”,
”mannerScore” : 55,
}
]",SendLIst,Back,제안한 매치업,학부생 안광은,/api/matchRequest,제안한 매치업 페이지
,POST,"{
”sportCategory” : “축구”,
”matchDate” : 2026-01-31,
”location” : “포항체육센터”,
”locationDetail” : “상세주소”,
”startTime” : 14:00,
”endTime” : 17:00,
”content” : “상세내용입니다.”
}","{
”matchPostId” : 1
}",create,Back,매치업 게시글 생성,학부생 안광은,/api/matchPost,매치업 게시판 페이지
/list,GET,"“sportCategoryList” : [
”축구”, “미식축구”
],
”regionList” : [
”경북”, “서울”
],
”startDate” : 2026-02-03,
”endDate” : 2026-03-02,
”keyword” : “멋사”","List [
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
},
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
},
{
”matchPostId” : 1,
”matchDate” : 2026-01-31,
”sportCategory” : 축구,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “밀란”,
”university” : “한동대학교”,
”region” : “경북”,
”location” : “포항체육센터”,
”mannerScore” : 55
}
]",list,Back,매치업 게시글 기본 목록,학부생 안광은,/api/matchPost,매치업 게시판 페이지
/receive,GET,,"List : [
{
”matchRequestId” : 1,
”region” : “경북”
”sportCategory” : “축구”,
”matchDate” : “2026-02-01”,
”location” : 매치문화센터”,
”clubId” : 1,
”imageUrl” : 1,
”university” : “한동대학교”,
”clubName” : “밀란”,
”mannerScore” : 55,
},
{
”matchRequestId” : 2,
”region” : “경북”
”sportCategory” : “축구”,
”matchDate” : “2026-02-01”,
”location” : 매치문화센터”,
”clubId” : 1,
”imageUrl” : 1,
”university” : “한동대학교”,
”clubName” : “밀란”,
”mannerScore” : 55,
}
]",ReceiveList,Back,제안 받은 매치업,학부생 안광은,/api/matchRequest,제안 받은 매치업
/{matchPostId},DELETE,,,delete,Back,매치업 게시글 삭제,학부생 안광은,/api/matchPost,매치업 게시판 페이지
/ongoing/{matchPostId},DELETE,,"{
”matchPostId” : 1
}",OngoingDelete,Back,진행중 매치업 종료,학부생 안광은,/api/matchPost,진행중 매치업 페이지
/{clubId},GET,,"{
”isMine” : true,
”scheduleResDtoList” : [
{
”scheduleId” : 1,
”title” : “멋사mt”,
”startDate” : 2026-01-02,
”endDate” : 2026-01-04
},
{
”scheduleId” : 2,
”title” : “멋사mt”,
”startDate” : 2026-01-02,
”endDate” : 2026-01-04
}
]
”matchPostResDtoList” : [
{
”matchPostId” : 2,
”matchDate” : 2026-03-09,
”university” : “한동대학교”,
”clubName” : “멋사”,
”status” : true
},
{
”matchPostId” : 3,
”matchDate” : 2026-03-09,
”university” : “한동대학교”,
”clubName” : “멋사”,
”status” : false
}
]",list,Back,스케줄 페이지 조회,학부생 안광은,/api/schedule,스케줄 페이지
/{galleryId},DELETE,,"{
”galleryId” : 1
}",,Back,갤러리 삭제,학부생 안광은,/api/gallery,갤러리 페이지
/match/{clubId},POST,"{
”keyword”
}","List : [
{
”galleryId” : 1,
”title” : “한마지로와의 첫 매칭”,
”matchDate” : “2026-02-03”,
”imageUrl” : “https://s3.amazonaws.com/example/image1.jpg”,
”isOfficial” : true
},
{
”galleryId” : 2,
”title” : “멋사와의 첫 매칭”,
”matchDate” : “2026-02-03”,
”imageUrl” : “https://s3.amazonaws.com/example/image1.jpg”,
”isOfficial” : true
}
]",,Back,매치업 갤러리 목록,학부생 안광은,/api/gallery,갤러리 페이지
/detail/{scheduleId},GET,,"{
”scheduleId” : 1,
”title” : “멋사mt”,
”startDate” : 2026-01-02,
”endDate” : 2026-01-04,
”startTime” : 14:00,
”endTime” : 18:00
}",detail,Back,스케줄 상세 조회,학부생 안광은,/api/schedule,스케줄 페이지
"

/{galleryId}",PUT,"RequestPart
”request” {
”title” : “한마지로와 첫 매치”
”matchDate” : “2026-02-03”
}
 “images” : [
{
imageFile1.jpg,
imageFile2.jpg,
}","{
”galleryId” : 1
}",,Back,갤러리 수정,학부생 안광은,/api/gallery,갤러리 페이지
/receive/{matchRequestId},DELETE,"{
”content” : “거절사유”
}","{
”matchRequestId” : 1
}",ReceiveDelete,Back,제안 받은 매치업 삭제,학부생 안광은,/api/matchRequest,제안 받은 매치업
/ongoing,GET,,"List : [
{
”matchPostId” : 1,
”region” : “경북”,
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “홀램”,
”university” : “한동대학교”,
”mannerScore” : 50
},
{
{
”matchPostId” : 2,
”region” : “경북”,
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “홀램”,
”university” : “한동대학교”,
”mannerScore” : 50
},
]",OngoingList,Back,진행중 매치업,학부생 안광은,/api/matchPost,진행중 매치업 페이지
"

/detail/{galleryId}",GET,,"{
”title” : “멋사 mt”,
”matchDate” : 2026-02-03,
”isOfficial” : true,
”isMine” : true,
”imageUrls” : [
”urlurl11111”,
”urlurl”22222,
”urlurl3333”,
”urlurl4444”,
”urlurl55555”,
]
}",,Back,갤러리 상세보기,학부생 안광은,/api/gallery,갤러리 페이지
/{scheduleId},PUT,"{
”title” : “멋사 mt”,
”startDate” : 2026-02-01,
”endDate” : 2026-02-05,
”startTime” : 14:00,
”endTime” : 19:00
}","{
”scheduleId” : 1
}",update,Back,스케줄 수정,학부생 안광은,/api/schedule,스케줄 페이지
/upcoming,GET,,"List : [
{
”matchPostId” : 1,
”region” : “경북”,
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “홀램”,
”university” : “한동대학교”,
”mannerScore” : 50
},
{
{
”matchPostId” : 2,
”region” : “경북”,
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “홀램”,
”university” : “한동대학교”,
”mannerScore” : 50
},
]",UpcomingLIst,Back,예정 매치업,학부생 안광은,/api/matchPost,예정된 매치업 페이지
/schedule/detail/{clubId}?matchPostId=,GET,,"{
”matchPostId” : 2
”sportCategory” : “축구”
”matchDate” : 2026-02-01
”startTime” : 14:00,
”endTime” : 18:00,
”location” : “주소”,
“locationDetail” : “상세주소”,
”university” : “한동대학교”,
”clubName” : “멋사”,
”phone” : “010-0000-0000”,
”content” : “상세내용”, 
”status” : true
}",schedule detail,Back,매치업 스케줄 상세조회,학부생 안광은,/api/matchPost,스케줄 페이지
/ongoing/{matchPostId},GET,,"{
”startTime” : “14:00”,
”endTime” : “17:00”,
”location” : “매치문화센터”,
”locationDetail” : “상세주소”,
”phone” : “010-0000-0000”,
”content” : “…”
 }",OngoingDetail,Back,진행중 매치업 상세조회,학부생 안광은,/api/matchPost,진행중 매치업 페이지
/award/{awardId},DELETE,,"{
”awardId” : 1
}",Award Delete,Back,수상경력 삭제,학부생 안광은,/api/club,대시보드 페이지
/{scheduleId},DELETE,,"{
”scheduleId” : 1
}",delete,Back,스케줄 삭제,학부생 안광은,/api/schedule,스케줄 페이지
/upcoming/{matchPostId},GET,,"{
”startTime” : “14:00”,
”endTime” : “17:00”,
”location” : “매치문화센터”,
”locationDetail” : “상세주소”,
”phone” : “010-0000-0000”,
”content” : “…”
 }",UpcomingDetail,Back,예정 매치업 상세조회,학부생 안광은,/api/matchPost,예정된 매치업 페이지
/myClub/{clubId},POST,"{
”keyword”
}","List : [
{
”galleryId” : 1,
”title” : “한마지로와의 첫 매칭”,
”matchDate” : “2026-02-03”,
”imageUrl” : “https://s3.amazonaws.com/example/image1.jpg”,
”isOfficial” : false
},
{
”galleryId” : 2,
”title” : “멋사와의 첫 매칭”,
”matchDate” : “2026-02-03”,
”imageUrl” : “https://s3.amazonaws.com/example/image1.jpg”,
”isOfficial” : false
}
]",,Back,우리들의 갤러리 목록,학부생 안광은,/api/gallery,갤러리 페이지
/send/{matchRequestId},GET,,"{
”startTime” : “14:00”,
”endTime” : “17:00”,
”location” : “매치문화센터”,
”locationDetail” : “상세주소”,
”phone” : “010-0000-0000”,
”content” : “…”
 }",SendDetail,Back,제안한 매치업 상세 조회,학부생 안광은,/api/matchRequest,제안한 매치업 페이지
,GET,"“sportCategoryList” : [
”축구”, “미식축구”
],
”regionList” : [
”경북”, “서울”
],
”keyword” : “멋사”","{
”List” : [
{
”clubId” : 1,
”region” : “경북”,
”university” : “한동대학교”,
”clubName” : “멋사”,
”sportCategory” : “축구”,
”mannerScore” : 55
},
{
”clubId” : 2,
”region” : “경북”,
”university” : “한동대학교”,
”clubName” : “멋사”,
”sportCategory” : “축구”,
”mannerScore” : 55
},
{
”clubId” : 3,
”region” : “경북”,
”university” : “한동대학교”,
”clubName” : “멋사”,
”sportCategory” : “축구”,
”mannerScore” : 55
},
{
”clubId” : 4,
”region” : “경북”,
”university” : “한동대학교”,
”clubName” : “멋사”,
”sportCategory” : “축구”,
”mannerScore” : 55
},
]",,Back,동아리 목록,학부생 안광은,/api/club,동아리 검색 페이지
/upcoming/{matchPostId},DELETE,"{
”content” : “…”
}","{
”matchPostId” : 1
}",upcomingDelete,Back,예정 매치업 취소,학부생 안광은,/api/matchPost,예정된 매치업 페이지
/receive/{matchRequestId},PUT,,"{
”matchRequestId” : 1
}",ReceiveUpdate,Back,제안 받은 매치업 수락하기,학부생 안광은,/api/matchRequest,제안 받은 매치업
/dashboard/{clubId},GET,,"{
”clubId” : 1,
”clubName” : “멋사”,
”description” : “설명”,
”totalMatches” : 10,
”totalWins” : 4,
”totalDraws” : 1,
”totalLosses” : 1,
”mannerScore” : 37.5,
“isMine” : false,
”upcomingResDtoList” : [
    {
     ”matchPostId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchPostId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”ongoingResDtoList” :  [
    {
     ”matchPostId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchPostId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”receiveResDtoList” : [
    {
     ”matchRequestId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchRequestId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”sendResDtoList” : [
    {
     ”matchRequestId” : 1,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    },
    {
     ”matchRequestId” : 2,
     “matchDate” : 2026-04-12,
     ”university” : “한동대학교”,
     “clubName” : “밀란”
    }
],
”scheduleResDtoList” : [
    {
     “scheduleId” : 1, 
     ”title” : “멋사 MT”,
     “startDate” : 2026-02-23,
     “endDate” : 2026-02-23
    },
    {
     “scheduleId” : 1, 
     ”title” : “멋사 MT”,
     “startDate” : 2026-02-23,
     “endDate” : 2026-02-23
    }
]
”galleryResDtoList” : [
    {
      “galleryId” : 1,
      “title” : “밀란과 한판”,
      “matchDate” : 2026-04-02,
      “imageUrl” : “urlurl”
    },
    {
      “galleryId” : 1,
      “title” : “밀란과 한판”,
      “matchDate” : 2026-04-02,
      “imageUrl” : “urlurl”
    },
]
}",Dashboard,Back,동아리 대시보드,학부생 안광은,/api/club,동아리 검색 페이지
/receive/{matchRequestId},GET,,"{
”startTime” : “14:00”,
”endTime” : “17:00”,
”location” : “매치문화센터”,
”locationDetail” : “상세주소”,
”phone” : “010-0000-0000”,
”content” : “…”
 }",ReceiveDetail,Back,제안 받은 매치업 상세 조회,학부생 안광은,/api/matchRequest,제안 받은 매치업
,POST,"RequestPart
”request” {
”title” : “한마지로와 첫 매치”
”matchDate” : “2026-02-03”
}
 “images” : [
{
imageFile1.jpg,
imageFile2.jpg,
}","{
”galleryId” : 1
}",create,Back,갤러리 추가,학부생 안광은,/api/gallery,갤러리 페이지
/award,POST,{”title” : “2025 해커톤 수상”},"{
”awardId” : 1,
”title” : “2025 해커톤 수상” }",Award Create,Back,수상경력 추가,학부생 안광은,/api/club,대시보드 페이지
/send/{matchRequestId},DELETE,"{
”content” : “취소사유”
}","{
”matchRequestId” :  1
}",SendDelete,Back,제안한 매치업 취소,학부생 안광은,/api/matchRequest,제안한 매치업 페이지
/{matchPostId},POST,"{
”startTime” : 14:00,
""endTime” : 18:00
}","{
”matchRequestId” : 2
}",,,매치업 신청,,/api/matchRequest,스케줄 페이지
/{clubId},POST,"{
”keyword”
}","List : [
{
”galleryId” : 1,
”title” : “한마지로와의 첫 매칭”,
”matchDate” : “2026-02-03”,
”imageUrl” : “https://s3.amazonaws.com/example/image1.jpg”,
”isOfficial” : true
},
{
”galleryId” : 2,
”title” : “멋사와의 첫 매칭”,
”matchDate” : “2026-02-03”,
”imageUrl” : “https://s3.amazonaws.com/example/image1.jpg”,
”isOfficial” : false
}
]",,Back,갤러리 기본 목록,학부생 안광은,/api/gallery,갤러리 페이지
/createList/{clubId},POST,"{
”keyword” : “검색”
}","{
”isMine” : true,
”matchHistoryList” : [
{
”matchHistoryId” : 1,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : true,
”result” : “승”
},
{
”matchHistoryId” : 2,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : false,
”result” : “”
},
{
”matchHistoryId” : 3,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : true,
”result” : “승”
},
]
}",,,생성된 히스토리 목록,,/api/matchHistory,히스토리 페이지
/{matchHistoryId},DELETE,,"{
”matchHistoryId"" : 1
}",,,히스토리 삭제,,/api/matchHistory,히스토리 페이지
/finish/{matchPostId},POST,"RequestPart
”request” {
”title” : “한마지로와 첫 매치”
”matchType” : “true”,
”result” : “승”,
”mannerScore” : “true”,
”rematch” : “true”
}
 “images” : [
{
imageFile1.jpg,
imageFile2.jpg,
}

matchType이 
true면 경기 이고 
false면 교류

mannerScore가 
true면 업,
false면 다운","{
”matchHistoryId” : 1
}",,Back,히스토리 작성,학부생 안광은,/api/matchHistory,종료된 메치업 페이지
/{clubId},POST,"{
”keyword” : “검색”
}","{
”isMine” : true,
”matchHistoryList” : [
{
”matchHistoryId” : 1,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : true,
”result” : “승”
},
{
”matchHistoryId” : 2,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : false,
”result” : “”
},
{
”matchHistoryId” : 3,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : true,
”result” : “승”
},
]
}",,,기본 히스토리 목록,,/api/matchHistory,히스토리 페이지
/finish,GET,,"List : [
{
”matchPostId” : 1,
”region” : “경북”,
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “홀램”,
”university” : “한동대학교”,
”mannerScore” : 50
},
{
{
”matchPostId” : 2,
”region” : “경북”,
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”clubId” : 1,
”imageUrl” : “urlurl”,
”clubName” : “홀램”,
”university” : “한동대학교”,
”mannerScore” : 50
},
]",FinishLIst,Back,종료된 매치업 조회,학부생 안광은,/api/matchPost,종료된 메치업 페이지
/{matchHistoryId},PUT,"{
“matchDate” : 2026-03-01,
”location” : “매치 장소”,
”awayClubId” : 1,
”matchType” : true,
”result” : “승”
}","{
”matchHistoryId"" : 1
}",,,히스토리 수정,,/api/matchHistory,히스토리 페이지
/addList/{clubId},POST,"{
”keyword” : “검색”
}","{
”isMine” : true,
”matchHistoryList” : [
{
”matchHistoryId” : 1,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : true,
”result” : “승”
},
{
”matchHistoryId” : 2,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : false,
”result” : “”
},
{
”matchHistoryId” : 3,
”matchDate” : 2026-01-03,
”awayClubId” : 2,
”imageUrl” : “urlurl”,
”clubName” : “멋사”,
”university” : “한동대학교”,
”location” : “장소”,
”matchType” : true,
”result” : “승”
},
]
}",,,추가된 히스토리 목록,,/api/matchHistory,히스토리 페이지
/search,POST,"{
”keyword” : “검색”
}","{
”clubId” : 1,
”clubName” : “멋쟁이 사자처럼”,
”university” : “한동대학교”
}",,,동아리 찾기,,/api/club,히스토리 페이지
/setting,DELETE,,,,Back,동아리 삭제,학부생 안광은,/api/club,설정 페이지
/{matchHistoryId},GET,,"{
”matchDate” : 2026-03-1,
”location” : “장소”,
”awayClubId” : 1,
”awayClubName” : “멋사”,
”matchType” : true,
”result” : “승”
}",,,히스토리 수정 조회,,/api/matchHistory,히스토리 페이지
,POST,"{
“matchDate” : 2026-03-01,
”location” : “매치 장소”,
”awayClubId” : 1,
”matchType” : true,
”result” : “승”
}","{
”matchHistoryId"" : 1
}",,,히스토리 생성,,/api/matchHistory,히스토리 페이지
/finish/{matchPostId},GET,,"{
”clubId” : 1,
”matchDate” :2026-02-04,
”startTime” : 14:00,
”endTime” : 16:00,
”oppositionClubId” : 2,
”phone” : “010-0000-0000”,
”location” : “장소”
”ocationDetail” : “주소 상세”
”content” : “상세내용”
 }",FinishDetail,Back,종료된 매치업 상세조회,학부생 안광은,/api/matchPost,종료된 메치업 페이지
,POST,"{
”title” : “동아리 MT”,
”startDate” : “2026-02-03”,
”endDate” : “2026-02-03”,
”startTime” : “14:00”,
”endTime” : “16:00”
}","{
”scheduleId” : 1
}",list,Back,스케줄 생성,학부생 안광은,/api/schedule,스케줄 페이지
/signupValid,POST,"{ 
”username” : “test”,
”password” : “1111”,
”name” : “김축구”,
”university” : “한동대학교”,
”phone” : “010-0000-0000”,
”email” : “test@handong.ac.kr”,
”clubName” : “멋사”,
”description” “우리 멋쟁이 …”,
”region” : “경북”,
”sportCategory” : “축구”
""emailVerificationToken"": ""UUID...”
}","{
”clubId” : 1
}",,,이메일 인증 회원가입,,/api/club,회원가입 페이지
/email/confirm,POST,"{
”email” : “test@handong.ac.kr”,
”code” : “123456”
}","{
“verificationToken"" : ""UUID...”",,,이메일 코드 검증하기,,/api/club,회원가입 페이지
/{matchPostId} (1),DELETE,,"{
”matchPostId” : 1
}",Delete,Back,매치업 삭제,학부생 안광은,/api/matchPost,매치업 게시판 페이지
,POST,"{
”keyword” : “한동대학교”
}","{
”univId” : 1
”name” : “한동대학교”
”address” : “handong.ac.kr”
}",,,학교 찾기,,/api/univ,회원가입 페이지
/isValidId?username=,GET,,"{
”isValidId” : true
}",,Back,아이디 중복 확인,학부생 안광은,/api/club,회원가입 페이지
/email/request,POST,"{
”email” : “test@handong.ac.kr”
}",,,,이메일 검증 보내기,,/api/club,회원가입 페이지
,POST,"{ 
”username” : “test”,
”password” : “1111”,
”name” : “김축구”,
”university” : “한동대학교”,
”phone” : “010-0000-0000”,
”email” : “test@handong.ac.kr”,
”clubName” : “멋사”,
”description” “우리 멋쟁이 …”,
”region” : “경북”,
”sportCategory” : “축구”
}","{
”clubId” : 1
}",,Back,회원가입,학부생 안광은,/api/club,회원가입 페이지
/{matchPostId} (1),PUT,"{
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”startTime” : “11:00”,
”endTime” : “17:00”,
”content” : “화이팅입니다!”
}","{
”matchPostId” : 1
}",Update,Back,매치업 수정,학부생 안광은,/api/matchPost,매치업 게시판 페이지
,POST,"{
”username” : “test”,
”password” : “1111”
}","{
”Authorization : “Bearer …”
}",,Back,로그인 (AccessToken),학부생 안광은,/api/auth,로그인 페이지
/setting/{clubId} (1),GET,,"{
”clubName” : “”,
”username” : “”,
”password” : “”,
”name” : “”,
”university” : “”,
”phone”: “”,
”email” : “”
}",setttingDetail,Back,내 정보 조회,학부생 조나은,/api/club,설정 페이지
,DELETE,,,,Back,로그아웃,학부생 안광은,/api/auth,설정 페이지
/{clubID} (1),GET,,"[
{
""id"": 1,
""title"": ""게시글 작성왕"",
""imageUrl"": ""https://server.com/images/badges/writer_gold.png"",
""isAcquired"": true
},
{
""id"": 2,
""title"": ""첫 승리"",
""imageUrl"": ""https://server.com/images/badges/first_win.png"",
""isAcquired"": true
}
…
]",,Back,,학부생 조나은,/api/badge,메인 페이지
,POST,"{
”sportCategory” : “미식축구”,
”matchDate” : “2026-01-01”,
”location” : “매치문화센터”,
”startTime” : “11:00”,
”endTime” : “17:00”,
”content” : “화이팅입니다!”
}","{
”matchPostId” : 1
}",Create,Back,매치업 등록,학부생 안광은,/api/matchPost,매치업 게시판 페이지
,POST,"{
”username” : “test”,
”password” : “1111”
}","{
”RefreshToken” : “Bearer …”
}",,Back,로그인 (RefreshToken),학부생 안광은,/api/login,로그인 페이지
/setting/{clubId} (1),PUT,"{
”name” : “”,
”username” : “”,
”password” : “”,
”university” : “”,
”clubName” : “”,
”phone” : “”,
”email” : “”
}","{
”clubId” : 1
}",,Back,내 정보 수정,학부생 조나은,/api/club,설정 페이지
