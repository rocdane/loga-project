package com.loga.microservices.rms.vendor.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadResponse {
    private String fileName;
    private String downloadUri;
    private long size;
}
