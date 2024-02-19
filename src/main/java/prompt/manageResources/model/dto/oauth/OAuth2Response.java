package prompt.manageResources.model.dto.oauth;

public interface OAuth2Response {

    //Outh 종류 (google, naver .. etc)
    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();
}
