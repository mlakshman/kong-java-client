package com.github.vaibhavsinha.kong.impl.service.plugin.authentication;

import java.io.IOException;

import retrofit2.Response;

import com.github.vaibhavsinha.kong.api.plugin.authentication.OAuth2ManageService;
import com.github.vaibhavsinha.kong.exception.KongClientException;
import com.github.vaibhavsinha.kong.internal.plugin.authentication.RetrofitOAuth2ManageService;
import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.Application;
import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.ApplicationList;
import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.Token;
import com.github.vaibhavsinha.kong.model.plugin.authentication.oauth2.TokenList;

public class OAuth2ServiceV2 implements OAuth2ManageService {

  private RetrofitOAuth2ManageService retrofitOAuth2ManageService;
  Application application;
  ApplicationList applicationList;
  Token token;
  TokenList tokenList;

  public OAuth2ServiceV2(RetrofitOAuth2ManageService retrofitOAuth2ManageService) {
    this.retrofitOAuth2ManageService = retrofitOAuth2ManageService;
  }

  @Override
  public void deleteConsumerApplication(String consumerId, String ApplicationId) {

    try {
      retrofitOAuth2ManageService.deleteConsumerApplication(consumerId, ApplicationId).execute();
    } catch (IOException e) {
      throw new KongClientException(e.getMessage());
    }
  }

  public Application createConsumerApplication(String consumerId, Application request) {
    try {
      Response<Application> res = retrofitOAuth2ManageService.createConsumerApplication(consumerId, request).execute();
      if (res.code() == 200 || res.code() == 201) {
        return res.body();
      }
      throw new KongClientException("Could not create new consumer Application", res.code(), res.message());
    } catch (Exception e) {
      throw new KongClientException(e.getMessage());
    }
  }

  public Application getConsumerApplication(String consumerId, String applicationId) {
    return application;
  }

  public Application updateConsumerApplication(String consumerId, String applicationId, Application request) {
    return application;
  }

  public ApplicationList listConsumerApplications(String consumerId) {
    try {
      Response<ApplicationList> res = retrofitOAuth2ManageService.listConsumerApplications(consumerId).execute();
      if (res.code() == 200 || res.code() == 201) {
        return res.body();
      }
      throw new KongClientException("Could not fetch ApplicationList", res.code(), res.message());
    } catch (Exception e) {
      throw new KongClientException(e.getMessage());
    }
  }

  public ApplicationList listClientApplications(String applicationId, String applicatonName, String clientId, String
    clientSecret, String consumerId) {
    return applicationList;
  }

  public Token createToken(Token request) {
    return token;
  }

  public Token getToken(String tokenId) {
    return token;
  }

  public Token updateToken(String tokenId, Token request) {
    return token;
  }

  public TokenList listTokens() {
    return tokenList;
  }
}
