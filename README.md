# 🎮 ludensdomain
- 게임을 위한 공간
- 유저들은 게임 유통과 구매장으로 사용
- 유통사 또는 개발사는 게임 발매 및 관리

<br>

## 📜 프로젝트 목표
- 특정 날(대규모 세일 주간)에 다이나믹하게 변동하는 대용량 트래픽을 견뎌내는 게임 유통 사이트(ESD) 구현
- 단순한 기능 구현과 구조에서 벗어나 보다 의미 있고 가독성 높은 코드를 작성
- 기존에 사용하던 기술들을 정확히 이해하며 객체지향 정신을 받아 코드에 녹여내고자 함

<br>

## ⚙ 사용 기술
- Spring Boot 5 
- Maven 빌드 도구, 
- Tomcat
- MySQL (Master-Slave 구조)
- Redis (session, cache)
- Docker

<br>

## 🚄 Git Flow
- 브랜치마다 정확한 목적과 기능을 분리하기 위해 사용
- 운영(main), 개발 주(main), 각 기능(feature) 등 역할 분담

![image](https://user-images.githubusercontent.com/71559880/117329750-ecd86500-aecf-11eb-9087-f4c045969f62.png)

<br>

## 📱 화면
- 카카오 오븐을 이용한 화면 구성
링크 : [카카오 오븐](https://ovenapp.io/project/aiaaov0xDr9DgzqKn2hFKsUoEjdvJYDt#ERlTJ)

### 사용자 화면
<div>
  <img width=200, src="https://user-images.githubusercontent.com/71559880/97028473-26cac500-1597-11eb-9976-bd1979d3f6fc.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/71559880/97028891-ae183880-1597-11eb-9624-68fcdea37c99.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/71559880/97028937-b83a3700-1597-11eb-9e82-ea3eadddf74d.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/71559880/97028985-c2f4cc00-1597-11eb-9618-6a5ef363a431.jpg">
</div>
<div>
  <img width="200" src="https://user-images.githubusercontent.com/71559880/97029028-ce47f780-1597-11eb-8432-5cb4c7ba1352.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/71559880/97029059-d738c900-1597-11eb-8372-2efba5950caa.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/71559880/97029082-de5fd700-1597-11eb-8f29-a6e0635724b2.jpg">
</div>

<br>

### 유통사 화면
<div>
  <img width="200" src="https://user-images.githubusercontent.com/71559880/97029114-ec155c80-1597-11eb-9a5b-d9de80f2199d.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/71559880/97029139-f59ec480-1597-11eb-921b-c1171a0f5047.jpg">
</div>

<br>

## 🗃 DB ERD
![image](https://user-images.githubusercontent.com/71559880/111853344-fd537100-895d-11eb-8539-f7513fd5738c.png)

<br>

## 📝 기술 블로그
1. 대용량 트래픽을 위한 유통 시스템 설계
2. 분산 서버를 이용하며 세션을 유지하는 방법
3. DB 부하 분산을 위한 캐시 적용
4. DB 부하 분산을 위한 윈도우 환경 MySQL replication 설정
5. 세션과 캐시 분리를 위한 Redis 분리와 Docker 사용

블로그 링크 : [https://daakludens.github.io/](https://daakludens.github.io/)

<br>

## 💽 USE CASE
[USE CASE로 이동](https://github.com/f-lab-edu/ludensdomain.wiki.git)
