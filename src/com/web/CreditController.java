package com.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*@RestController
@RequestMapping("/api/credit")*/
public class CreditController {

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String creditScoring() {
        return "new";
    }
}
