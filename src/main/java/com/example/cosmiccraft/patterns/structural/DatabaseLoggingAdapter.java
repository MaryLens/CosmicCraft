package com.example.cosmiccraft.patterns.structural;

import com.example.cosmiccraft.models.LogEntry;
import com.example.cosmiccraft.repositories.LogRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class DatabaseLoggingAdapter {
    private final Logger delegate;
    private final LogRepository logRepository;
    private final String source;

    public void info(String msg) {
        delegate.info(msg);
        saveLog("INFO", msg);
    }

    public void info(String format, Object arg) {
        delegate.info(format, arg);
        saveLog("INFO", String.format(format, arg));
    }

    public void error(String msg) {
        delegate.error(msg);
        saveLog("ERROR", msg);
    }

    public void error(String format, Object arg) {
        delegate.error(format, arg);
        saveLog("ERROR", String.format(format, arg));
    }

    public void debug(String msg) {
        delegate.debug(msg);
        saveLog("DEBUG", msg);
    }

    public void debug(String format, Object arg) {
        delegate.debug(format, arg);
        saveLog("DEBUG", String.format(format, arg));
    }

    private void saveLog(String level, String message) {
        LogEntry entry = new LogEntry();
        entry.setLevel(level);
        entry.setMessage(message);
        entry.setSource(source);
        entry.setUserEmail(getCurrentUserEmail());
        entry.setTimestamp(LocalDateTime.now());
        logRepository.save(entry);
    }

    private String getCurrentUserEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())
                ? auth.getName() : null;
    }
}
