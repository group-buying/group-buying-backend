package shop.donutmarket.donut.domain.blacklist.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.donutmarket.donut.domain.blacklist.service.BlackListService;


@RestController
@RequiredArgsConstructor
public class BlackListController {
    
    private final BlackListService blackListService;
}
