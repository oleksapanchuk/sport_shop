package dev.oleksa.sportshop.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
public class LocaleResolverConfiguration implements LocaleResolver {

    public static final List<Locale> LOCALES = Arrays.asList(
            new Locale("en"),
            new Locale("uk")
    );

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getHeader("Accept-Language");
        if (language == null || language.isEmpty()) {
            return Locale.forLanguageTag("en");
        }

        Locale locale = Locale.forLanguageTag(language);
        if (LOCALES.contains(locale)) {
            return locale;
        }

        return Locale.forLanguageTag("en");
    }

    public Locale resolveLocale(String language) {
        if (language == null || language.isEmpty()) {
            return Locale.forLanguageTag("en");
        }

        Locale locale = Locale.forLanguageTag(language);
        if (LOCALES.contains(locale)) {
            return locale;
        }

        return Locale.forLanguageTag("en");
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
