package com.artur.summer.backend.controller;

import com.artur.summer.backend.dto.Document;
import com.artur.summer.backend.dto.DocumentStatus;
import com.artur.summer.backend.dto.HistoryDto;
import com.artur.summer.backend.dto.ui.CardFullInfoDto;
import com.artur.summer.backend.dto.ui.ClientInfoDto;
import com.artur.summer.backend.dto.ui.ProductList;
import com.artur.summer.backend.dto.ui.ProductsDto;
import com.artur.summer.backend.security.ClientTokenService;
import com.artur.summer.backend.service.ClientService;
import com.artur.summer.backend.service.impl.DocumentServiceImpl;
import com.artur.summer.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.artur.summer.backend.security.AuthFilter.X_AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DashboardController extends Api {

    private final ClientService clientService;
    private final ProductService productService;
    private final ClientTokenService tokenService;
    private final DocumentServiceImpl documentService;

    @GetMapping(value = "/client-info")
    public @ResponseBody
    ClientInfoDto getAccounts(@RequestHeader(X_AUTHORIZATION) String session) {
        return clientService.getClientInfo(session);
    }

    @GetMapping("/products")
    public ProductsDto getProducts(@RequestHeader(X_AUTHORIZATION) String session) {
        return productService.getAllActiveProductsShortInfo(session);
    }

    @GetMapping("/card")
    public CardFullInfoDto getCardFullInfo(@RequestHeader(X_AUTHORIZATION) String session, @RequestParam("uuid") String cardUuid) {
        return productService.getCardFullInfo(session, cardUuid);
    }

    @GetMapping("/cardList")
    public List<ProductList> getCardList(@RequestHeader(X_AUTHORIZATION) String session) {
        return productService.getCardList(session);
    }

    @GetMapping("/document")
    public DocumentStatus getDocumentInfo(@RequestHeader(X_AUTHORIZATION) String session, @RequestParam("documentUuid") String documentUuid) {
        return documentService.getDocument(session, documentUuid);
    }

    @GetMapping("/history")
    public List<HistoryDto> getHistory(@RequestHeader(X_AUTHORIZATION) String session, @RequestParam("cardUuid") String cardUuid) {
        return documentService.getHistory(session, cardUuid);
    }


}
