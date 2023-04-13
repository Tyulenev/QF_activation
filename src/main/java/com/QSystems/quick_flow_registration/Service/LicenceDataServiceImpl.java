package com.QSystems.quick_flow_registration.Service;



import com.QSystems.quick_flow_registration.Additional.Decoder;
import com.QSystems.quick_flow_registration.Additional.OSread;
import com.QSystems.quick_flow_registration.DAO.LicenceDataDAO;
import com.QSystems.quick_flow_registration.entity.LicenceData;
import com.QSystems.quick_flow_registration.exceptionHandling.NoSuchLicenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LicenceDataServiceImpl implements LicenceDataService {

    @Autowired
    private LicenceDataDAO licenceDataDAO;

    @Override
    @Transactional
    public List<LicenceData> getLicenceTable(){
        return licenceDataDAO.getLicenceTable();
    }

    @Override
    @Transactional
    public void saveLicenceData(LicenceData ld) {
        licenceDataDAO.saveLicenceData(ld);
    }

    @Override
    @Transactional
    public void deleteLicenceData() {
        licenceDataDAO.clearLicenceTable();
    }

    @Override
    public String checkLicenceData() {
        Decoder dec1 = new Decoder();
        String encryptedData = null;
//        dec1.decrypt(encryptedData);
        List<LicenceData> ld = licenceDataDAO.getLicenceTable();
        if (ld.size()==1) {
            encryptedData = ld.get(0).getValue();
        }
        try {
            if (ld.size()!=1)
                throw new NoSuchLicenceException("\n" +
                        "Licensing table is broken (number of entries != 1). Perform activation again.");
            if (!OSread.getDataForRegistration().equals(dec1.decrypt(encryptedData))) {
                throw new NoSuchLicenceException("Licences data is incorrect. Please re-enter your licence key.");
            } else return "Licence is OK";
        } catch (Exception e) {
            throw new NoSuchLicenceException("Decryption failed");
        }
    }

}
