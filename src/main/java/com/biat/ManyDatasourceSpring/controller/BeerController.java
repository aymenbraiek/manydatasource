package com.biat.ManyDatasourceSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/biat")
public class BeerController {
    private final static String query = "select * from beer_inventory where version=?";
    private final static String querybeer = "secel * from beer where beer_name=? ";
    private List<Map<String, Object>> listeallbeer = new ArrayList<>();
    @Autowired
    @Qualifier("BeerinvJdbcTemplate")
    private JdbcTemplate jdbcTemplatebeerinventory;
    @Autowired
    @Qualifier("BeerJdbcTemplate")
    private JdbcTemplate jdbcTemplatebeer;

    @GetMapping("/getallbeerinventory")
    public List<Map<String, Object>> getallbeerinventory(@RequestParam("version") BigInteger version) {
        listeallbeer = jdbcTemplatebeerinventory.queryForList(query, version);
        return listeallbeer;
    }

    @GetMapping("/getallbeer")
    public List<Map<String, Object>> getallbeer(@RequestParam("beer_name") String beer_name) {
        listeallbeer = jdbcTemplatebeer.queryForList(querybeer, beer_name);
        return listeallbeer;
    }

}
