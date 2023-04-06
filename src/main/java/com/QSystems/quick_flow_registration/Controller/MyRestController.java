package com.QSystems.quick_flow_registration.Controller;



import com.QSystems.quick_flow_registration.Additional.Decoder;
import com.QSystems.quick_flow_registration.Additional.FileWriter;
import com.QSystems.quick_flow_registration.Additional.OSread;
import com.QSystems.quick_flow_registration.Service.LicenceDataService;
import com.QSystems.quick_flow_registration.entity.LicenceData;
import com.QSystems.quick_flow_registration.exceptionHandling.NoSuchLicenceException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MyRestController {

    @Autowired
    private LicenceDataService licenceDataService;
    @Value("${FILE_NAME}")
    private String FILE_NAME;
    @Value("${FILE_DIST}")
    private String FILE_DIST;

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
    public String getDataForReg() throws IOException {
        String strForReg = OSread.getMatherBoardNumber();
        FileWriter.writeFile(strForReg, FILE_DIST, FILE_NAME);
        return strForReg;
    }

    @PostMapping("/add-licence-data")
    public LicenceData addLicenceData(@RequestBody LicenceData licData) {
        licenceDataService.deleteLicenceData();
        licData.setParamName("Enc_pswd");
        licenceDataService.saveLicenceData(licData);
        return licData;
    }

    @GetMapping("/is-licences-ok")
    public String isLicenceOk() {
        return licenceDataService.checkLicenceData();
    }

//    Additions
    @GetMapping("/get-os-info")
    public String fetOSInfo() {
        return OSread.getOSName();
    }


    @SneakyThrows
    @GetMapping("/test-decrypt")
    public String testDecrypt123 (@RequestBody String encryptedData) {
        Decoder dec1 = new Decoder();
        return dec1.decrypt(encryptedData);
    }

    @DeleteMapping("/delete-all")
    public String testDelAll () {
        licenceDataService.deleteLicenceData();
        return "Licence data deleted. Licences table is Empty!";
    }

}
