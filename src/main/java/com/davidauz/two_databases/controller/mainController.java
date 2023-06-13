package com.davidauz.two_databases.controller;

import com.davidauz.two_databases.entity.mssqlentity.mssqlTable;
import com.davidauz.two_databases.repository.mssqlRepos.mssqlTableRepo;
import com.davidauz.two_databases.repository.mysqlRepos.mysqlTableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
//@Slf4j
public class mainController {

    @Autowired
    mssqlTableRepo mssqltablerepo;

    @Autowired
    mysqlTableRepo usersRepository;


    @GetMapping("/test")
    public String test_method() {
        String strToRet="";
        strToRet="<p>begin</p>";
        mssqlTable mss=new mssqlTable();
        mss.setColumnVarchar("HELLO");
        mssqltablerepo.save(mss);
        List<mssqlTable> mssql_objects= mssqltablerepo.findAll();
        for(mssqlTable msst : mssql_objects)
            strToRet = strToRet+"<p>"+msst.toString()+"</p>";
        strToRet = strToRet+"<p></p>end.<p>";
        return strToRet;
    }

}
