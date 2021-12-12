package com.pretendco.ar.service.impl;

import com.pretendco.ar.entity.ARDemo;
import com.pretendco.ar.repository.ARDemoRepository;
import com.pretendco.ar.service.ARDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ARDemoServiceImpl implements ARDemoService {
    @Autowired
    private ARDemoRepository ARDemoRepository;

    @Override
    public List<ARDemo> getARDemoList() {
        return ARDemoRepository.findAll();
    }

    @Override
    public ARDemo findARDemoById(long id) {
        return ARDemoRepository.findById(id);
    }
}

