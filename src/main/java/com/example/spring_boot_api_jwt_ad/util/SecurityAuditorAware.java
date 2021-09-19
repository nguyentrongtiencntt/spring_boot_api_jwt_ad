package com.example.spring_boot_api_jwt_ad.util;

import com.example.spring_boot_api_jwt_ad.authen.UserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityAuditorAware implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        if (authentication.getPrincipal()=="anonymousUser"){
            return Optional.of(0l);
        }

        return Optional.of(((UserPrincipal)
                authentication.getPrincipal()).getUserId());
    }
}
