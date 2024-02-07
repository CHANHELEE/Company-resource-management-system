package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.model.entity.Mileage;
import prompt.manageResources.model.entity.MileageHist;
import prompt.manageResources.repository.MileageHistRepository;
import prompt.manageResources.repository.MileageRepository;

@Service
@RequiredArgsConstructor
public class MileageService {

    private final MileageRepository mileageRepository;
    private final MileageHistRepository mileageHistRepository;

//    public Mileage initSave(Account account) {
//        Mileage mileage = new Mileage();
//        mileage.setAccount(account);
//        return mileageRepository.save(mileage);
//    }

//    public Mileage saveMileageAndHist() {
//        Mileage mileage = new Mileage();
//        mileage = mileageRepository.save(mileage);
//
//        MileageHist mileageHist = new MileageHist();
//        mileageHist.set
//        mileageHist.set
//        mileageHistRepository.save(mileageRepository.save(mileage));
//    }

    public Mileage save(Mileage mileage) {
        return mileageRepository.save(mileage);
    }



}
