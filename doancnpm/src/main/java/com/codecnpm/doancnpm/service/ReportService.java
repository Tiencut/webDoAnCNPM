package com.codecnpm.doancnpm.service;

import com.codecnpm.doancnpm.dto.ReportDto;

import java.util.List;

public interface ReportService {
    List<ReportDto> getAllReports();
    ReportDto getReportById(Long id);
    ReportDto createReport(ReportDto reportDto);
    ReportDto updateReport(Long id, ReportDto reportDto);
    void deleteReport(Long id);
}