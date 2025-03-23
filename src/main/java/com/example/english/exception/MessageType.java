package com.example.english.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_FOUND("1001", "KAYIT BULUNAMADI"),
    GENERAL_EXCEPTION("9999", "Hata Oluştu"),
    VALIDATION_ERROR("1002", "Geçersiz veri girişi"),
    UNAUTHORIZED("1003", "Yetkisiz erişim"),
    FORBIDDEN("1004", "Erişim engellendi"),
    BAD_REQUEST("1005", "Geçersiz istek"),
    NOT_FOUND("1006", "Kaynak bulunamadı"),
    METHOD_NOT_ALLOWED("1007", "Geçersiz HTTP metodu"),
    CONFLICT("1008", "Çakışma hatası"),
    INTERNAL_SERVER_ERROR("1009", "Sunucu hatası"),
    SERVICE_UNAVAILABLE("1010", "Hizmet kullanılamıyor"),
    DATABASE_ERROR("1011", "Veritabanı hatası"),
    TIMEOUT_ERROR("1012", "İstek zaman aşımına uğradı"),
    RATE_LIMIT_EXCEEDED("1013", "Çok fazla istek gönderildi"),
    INVALID_TOKEN("1014", "Geçersiz veya süresi dolmuş token"),
    INSUFFICIENT_FUNDS("1015", "Yetersiz bakiye"),
    OPERATION_NOT_ALLOWED("1016", "İşlem yapılamaz"),
    DEPENDENCY_FAILURE("1017", "Bağlı servis hatası"),
    FILE_UPLOAD_ERROR("1018", "Dosya yükleme hatası"),
    JSON_PARSE_ERROR("1019", "JSON ayrıştırma hatası"),
    INVALID_PASSWORD_OR_USERNAME("1020", "Şifre veya Username hatalı"),
    PASSWORD_TOO_WEAK("1021", "Şifre yeterince güçlü değil"),
    PASSWORD_EXPIRED("1022", "Şifrenin süresi dolmuş"),
    PASSWORD_MISMATCH("1023", "Girilen şifreler uyuşmuyor"),
    DElETE_FAILED("1024","Silme işlemi başarısız"),
    RECORD_FAILED("1025","Kayıt işlemi başarısız"),
    AI_FAILED("1026","AI Erişim Sağlanamıyor"),
    TOKEN_GENERATE_FAILED("1027", "Token olusturulamadı");
    private String code;
    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
