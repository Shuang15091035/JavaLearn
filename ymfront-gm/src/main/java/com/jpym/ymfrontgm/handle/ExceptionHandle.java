package com.jpym.ymfrontgm.handle;

import com.jpym.ymfrontgm.exception.YmfrontGmException;
import com.jpym.ymfrontgm.util.MsgConstant;
import com.jpym.ymfrontgm.util.Result;
import com.jpym.ymfrontgm.util.ResultUtil;
import com.jpym.ymfrontgm.util.StringUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler( value = Exception.class )
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof YmfrontGmException) {
            YmfrontGmException ye = (YmfrontGmException) e;
            return ResultUtil.error(ye.getCode(), ye.getMessage());
        }
        return ResultUtil.error(MsgConstant.ERROR_CODE, StringUtil.empty(e.getMessage()) ? "" : e.getMessage());
    }
}
