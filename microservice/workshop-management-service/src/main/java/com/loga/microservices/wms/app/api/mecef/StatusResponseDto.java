package com.loga.microservices.wms.app.api.mecef;

import java.util.Date;
import java.util.List;

public class StatusResponseDto {
    Boolean status;
    String version;
    String ifu;
    String nim;
    Date tokenValid;
    Date serverDateTime;
    Integer pendingRequestsCount;
    List<PendingRequestDto> pendingRequestsList;
}
