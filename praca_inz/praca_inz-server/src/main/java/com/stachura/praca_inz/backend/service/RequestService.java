package com.stachura.praca_inz.backend.service;

import com.stachura.praca_inz.backend.model.Request;
import com.stachura.praca_inz.backend.web.dto.RequestListElementDto;

import java.util.List;

public interface RequestService {

    Request getRequestById(Long id);

    List<RequestListElementDto> getAllRequests(String type);

    List<RequestListElementDto> getAllRequestsForUser(String type,String username);

    List<RequestListElementDto> getAllRequestsForOffice(String type,Long id);

    void createNewRequest(Request report);

    Request updateRequest(Request report);

    void deleteRequestById(Long id);

    void deleteRequest(Request report);
}
