package com.QSystems.quick_flow_registration.DAO;




import com.QSystems.quick_flow_registration.entity.LicenceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LicenceDataDAOImpl implements LicenceDataDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<LicenceData> getLicenceTable() {
        Query query = entityManager.createQuery("from LicenceData");
        List<LicenceData> licenceDataTable = query.getResultList();
        return licenceDataTable;
    }

    @Override
    public void saveLicenceData(LicenceData ld){
        LicenceData newEmp = entityManager.merge(ld);  //из JPA - это тоже самое что и saveOrUpdate из Hibernate
        ld.setId(newEmp.getId());
    }

    @Override
    public void clearLicenceTable(){
        Query query = entityManager.createQuery("delete from LicenceData");
        query.executeUpdate();
    }
//
//    @Override
//    public Employee getEmployee(int id) {
//            Employee emp = entityManager.find(Employee.class, id);
//        return emp;
//    }
//
//    @Override
//    public void deleteEmp(int id) {
//        Query query = entityManager.createQuery("delete from LicenceData " +
//                   "where id =:employeeId");
//                query.setParameter("employeeId", id);
//                query.executeUpdate();
//    }
}
