package ru.m1kem1ke.laconic.link.service;

import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.m1kem1ke.laconic.link.converter.ID62Converter;

@Service
public class URLConverterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLConverterService.class);

    @Autowired
    private RedissonClient redissonClient;

    public String shortenURL(String localURL, String longUrl) {
        LOGGER.info("Shortening {}", longUrl);
        int id = redissonClient.getMap("url").size();

        String uniqueID = ID62Converter.INSTANCE.createUniqueID(id);
        redissonClient.getMap("url").put(id, longUrl);
        String baseString = formatLocalURLFromShortener(localURL);
        String shortenedURL = baseString + uniqueID;
        return shortenedURL;
    }

    public String getLongURLFromID(String uniqueID) {
        Integer dictionaryKey = ID62Converter.INSTANCE.getDictionaryKeyFromUniqueID(uniqueID);
        String longUrl = (String) redissonClient.getMap("url").get(dictionaryKey);
        LOGGER.info("Converting shortened URL back to {}", longUrl);
        return longUrl;
    }

    private String formatLocalURLFromShortener(String localURL) {
        String[] addressComponents = localURL.split("/");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < addressComponents.length - 1; ++i) {
            sb.append(addressComponents[i]);
        }

        sb.append('/');
        return sb.toString();
    }

}
