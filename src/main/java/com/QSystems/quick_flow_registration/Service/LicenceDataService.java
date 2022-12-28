package com.QSystems.quick_flow_registration.Service;












import com.QSystems.quick_flow_registration.entity.LicenceData;

import java.util.List;

public interface LicenceDataService {
    public List<LicenceData> getLicenceTable();
    public void saveLicenceData(LicenceData ld);

    public void testDeleteData();
//    public void saveEmployee(LicenceData emp);
//
//    public LicenceData getEmployee(int id);
//    public void deleteEmp(int id);
}
