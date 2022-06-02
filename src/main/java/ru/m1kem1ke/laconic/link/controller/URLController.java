package ru.m1kem1ke.laconic.link.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.m1kem1ke.laconic.link.dto.ShortenRequestDto;
import ru.m1kem1ke.laconic.link.service.URLConverterService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class URLController {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLController.class);

    @Autowired
    private URLConverterService urlConverterService;

    @PostMapping(value = "/shortener", consumes = {"application/json"})
    public String shortenUrl(@RequestBody ShortenRequestDto shortenRequest, HttpServletRequest request) throws Exception {
        String longUrl = shortenRequest.getUrl();
        LOGGER.info("Received url to shorten: " + longUrl);

        String localURL = request.getRequestURL().toString();
        String shortenedUrl = urlConverterService.shortenURL(localURL, longUrl);
        LOGGER.info("Shortened url to: " + shortenedUrl);

        return shortenedUrl;
    }

    @GetMapping(value = "/{id}")
    public RedirectView redirectUrl(@PathVariable String id) {
        LOGGER.debug("Received shortened url to redirect: " + id);
        String redirectUrlString = urlConverterService.getLongURLFromID(id);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(redirectUrlString);

        return redirectView;
    }
}