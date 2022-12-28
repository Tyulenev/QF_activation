package com.QSystems.quick_flow_registration.Controller;



import com.QSystems.quick_flow_registration.OSread.OSread;
import com.QSystems.quick_flow_registration.Service.LicenceDataService;
import com.QSystems.quick_flow_registration.entity.LicenceData;
import com.QSystems.quick_flow_registration.exceptionHandling.NoSuchLicenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private LicenceDataService licenceDataService;

//    @ResponseBody
    @RequestMapping(value = "/licence-info", method = RequestMethod.GET)
    public List<LicenceData> showLicenceInfo() throws Exception {
        List<LicenceData> licenceData = licenceDataService.getLicenceTable();
        if (licenceData.size()!=1) {
            throw new NoSuchLicenceException("Требуется регистрация продукта QuickFlow" +
                    ". (*Current table size - " + licenceData.size() + ")");
        }

        return licenceData;
    }

    @GetMapping("/get-data-for-registration")
    public String getDataForReg() {
        return OSread.getMatherBoardNumber();
    }

    @PostMapping("/add-licence-data")
    public LicenceData addLicenceData(@RequestBody LicenceData licData) {
        licData.setParamName("Enc_pswd");
        licenceDataService.saveLicenceData(licData);
        return licData;
    }


//    @GetMapping("/employees/{id}")
//    public LicenceData getEmployee(@PathVariable int id) {
//        LicenceData emp = employeeService.getEmployee(id);
//
//        return emp;
//    }
//
//    @PostMapping("/add-employee")
//    public LicenceData addNewEmployee(@RequestBody LicenceData emp) {
//        employeeService.saveEmployee(emp);
//        return emp;
//    }
//
//    @DeleteMapping("/del-employee/{id}")
//    public String deleteEmps (@PathVariable int id) {
//        employeeService.deleteEmp(id);
//        return "LicenceData with id = " + id + " was deleted";
//    }

    @GetMapping("/test1")
    public String test1 () {
        return "test1 !!!!!";
    }

    @DeleteMapping("/test-delete-all")
    public String testDelAll () {
        licenceDataService.testDeleteData();
        return "Delete OK!";
    }

}
