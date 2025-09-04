package com.codecnpm.doancnpm.service.impl;

import com.codecnpm.doancnpm.dto.ReportDto;
import com.codecnpm.doancnpm.entity.Report;
import com.codecnpm.doancnpm.mapper.ReportMapper;
import com.codecnpm.doancnpm.repository.ReportRepository;
import com.codecnpm.doancnpm.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ReportMapper reportMapper;

    public ReportServiceImpl(ReportRepository reportRepository, ReportMapper reportMapper) {
        this.reportRepository = reportRepository;
        this.reportMapper = reportMapper;
    }

    @Override
    public List<ReportDto> getAllReports() {
        return reportRepository.findAll().stream()
                .map(reportMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReportDto getReportById(Long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        return reportMapper.toDto(report);
    }

    @Override
    public ReportDto createReport(ReportDto reportDto) {
        Report report = reportMapper.toEntity(reportDto);
        return reportMapper.toDto(reportRepository.save(report));
    }

    @Override
    public ReportDto updateReport(Long id, ReportDto reportDto) {
        Report existingReport = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        existingReport.setWeekNumber(reportDto.getWeekNumber());
        existingReport.setContent(reportDto.getContent());
        existingReport.setFeedback(reportDto.getFeedback());
        // Update group if needed
        return reportMapper.toDto(reportRepository.save(existingReport));
    }

    @Override
    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
}