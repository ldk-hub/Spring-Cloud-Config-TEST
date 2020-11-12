# Micro Service Architecture (MSA)
[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fldk-hub%2FSpringCloudConfigTEST&count_bg=%235A8738&title_bg=%236D1313&icon=&icon_color=%23FF0000&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)
[![HitCount](https://img.shields.io/badge/lisence-MIT-green.svg)](https://github.com/ldk-hub/MSA_project/blob/master/LICENSE)

프로젝트 개요 : MSA 프로젝트를 직접 구성해보고 앱에서 각종 제어를 실습해보는 목적으로 프로젝트를 진행 중. 

프로젝트 목적 MSA의 기능 구현 및 샘플케이스 구성  
![333](https://user-images.githubusercontent.com/12209348/90950280-02654600-e48b-11ea-8992-347d8e50611b.jpg)

MSA는 만능이 아님 서비스 환경적으로  서비스 사용자가 많았을 경우에 적합한 기술이다.  

1. 빌드 및 테스트 시간을 단축시킬 수 있다.  
30개의 서비스를 가진 Monolithic 의 빌드 시간이 30분이었다면, MSA는 각각의 서비스를 1분 만에 빌드 할 수 있습니다. 이는 CI / CD를 추구하는 기업에서는 좀 더 적합한 모델이 됩니다. 왜냐하면 하루에도 몇 번을 빌드 및 배포를 해야 하는데 그때마다 많은 시간을 소모하게 된다면 낭비이기 때문입니다.  

2. 상황에 맞게 기술을 유연하게 적용할 수 있습니다. 예를 들어 TPS(시간당 트랜잭션)가 높고, 읽기 작업이 많은 서비스에는 Node + Redis로 구현을 하고, 트랜잭션 및 안정성이 중요한 서비스에는 Spring + RDB를 적용할 수 있습니다.  



### 마이크로 서비스  
 - 1. 유레카 (집중관리 서비스)  
 
  - 유레카 서버 구성 테스트  
 ![screencapture-localhost-8761-2019-09-19-12_41_07](https://user-images.githubusercontent.com/12209348/65213458-339dff00-dae1-11e9-95e1-425d6662b4a2.png)  

 - 유레카 서버에 클라이언트 연결  
![screencapture-localhost-8761-2019-09-20-09_44_19](https://user-images.githubusercontent.com/12209348/65290702-41a85a00-db8b-11e9-9288-96ff23bc9421.png)  

 - 유레카 클라이언트 테스트   
 ![screencapture-localhost-8080-2019-09-20-09_44_04](https://user-images.githubusercontent.com/12209348/65290701-41a85a00-db8b-11e9-8f08-87958cee740e.png)  

### 개발 진행  
 - 오라클  -> h2 로 데이터베이스 변경(경량화 목적)  
 - h2 DB 구성완료  
 - 프로젝트 DB 연동완료(postgresql, h2)  
 - 클라이언트(h2), 서버(postgresql)로 구성완료  
 
 
### 진행예정
1. 유레카 구성 2개 서버
  - 서버아이피 및 스테이터스 
  -레지스트리, 인스턴스, 클라이언트, 서버  디테일하게 정리
  
2. 리본구성
  - 트래픽 분리(기본 라운드로빈, 트래픽분배 다른패턴 파악)
3. 히스트릭스 구성
   시스템 다운시 간접영향부분 절단 테스트
4. 
