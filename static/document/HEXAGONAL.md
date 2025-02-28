# 헥사고날 아키텍쳐 

1. 모듈별 application.yml 적용
2. 빈 스캔 못함.
3. entityScan
4. 모듈 참조



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


