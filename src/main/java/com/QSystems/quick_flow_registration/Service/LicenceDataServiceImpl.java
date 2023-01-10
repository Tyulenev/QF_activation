package com.QSystems.quick_flow_registration.Service;



import com.QSystems.quick_flow_registration.Additional.OSread;
import com.QSystems.quick_flow_registration.DAO.LicenceDataDAO;
import com.QSystems.quick_flow_registration.entity.LicenceData;
import com.QSystems.quick_flow_registration.exceptionHandling.NoSuchLicenceException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String checkLicenceData(String encryptedData) {
        if (!OSread.getMatherBoardNumber().equals(encryptedData)) {
            throw new NoSuchLicenceException("Licences data is incorrect. Please re-enter your licence key.");
        }
        else return "Licence is OK";
    }

    //    @Override
//    @Transactional
//    public void saveEmployee(LicenceData emp) {
//        employeeDAO.saveEmployee(emp);
//    }
//
//    @Override
//    @Transactional
//    public LicenceData getEmployee(int id) {
//        return employeeDAO.getEmployee(id);
//    }
//
//    @Override
//    @Transactional
//    public void deleteEmp(int id) {
//        employeeDAO.deleteEmp(id);
//    }
}
