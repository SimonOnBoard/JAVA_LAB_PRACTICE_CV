package com.itis.practice.team123.cvproject.dto;

import com.itis.practice.team123.cvproject.models.Certificate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CertificateDto {
    private String name;
    private String year;

    public static CertificateDto from(Certificate certificate) {
        return CertificateDto.builder()
                .name(certificate.getDescription())
                .year(certificate.getYearOfReceipt())
                .build();
    }

    public static List<CertificateDto> from(List<Certificate> certificates) {
        return certificates.stream()
                .map(CertificateDto::from)
                .collect(Collectors.toList());
    }
}
