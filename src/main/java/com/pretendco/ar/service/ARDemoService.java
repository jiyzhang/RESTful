package com.pretendco.ar.service;
import com.pretendco.ar.entity.ARDemo;
import java.util.List;

public interface ARDemoService {
    public List<ARDemo> getARDemoList();
    public ARDemo findARDemoById(long id);
}
