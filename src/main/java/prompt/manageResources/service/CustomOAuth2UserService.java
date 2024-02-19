package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import prompt.manageResources.model.dto.oauth.CustomOAuth2User;
import prompt.manageResources.model.dto.oauth.GoogleResponse;
import prompt.manageResources.model.dto.oauth.OAuth2Response;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.model.entity.Mileage;
import prompt.manageResources.model.enums.Auth;
import prompt.manageResources.model.enums.Dept;
import prompt.manageResources.model.enums.Position;
import prompt.manageResources.repository.AccountRepository;
import prompt.manageResources.repository.MileageRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final AccountRepository accountRepository;
    private final MileageRepository mileageRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        //google oauth 일경우.
        if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
            boolean isPromptUser = oAuth2Response.getEmail().contains("@promptech.co.kr");
            if (!isPromptUser) {
                throw new SecurityException("Not prompt user: ".concat(oAuth2Response.getEmail()));
            }
        }

        String userName = oAuth2Response.getProviderId();
        Account account = accountRepository.findByUserName(userName);
        // 리팩토링 필요
        //최초 로그인일 경우
        if (account == null) {

            String email = oAuth2Response.getEmail();
            String name = oAuth2Response.getName();
            Auth auth = Auth.ADMIN;
            Mileage mileage = new Mileage();
            mileageRepository.save(mileage);

            Account newAccount = new Account();
            newAccount.setUserName(userName);
            newAccount.setName(name);
            newAccount.setEmail(email);
            newAccount.setMileage(mileage);
            newAccount.setAuth(auth);
            newAccount.setDept(Dept.DEV);
            newAccount.setPosition(Position.ASSISTANT);
            newAccount.setPassword("$2a$10$EO.Dyc1i8NMbMNcu2qn0eeTVFE1Ppj6ZX7eT8mNQCRqzslWxIocIG");

            account = accountRepository.save(newAccount);
        } else {

        }


        return new CustomOAuth2User(oAuth2Response, account.getAuth().toString());
        //추후 작성
    }
}
