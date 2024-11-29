package fisa.woorizip.backend.member.controller.auth;

import fisa.woorizip.backend.member.domain.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {

    Role[] role() default Role.MEMBER;
}
