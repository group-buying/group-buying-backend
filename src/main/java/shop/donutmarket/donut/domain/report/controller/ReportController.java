package shop.donutmarket.donut.domain.report.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.report.service.ReportService;

@RestController
@RequiredArgsConstructor
public class ReportController {
    
    private final ReportService reportService;
}
