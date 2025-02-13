# Spring
2002 yılında ortaya çıkan, açık kaynaklı bir framework'tür.
Geliştiricilerin karşılaştığı yaygın zorlukları ele alan ve günlük görevlerimizi kolaylaştıran bir kütüphane koleksiyonu sunar.

 İşte bu kütüphanelerden bazıları:

- Spring Core: Spring ile ilgili IoC dahil diğer temel paketleri sunar.
- Spring Web: REST API dahil, web isteklerini karşılamak ve web teknolojileri ile bütünleşmek için gerekli bileşenleri içerir.
- Spring Security: Güvenlikle ilgili kimlik doğrulama (authentication) ve yetkilendirme (authorization) mekanizmalarını içerir.
- Spring Data: Veritabanına erişim ve manipülasyonu sağlar. Örneğin, NoSQL veya ilişkisel veritabanları ile çalışabilir.
- Spring MVC: MVC mimarisine dayalı web uygulamaları geliştirmek için kullanılır.
- Spring Test: Spring uygulamaları için birim testler (unit test) dahil olmak üzere çeşitli test mekanizmalarını içerir.

Bunlar Spring'in geliştirmeyi daha verimli ve yönetilebilir hale getirmek için sunduğu birçok kütüphaneden sadece birkaçıdır.

# Spring Boot
Spring Boot, Spring Framework’ün bir genişletmesi (extension) olup, Spring tabanlı uygulamaların geliştirilmesini ve yapılandırılmasını kolaylaştırır. 
2014 yılında tanıtılmıştır ve özellikle Spring uygulamalarını hızlı bir şekilde geliştirmek için tasarlanmıştır.
Ayrıca, geliştiricilerin kapsamlı bir yapılandırmaya ihtiyaç duymadan hızlı bir şekilde işe koyulmalarını sağlar.

## Spring Boot Kazanımları
- Auto-Configuration: Spring Boot, eklediğimiz bağımlılıklara göre uygulamayı otomatik olarak yapılandırır ve varsayılan ayarlarla çalışmasını sağlar.
- Standalone App: Bir application sunucusuna ihtiyaç duymadan bağımsız uygulamalar geliştirmemizi sağlar.
- Embedded Server: Tomcat ve Jetty benzeri gömülü web sunucuları içerir. Spring boot app deploy etmek için external web sunucu kurma gereksinimini ortadan kaldırmaktadır.
- Production Ready: Prod ortamı için gerekli olan health kontrolleri, metrik ölçümleri ve harici yapılandırmalar gibi özellikler için destek sağlar.
- Spring Boot Starter'lar: Geliştirmeyi hızlandıran ve bağımlılık yönetimini kolaylaştıran hazır bağımlılık grupları sunar (örneğin, spring-boot-starter-web, spring-boot-starter-data-jpa).

# Spring Boot Projesi Oluşturma
[Start Spring Adresi](https://start.spring.io/)

1. Build Tool seçimi yapılır: Gradle veya Maven
2. Programlama dili seçilir: Java veya Kotlin
3. Spring boot versiyonu seçilir
4. Metadata verileri doldurulur
5. Gerekli olan bağımlılıklar eklenir

Gradle ile projeyi ayaka kaldırma komutu:
```shell
gradle bootRun
```
# Spring Boot Dosya Yapısı
[İngilizce Eğitim Video](understanding_springboot_structure.mp4)

Tipik bir Spring Boot projesi, özellikle derleme aracı olarak Gradle kullanıldığında, standartlaştırılmış bir yapıyı takip eder. Bu yapıya bağlı kalmak uyumluluğu ve entegrasyon kolaylığını garanti eder. 
Gradle projenin bir taslağı:
```
spring-boot-intro/
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   └── com/
│   │   │       └── codesignal/springbasics/
│   │   │           └── MyApplication.kt
│   │   ├── resources/
│   │       └── application.properties
│   ├── test/
│   │   ├── kotlin/
│   │   │   └── com/
│   │   │       └── codesignal/springbasics/
│   │   │           └── MyApplicationTests.kt
├── build.gradle.kts
```
## Önemli Dosyalara Yakından Bakış
- `SpringbasicsApplication.kt` Spring Boot uygulamamızın giriş kısmıdır.
- `build.gradle.kts` Build tanımların yapıldığı ve ilgili bağımlılıkların tanımlandığı yapılandırma dosyasıdır.
- `application.yml veya application.properties` Uygulamada kullanacağımız çeşitli yapılandırmaların tanımlandığı yerdir. Gradle'ın build yapılandırması ile karıştırılmamalıdır.
- `SpringbasicsApplicationTest` src/test altında yer alır ve unit test dahil diğer testler burada yapılır.

# Gradle
Gradle, yazılım projelerinin inşa, test ve paketleme süreçlerini kolaylaştıran çok yönlü bir yapı otomasyon aracıdır. Genellikle Java ve Kotlin projeleri için kullanılsa da, Gradle çok sayıda başka dili de destekler. Temel işlevleri şunlardır:
- Dependency Management: Gradle otomatik olarak ilgili bağımlılıkları alır ve gereksinime göre projeye entegre eder.
- Code Compilation: Kaynak source kodunu executable koda dönüştürür.
- Test Execution: Gradle, kodunuzun doğruluğunu doğrulamak için testlerinizi çalıştırır.
- Application Packaging: Kodunuzu dağıtılabilir ve çalıştırılabilir bir formatta birleştirir.

## Gradle Yapılandırma Dosyasını İnceleyelim
```gradle
plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "com.codesignal"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        freeCompilerArgs.set(listOf("-Xjsr305=strict"))
    }
}


allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

```
- Plugins: Build'e işlevsellik eklemek için kullanılır. `org.springframework.boot` plugini önemlidir çünkü Spring Boot'u herhangi bir sürüm belirtmeden kullanma olanağı sağlar. `gradle bootRun` komutunuda bünyesinde barındırmaktadır.
- Dependencies: Proje için gerekli olan bağımlılıkları tanımlar. Dikkat etmenizi istediğim bir nokta genellikle `spring-boot-starter-web` gibi bağımlılıkları içerir.
- Repositories: Bağımlılıkların nereden indirileceğini belirtir. [`mavenCentral()`](https://mvnrepository.com/repos/central) genellikle kullanılır.
- Group ve Version: 
  - Group: Base package'ın adıdır.
  - Version: Package'ın sürüm tanımıdır.
- Source Compatibility: JVM Kotlin `java {
  toolchain {
  languageVersion = JavaLanguageVersion.of(17)
  }
  }` Önemlidir! JVM özelliklerinin kullanılacağı sürüm tanımlanır.
- Kotlin JVM Toolchain:  Kotlin compiler'ın JVM 17 sürümüne compile etmesi gerektiğini söyler.
- Test Configuration: `tasks.withType<Test> { useJUnitPlatform() }` testleri çalıştırmak için JUnit Platform'un kullanılması gerektiğini belirtir. Bu, modern JUnit 5 özelliklerini kullanmanızı sağlar. 