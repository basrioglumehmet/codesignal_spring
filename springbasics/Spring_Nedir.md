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

# Bootstrap Class (Başlangıç Sınıfı)
Bir Spring Boot uygulamasının temel taşı, bootstrap sınıfıdır. Bu sınıf, uygulamayı başlatan ve Spring konteynerini oluşturan ana giriş noktasıdır.
```kotlin
package com.codesignal.springbasics

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringbasicsApplication

fun main(args: Array<String>) {
	runApplication<SpringbasicsApplication>(*args)
}
```
`SpringBootApplication` annotation'ı özellikle güçlüdür. Özetle, Uygulamanın yapılandırmalarını bizim yerimize ayarlamasını yapar.
 Diğer önemli bileşenimiz main metot'udur. Bu metot uygulamanın giriş noktasına hizmet eder ve jar dosyası çalıştırıldığı vakit yürütülür. `runApplication<MyApplication>(*args)` ise uygulamanın başlatılması gerektiğini ve gömülü olan Tomcat sunucusunu başlatmasını söyler.

# Application Yapılandırma Dosyasını Anlayalım
`application.properties` veya `application.yml` Spring Boot uygulamasının çeşitli yapılandırmaları burada tanımlanır ve `src/main/resources` dizisi altında toplanır.
- server.port: Sunucunun hangi portta çalışacağının belirtildiği yerdir. `server.port = 8080`
- logging.level.root: Log seviyesinin tanımlandığı yerdir. `logging.level.root = DEBUG`
- Database Yapılandırması:
  - spring.datasource.url= `protokol (jdbc)` ://`veritabanı_türü (mysql veya postgres)`://`host ve port bilgisi (localhost:3306)`/`veri tabanı adı (mydb)` 
  - spring.datasource.username= Veritabanının kullanıcı adıdır
  - spring.datasource.password= Veritabanının kullanıcı şifresidir
- Custom Application Properties: Uygulamanızda kullanmak üzere özel propertyler ekleyebilirsiniz. Bunlara daha sonra Spring @Value annotation veya Environment arayüzü kullanılarak kodunuzda erişilebilir.

# Yaygın Gradle Komutları
- gradle build: Projeyi compile eder
- gradle clean: Build dosyasını temizler
- gradle test: Testleri çalıştırır
- gradle bootRun: Spring Boot uygulamasını ayağa kaldırır

# Tomcat <img src="./Apache_Tomcat_logo.svg.png" width="28" />
Web geliştirmeye yeni başladıysanız, sınırlı bir süre boyunca çalışıp sonra sonlanan programlar yazmaya alışkın olabilirsiniz. Ancak, bir web uygulaması oluştururken, uygulamanın kullanıcılardan HTTP istekleri almak ve bunlara yanıt vermek için sürekli çalışması gerekir. Spring Boot'un ağırlıklı olarak web geliştirme için kullanıldığı göz önüne alındığında, gömülü bir Tomcat sunucusu içerir. Bu sunucu, gelen web isteklerini dinleyerek uygulamanızı süresiz olarak çalıştırır. Varsayılan olarak, uygulama 8080 numaralı bağlantı noktasında çalışır. Sonuç olarak, gradle bootRun komutunu çalıştırdığınızda, uygulamanız basitçe çalışıp çıkmaz; başlar ve siz manuel olarak durdurana kadar etkin kalır.
# Actuator
`implementation("org.springframework.boot:spring-boot-starter-actuator")` Actuator, Spring Boot uygulamalarının yönetimi ve izleme işlemlerini kolaylaştıran bir araçtır.
```yaml
management:
  endpoint:
    health:
      show-details=always:
```
bu yaml yapılandırma tanımı her zaman detayları göster dediğimiz kısımdır. `http://localhost:8080/actuator/health` adresinden erişim sağlanır.
```json
{
    "status" : "UP"
}
```
gibi json response'u döner.
health yerine farklı durumlarıda sorgulayabilirsiniz.

autoconfig = Tüm autoconfig tanımlamalarını gösterir

beans = Spring tarafından yönetilen tüm beanleri gösterir

dump = Thread dump almayı sağlar.

env = Spring’s ConfigurableEnvironment değerlerini gösterir.

health = Uygulama sağlığını gösterir.

info = Uygulama bilgilerini gösterir.

loggers = Uygulamada kullanılan log bilgileri gösterir.

mappings = Uygulamada kullanılan log bilgileri gösterir.

trace = En son kullanılan 100 HTTP isteklerini listeler.

# Dependency Nedir
Spring Bean konusuna değinmeden önce, dependency nedir anlayarak başlayalım. Bir sandviç yaptığınızı düşünün. Bu sandviçin içerisine ekmek, domates ve peynir gibi class'lar oluşturulur, yanında ızgaraya yer verilir ki sandviç yapabilelim. Sandviç yapmak için ızgaranın gerekli malzemelere, yani class'lara ihtiyacı vardır. İşte tam bu noktada bağımlılıklar devreye girmektedir.
## Sandviç Problemini Tanımlayalım
Sandviç problemini tanımlarken manuel bağımlılık yönetimi yapacağız.

```kotlin
class Bread
class Lettuce
class Tomato
class Cheese
class Sandwich
class Grill

class SandwichMaker(
    private val grill: Grill,
    private val bread: Bread,
    private val lettuce: Lettuce,
    private val tomato: Tomato,
    private val cheese: Cheese
) {
    fun prepareSandwich(): Sandwich {
        println("Sandwich is ready!")
        return Sandwich()
    }
}

fun main() {
    val grill = Grill()
    val bread = Bread()
    val lettuce = Lettuce()
    val tomato = Tomato()
    val cheese = Cheese()
    val sandwichMaker = SandwichMaker(grill, bread, lettuce, tomato, cheese)
    sandwichMaker.prepareSandwich()
}
```
Manuel yönetim karmaşıktır ve bağımlılıklardan emin olunması gerekmektedir. Bağımlılıkların sayısı arttığı taktirde yönetimi zahmetli olacaktır.

Spring Context ve Beans Bizi Bu Problemlerden Kurtarıyor
Inversion of Control (IoC) ve Dependency Injection (DI), Spring framework'ü ile birlikte nesne yönetimini ve bağımlılıkları kolaylaştırarak, yazılım geliştirmede karışıklıkları önler. Bu da uygulamanın daha esnek, modüler ve bakımı daha kolay hale gelmesini sağlar.

# IoC (Inversion of Control)
IoC, Temelde bir nesnenin kontrolünü dışarıya (genellikle bir framework'e) devretme prensibidir. Yani, uygulamanın nesne yaratma ve yönetme sorumluluğu, framework (örneğin Spring) gibi bir dış bileşene bırakılır. Bu sayede kodumuz daha esnek, modüler ve test edilebilir hale gelir.

IoC'nin üç temel yönü:

- Nesne Oluşturma: Bir sınıfın nesnesi oluşturulurken, bu işin nasıl yapılacağı (örneğin, hangi parametrelerin geçileceği) dışarıda bir sistem tarafından belirlenir. Yani, nesnenin yaratılmasındaki kontrol uygulama kodundan alınır.

- Nesne Yapılandırma: Nesne yaratıldıktan sonra, bu nesnenin yapılandırılmasına (örneğin, gerekli bağımlılıklarının verilmesine) da dışarıdan karar verilir. Bu sayede, sınıflar arası bağımlılıkları yönetmek daha kolay hale gelir.

- Nesne Yönetimi ve Denetimi: Nesnelerin yaşam döngüsünün yönetimi de dışarıya devredilir. Spring Context, nesnelerin ne zaman oluşturulacağı, ne zaman yok edileceği gibi süreçleri yönetir. Bu, Garbage Collection (Çöp Toplayıcı) ve Bean yaşam döngüsü gibi süreçlerin otomatikleşmesini sağlar.

## Spring Context ve Beans
Spring, nesnelerin yönetimini IoC Container (Spring Context) aracılığıyla yapar. Spring Context, uygulama sırasında oluşturulan ve yönetilen nesneleri Beans olarak adlandırır.

`Bean`: Spring Context içinde yönetilen her bir nesne bir Bean olarak adlandırılır.

`Spring Context`: IoC Container'ın bir başka adı olan Spring Context, tüm Beans'lerin oluşturulmasından, yapılandırılmasından ve yaşam döngüsünün yönetilmesinden sorumludur.
Spring Context, Bean sırasını ve biçimini belirleyebilir. Bu şu anlama gelir:

- Bean'lerin Sırası: Spring Context, bean'lerin oluşturulma sırasını belirler. Örneğin, bir bean'in bir başka bean'e bağımlılığı varsa, Spring doğru sırayla bu bean'leri oluşturur.
- Bean'lerin Biçimi: Spring, bean'leri yapılandırırken, @Component, @Service, @Repository, @Controller gibi anotasyonları kullanarak ve XML veya Java Config ile bean'lerin yapılandırılmasını kontrol eder. Bu, bean'lerin nasıl ve ne şekilde oluşturulacağına dair kontrol sağlar.

# @Component Annotation
Spring Framework'e bu classın yönetiminden sen sorumlusun dememizdir. `@Component` tanımlandığı zaman singleton(single instance) oluşturur.
```kotlin
package com.codesignal

import org.springframework.stereotype.Component

@Component
class Grill
```
Peki @Component ne yapıyor?
- Discovery: Component ile işaretlenmiş class'ları arar.
- Creation: Class başına instance oluşturur bunun anlamı manuel olarak obje oluşturmana gerek yok demektir.
- Management: Spring bahsettiğimiz objelerin yönetiminden, oluşturulmasından ve provided olmasından sorumludur.

Şu sebeplerden dolayı kullanışlıdır:
- Manuel kullanımı azaltır
- Component ile işaretlenen class'lara merkezi yönetim sağlar
- Spring, Component ile işaretlenen nesnelerin ihtiyaç duyulduğu diğer bölümlerine nesnemizi otomatik olarak sağlar.

# Spring Bean'leri Nasıl Araştırır?
@Component, @Service veya @Repository ile işaretlenen class'ları base ve alt package konumunda araştırmaktadır (`src/main/kotlin/com/codesignal/todoapp`)

# @Bean Annotation
`@Component` işaretlemesine ek olarak `@Bean kullanabilirsiniz. Bu bize Bean'ler üzerinde ayrıntılı kontrol sağlar.

```kotlin
package com.codesignal

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {
    @Bean
    fun grill(): Grill {
        return Grill()
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
```

# Bölünmüş Yapılandırma Sınıfları (@Configuration Annotation)
Daha güzel bir mimari yapısı için yapılandırmaları `@Configuration` annotation ile ayırabiliriz.
```kotlin
package com.codesignal

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SandwichConfig {
    @Bean
    fun bread(): Bread {
        return Bread()
    }

    @Bean
    fun lettuce(): Lettuce {
        return Lettuce()
    }

    @Bean
    fun tomato(): Tomato {
        return Tomato()
    }

    @Bean
    fun cheese(): Cheese {
        return Cheese()
    }

    @Bean
    fun sandwich(): Sandwich {
        return Sandwich(bread(), lettuce(), tomato(), cheese(), grill())
    }

    @Bean
    fun grill(): Grill {
        return Grill()
    }
}
```
Spring, uygulama başlatıldığında tüm class'ları tarar (component scanning) ve @Configuration ile işaretlenmiş olan sınıfları bulur. Daha sonra, bu sınıfların içindeki @Bean ile işaretlenmiş metotları tarar. Eğer @Bean anotasyonu ile işaretlenen bir metot varsa, Spring bu metodu çağırarak dönen nesneyi Spring Context'e (IoC Container'a) ekler. Böylece, bu nesne artık bir instance (bean) olarak yönetilir ve gerektiğinde bağımlılık olarak enjekte edilebilir. Gerçek dünya uygulamalarında katmanlı mimaride config adlı katmanda yapılandırmalarımızı toplarız.
```
com.example.project
├── config
│   ├── AppConfig.java
│   ├── SecurityConfig.java
│   ├── DatabaseConfig.java
├── service
├── repository
├── controller
```

## Component ve Bean Annotation Farkı
- @Component: Class seviyesinde uygulanır ve Spring için işaretlenir. Spring bir adet instance oluşturur ve onu yönetir. Dikkat etmenizi istiyorum @Component sadece tek bir sınıfı yönetir, **birden fazla instance oluşturmanız durumunda uygun değildir.**
- @Bean Nedir?
  - @Bean, Spring IoC Container tarafından yönetilen bir nesne (bean) oluşturmak için kullanılan bir anotasyondur.
  - @Configuration anotasyonu ile işaretlenmiş sınıflar içinde kullanılır.
  - Spring'in otomatik olarak yönetmediği (örn: 3rd party library'lerden gelen) nesneleri Spring Context'e dahil etmemizi sağlar.
  - Spring’in component scanning mekanizması (@Component, @Service, @Repository, @Controller) ile otomatik olarak algılayamadığı bağımlılıkları yönetmemize yardımcı olur.
```kotlin
@Configuration
public class AppConfig {
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```