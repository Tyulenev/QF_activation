package com.QSystems.quick_flow_registration.DAO;











import com.QSystems.quick_flow_registration.entity.LicenceData;

import java.util.List;

public interface LicenceDataDAO {
    public List<LicenceData> getLicenceTable();

    public void saveLicenceData(LicenceData ld);
    public void clearLicenceTable();
//
//    public Employee getLicenceData(int id);
//
//    public void deleteLicenceData(int id);
}
