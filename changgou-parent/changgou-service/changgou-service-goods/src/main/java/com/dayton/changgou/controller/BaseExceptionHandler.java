package com.dayton.changgou.controller;

import entity.Result;
import entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 * @author Martin Deng
 * @since 2020-09-18 22:14
 */
@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

	/**
	 * 全局异常处理
	 * @param e	    异常
	 * @return entity.Result
	 * @author Martin Deng
	 * @since 2020/9/18 22:16
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result error(Exception e){
		log.error(e.getMessage());
		return new Result(false, StatusCode.ERROR, e.getMessage());
	}

}
