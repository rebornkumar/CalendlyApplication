package com.learn.CalendlyApplication.service;

import com.learn.CalendlyApplication.dto.BasicResponse;
import com.learn.CalendlyApplication.dto.SessionDto;

import java.util.List;
import java.util.Map;

public interface HostService {
    void addSession(Integer hostId,List<SessionDto> sessionDtoList);
}
