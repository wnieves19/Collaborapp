package io.collaborapp.collaborapp.authentication;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by wilfredonieves on 10/27/17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthenticationScope
{
}