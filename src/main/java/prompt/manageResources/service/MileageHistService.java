package prompt.manageResources.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prompt.manageResources.model.entity.Account;
import prompt.manageResources.model.entity.MileageHist;
import prompt.manageResources.model.enums.mileage.UpdateReason;
import prompt.manageResources.repository.MileageHistRepository;

@Service
@RequiredArgsConstructor
public class MileageHistService {

    private final MileageHistRepository mileageHistRepository;

//    public MileageHist initSave(Account account) {
//        MileageHist mileageHist = new MileageHist();
//        mileageHist.setAccount(account);
//        mileageHist.setUpdateReason(UpdateReason.NEW);
//        return mileageHistRepository.save(mileageHist);
//    }

    public MileageHist save(MileageHist mileageHist) {
        return mileageHistRepository.save(mileageHist);
    }

}
