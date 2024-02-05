package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.model.entity.Mileage;
import prompt.manageResources.repository.MileageRepository;

@Service
@RequiredArgsConstructor
public class MileageService {

    private final MileageRepository mileageRepository;

    public Mileage initSave(Account account) {
        Mileage mileage = new Mileage();
        mileage.setAccount(account);
        return mileageRepository.save(mileage);
    }



}
