# 헥사고날 아키텍쳐 

1. 모듈별 application.yml 적용
2. 빈 스캔 못함.
3. entityScan
4. 모듈 참조


### 🏛 **Hexagonal Architecture 구조 정리**

프로젝트는 **Hexagonal Architecture (Ports & Adapters)** 패턴을 따르며,  
모듈 간 **명확한 의존성 방향**을 유지합니다.

---

## **📂 프로젝트 모듈 구조**
```
📦 프로젝트 루트
├── 📂 application        # 애플리케이션 진입점 (Controller 계층)
│   ├── 📂 controller    # API 요청 처리
│   └── 📄 (기타 설정 파일)
│
├── 📂 core              # 비즈니스 로직 (도메인 중심 계층)
│   ├── 📂 domain       # 핵심 도메인 엔티티 및 비즈니스 규칙
│   ├── 📂 service      # 도메인 서비스 (비즈니스 로직 수행)
│   ├── 📂 port         # 인터페이스 (inbound & outbound ports)
│   └── 📄 (기타 설정 파일)
│
├── 📂 infra             # 외부 시스템 및 인프라 계층
│   ├── 📂 cache        # 캐싱 관련 모듈 (예: Redis)
│   ├── 📂 message      # 메시지 큐 (Kafka, RabbitMQ)
│   ├── 📂 persistence  # 데이터베이스 접근 (JPA, MyBatis)
│   ├── 📂 batch        # 배치 처리 모듈 (Spring Batch)
│   └── 📄 (기타 설정 파일)
│
└── 📄 build.gradle     # Gradle 설정
```

---

## **📌 모듈 간 의존성 방향**
📌 **Hexagonal 구조에서 모듈 간의 의존성 흐름**  
✅ **의존성 규칙**을 준수하여 **단방향 의존성**을 유지합니다.

```
[Application]  --->  [Core]  <---  [Infra]
```

| **모듈**           | **설명**                                                      | **의존성**                           |
|-------------------|-----------------------------------------------------------|--------------------------------|
| **`application`**  | - API 진입점 (Controller)                                  | `core` 모듈에 의존              |
| **`core`**        | - 도메인 중심의 비즈니스 로직 (`service`, `domain`, `port`) | `infra` 모듈에 의존 (`port` 사용) |
| **`infra`**       | - 외부 시스템 연동 (`persistence`, `cache`, `message`, `batch`) | `core.port`를 구현               |

---

## **📌 상세한 의존성 흐름**
1. **Application 모듈 → Core 모듈**
    - `application` 은 `core` 모듈을 호출하여 비즈니스 로직을 실행합니다.
    - `Controller → Service` 호출 방식으로 작동.

2. **Core 모듈 ← Infra 모듈 (persistence, cache, batch, message)**
    - `core` 모듈은 **직접 `infra` 모듈에 의존하지 않음!**
    - 대신 `port` 인터페이스를 정의하고, `infra` 모듈이 이를 구현.
    - **즉, core는 infra에 대해 알지 못하고, port만 바라봄.**

3. **Infra 모듈 (Persistence, Cache, Batch, Message)**
    - `persistence`: DB (JPA, MyBatis) 연동
    - `cache`: Redis 등 캐싱 활용
    - `batch`: 스케줄링 및 배치 처리
    - `message`: Kafka / RabbitMQ 기반 메시지 처리

---

## **📌 Hexagonal Architecture 의존성 방향 (다이어그램)**
```plaintext
   [ Controller ]  --->  [ Core Service ]
        |                      |
        ▼                      ▼
  [ Core Port ]  <---  [ Infra Adapter ]
    (Interface)         (Persistence, Cache, Batch, Message)
```

---

## **📌 Hexagonal Architecture 핵심 원칙**
✔ **Core (비즈니스 로직)와 Infra (외부 시스템)를 분리**  
✔ **Core는 Infra에 대해 알지 못하고, Port 인터페이스를 통해 소통**  
✔ **단방향 의존성 유지 (`Application → Core`, `Core ← Infra`)**  
✔ **테스트 및 유지보수 용이 (Infra 교체 가능)**

---

## **📌 결론**
✅ **이 구조를 통해 "유지보수성과 확장성"을 극대화할 수 있음** 🚀  
✅ **Infra 모듈을 교체해도 Core 모듈이 영향을 받지 않음**  
✅ **비즈니스 로직과 외부 연동을 분리하여 독립적인 테스트 가능**

---




### application.yml 모듈별 적용하기 
* 서로 다른 모듈, application.yml 분리하기, resource/config 안에 넣으면 인식됨

<details>
  <summary>📌 왜 해결되었을까?</summary>

✅ **`application.yml`을 `src/main/resources/config/` 안에 두어서 문제 해결 🎯**

### **📌 왜 해결되었을까?**
Spring Boot는 **설정 파일을 자동으로 로드하는 기본 위치**가 있다.

#### **1️⃣ Spring Boot가 기본적으로 찾는 설정 파일 위치**
Spring Boot는 `application.yml`을 아래 위치에서 자동으로 탐색함:
1. `classpath:/`
    - 즉, `src/main/resources/application.yml`
2. `classpath:/config/`
    - 즉, `src/main/resources/config/application.yml`
3. `file:./`
    - 즉, `프로젝트 루트 디렉토리/application.yml`
4. `file:./config/`
    - 즉, `프로젝트 루트 디렉토리/config/application.yml`

✅ **즉, `src/main/resources/config/application.yml`에 두면 Spring Boot가 자동으로 로드할 수 있는 기본 경로 중 하나라서 해결된 것!**

---

### **📌 자동 로드되는 경로의 우선순위**
Spring Boot는 설정 파일을 여러 곳에서 찾을 수 있지만, **우선순위**가 있다.
> **우선순위 (높음 → 낮음)**  
> 1️⃣ `file:./config/` (프로젝트 루트 디렉토리의 `config/` 폴더)  
> 2️⃣ `file:./` (프로젝트 루트 디렉토리)  
> 3️⃣ `classpath:/config/` (`src/main/resources/config/`) ✅  
> 4️⃣ `classpath:/` (`src/main/resources/`)

📌 **즉, `config/` 폴더 안에 있으면 `src/main/resources/`보다 높은 우선순위로 적용됨!**  
✔ 그래서 `persistence` 모듈의 `application.yml`을 `src/main/resources/config/`에 넣으면 **자동 인식**됨.

---

### **📌 더 좋은 설정 관리 방법?**
✅ **1. `config/` 폴더를 유지하면서 프로파일 관리**
- 여러 환경(예: `dev`, `prod`)을 고려해서 아래처럼 설정 가능
```yaml
📂 src/main/resources/config/
 ├── 📄 application.yml  # 기본 설정
 ├── 📄 application-dev.yml  # 개발 환경 설정
 ├── 📄 application-prod.yml  # 운영 환경 설정
```
- 실행 시 프로파일 적용:
  ```bash
  java -jar myapp.jar --spring.profiles.active=dev
  ```

✅ **2. `spring.config.import`를 사용해 추가적인 설정 가져오기**
- `application.yml`에서 `config/application-persistence.yml`을 명시적으로 불러올 수도 있음
```yaml
spring:
  config:
    import: "classpath:/config/application-persistence.yml"
```

---

### **📌 정리 🚀**
- `src/main/resources/config/application.yml`을 사용하면 **Spring Boot가 자동 인식**하여 설정 적용됨.
- `config/` 폴더는 Spring Boot에서 **기본적으로 인식하는 우선순위 높은 설정 폴더**이므로 별도의 import 필요 없음.
- 향후 여러 환경(`dev`, `prod`)을 고려하면 **프로파일 기반(`application-dev.yml`) 설정도 함께 활용하는 것이 좋음**! 🚀

---
✅ **이제 설정 문제 해결했으니, 다음으로 뭘 더 도와줄까?** 😃

</details>


