package com.artur.summer.backend.controller;

import com.artur.summer.backend.dto.ui.ClientInfoDto;
import com.artur.summer.backend.dto.ui.ProductsDto;
import com.artur.summer.backend.security.ClientTokenService;
import com.artur.summer.backend.service.ClientService;
import com.artur.summer.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static com.artur.summer.backend.security.AuthFilter.X_AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DashboardController extends Api {

    private final ClientService clientService;
    private final ProductService productService;
    private final ClientTokenService tokenService;

    @PostMapping(value = "/client-info")
    public @ResponseBody
    ClientInfoDto getAccounts(@RequestHeader(X_AUTHORIZATION) String session) {
        return clientService.getClientInfo(session);
    }

    @GetMapping("/products")
    public ProductsDto test(@RequestHeader(X_AUTHORIZATION) String session) {
        return productService.getAllActiveProductsShortInfo(session);
    }

}
