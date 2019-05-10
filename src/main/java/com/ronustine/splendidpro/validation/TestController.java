package com.ronustine.splendidpro.validation;

import com.ronustine.splendidpro.common.ResponseBean;
import com.ronustine.splendidpro.validation.service.ValidateDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ForkJoinTask;

/**
 * 类ValidateController的实现描述：校验
 *
 * @author ronustine
 */
@RequestMapping("test")
@Controller
public class TestController {

    Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    ValidateDetailService validateDetailService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean operating(Long id) {

        return ResponseBean.builder().success().result(validateDetailService.getById(id)).build();
    }

}
