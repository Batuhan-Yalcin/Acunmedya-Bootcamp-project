Bootcamp Projesi - Yapı ve Mimari Açıklaması
Genel Bakış
Bu proje, kullanıcı yönetim sistemi olarak tasarlanmış olup, çeşitli kullanıcı türlerini (standart kullanıcı, çalışan, başvuran ve eğitmen) yönetebilen bir Spring Boot REST API'si sunar. Proje, katmanlı mimari prensiplerine göre düzenlenmiştir.
Proje Mimarisi
Proje, klasik katmanlı mimari (layered architecture) prensibini takip eder:
Controller Katmanı: Dış dünya ile iletişimi sağlar
Service Katmanı: İş mantığını yürütür
Repository Katmanı: Veritabanı işlemleri için arayüz sağlar
Entity Katmanı: Veritabanı tablolarıyla eşleşen varlıkları tanımlar
DTO Katmanı: Veri transfer nesnelerini içerir
Varlık (Entity) Yapısı
Proje, kalıtım (inheritance) yapısı ile organize edilmiştir:
User: Temel kullanıcı sınıfı (username, firstName, lastName, email, password, vb.)
Employee: User sınıfından türetilmiş, çalışan bilgilerini içerir (pozisyon bilgisi)
Applicant: User sınıfından türetilmiş, başvuranları temsil eder (hakkında bilgisi)
Instructor: User sınıfından türetilmiş, eğitmen bilgilerini içerir (şirket adı)
DTO (Data Transfer Object) Yapısı
Her entity için iki tür DTO bulunur:
DtoXXX: Entity'den client'a veri göndermek için kullanılır (örn: DtoUser, DtoEmployee)
DtoXXXIU: Client'dan veri almak için kullanılır (Insert/Update) (örn: DtoUserIU, DtoEmployeeIU)
Katman Detayları
Controller Katmanı
Her entity türü için bir controller interface ve implementasyonu bulunur
RESTful API prensiplerine uygun endpoint'ler içerir (GET, POST, PUT, DELETE)
Path yapısı: /api/{entity-type}/{operation}/{param}
Service Katmanı
İş mantığını içeren operasyonları tanımlar
Controller ile repository arasında aracı görevi görür
Entity ve DTO dönüşümlerini yönetir
Repository Katmanı
Spring Data JPA'nın JpaRepository arayüzünü kullanır
Her entity türü için ayrı repository bulunur
Temel CRUD operasyonlarını sağlar
API Endpoint'leri
Her controller şu temel endpoint'leri sağlar:
Kayıt: POST /api/{entity-type}/save
Listeleme: GET /api/{entity-type}/getAll
Detay Görüntüleme: GET /api/{entity-type}/getById/{id} veya /api/applicants/id/{id}
Güncelleme: PUT /api/{entity-type}/update/{id}
Silme: DELETE /api/{entity-type}/delete/{id}
Test Yapısı
Proje, kapsamlı test yapısı içerir:
Controller Testleri:
MockMvc ile controller endpointlerini test eder
Service katmanı mocklanarak izole testler sağlar
Service Testleri:
Repository katmanı mocklanarak birim testleri yapılır
CRUD operasyonlarının doğru çalıştığını doğrular
Testler, JUnit 5 ve Mockito kütüphaneleri kullanılarak yazılmıştır.
Teknolojiler
Spring Boot: Ana framework
Spring Data JPA: Veritabanı işlemleri
PostgreSQL: Veritabanı
Lombok: Kod kalabalığını azaltmak için kullanılır
JUnit ve Mockito: Test framework'leri
Proje Avantajları
Genişletilebilirlik: Yeni kullanıcı türleri kolayca eklenebilir
Bakım Kolaylığı: Katmanlı mimari sayesinde kodun belirli bölümlerini değiştirmek kolaydır
Test Edilebilirlik: Her katman için test kodu yazılmıştır
Güvenli Veri Transferi: DTO'lar sayesinde hassas veriler filtrelenir
Bu proje, temel CRUD işlemlerini başarıyla gerçekleştiren, genişletilebilir ve test edilebilir bir REST API örneğidir.
