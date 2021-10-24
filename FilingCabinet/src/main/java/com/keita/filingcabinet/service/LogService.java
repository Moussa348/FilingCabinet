package com.keita.filingcabinet.service;

import com.keita.filingcabinet.model.dto.PagingRequest;
import com.keita.filingcabinet.model.entity.Log;
import com.keita.filingcabinet.model.enums.OperationType;
import com.keita.filingcabinet.repository.LogRepository;
import com.keita.filingcabinet.security.OwnershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void add(String fileId, String fileName, OperationType operationType) {
        logRepository.save(
                Log.builder()
                        .fileId(fileId)
                        .fileName(fileName)
                        .by(OwnershipService.getCurrentUserDetails())
                        .date(LocalDateTime.now())
                        .operationType(operationType)
                        .build());
    }

    public List<Log> findAllByFileId(String fileId, PagingRequest pagingRequest) {
        return logRepository.findAllByFileId(
                fileId,
                PageRequest.of(
                        pagingRequest.getNoPage(),
                        pagingRequest.getSize(),
                        Sort.by("date")
                )
        );
    }

}
