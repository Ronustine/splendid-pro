package com.ronustine.splendidpro.validation;

import com.alibaba.fastjson.JSON;
import com.ronustine.splendidpro.common.ResponseBean;
import com.ronustine.splendidpro.validation.entity.dto.ValidateRequestDTO;
import com.ronustine.splendidpro.validation.entity.dto.ValidateResult;
import com.ronustine.splendidpro.validation.service.ValidationFieldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类ValidateController的实现描述：校验
 *
 * @author ronustine
 */
@RequestMapping("validate")
@Controller
public class ValidateController {

    Logger log = LoggerFactory.getLogger(ValidateController.class);

    @Autowired
    ValidationFieldService validationFieldService;


    /**
     * 校验接口
     * @param validateRequestDTO
     * @return
     */
    @RequestMapping(value = "/go", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean operating(@RequestBody ValidateRequestDTO validateRequestDTO) {
        ValidateResult validateResult = validationFieldService.getVerifyResponseMsg(validateRequestDTO.getStandardCode(),
                validateRequestDTO.getInterfaceCode(), JSON.toJSONString(validateRequestDTO.getValidateData()));
        return ResponseBean.builder().success().result(validateResult).build();
    }



}
