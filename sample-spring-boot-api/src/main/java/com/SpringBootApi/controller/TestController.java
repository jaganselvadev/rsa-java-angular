package com.SpringBootApi.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootApi.rsa.RSAUtil;

@RestController
@RequestMapping("/sample")
@CrossOrigin
public class TestController {
	
	//Sample API
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<?> apiTest(HttpServletResponse response, HttpServletRequest request) {

		Map<String, String> returnval = new HashMap<>();
		returnval.put("message", "ok");
		return new ResponseEntity<>(returnval, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getmethod/{name}/{password}", method = RequestMethod.GET)
	public ResponseEntity<?> apiTestGetMethod(@PathVariable("name") String name, @PathVariable("password") String password, HttpServletResponse response, HttpServletRequest request) {

		Map<String, String> returnval = new HashMap<>();
		returnval.put("name", name);
		returnval.put("password", password);
		return new ResponseEntity<>(returnval, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/postmethod", method = RequestMethod.POST)
	public ResponseEntity<?> apiTestPostMethod( @RequestParam("message") String message, HttpServletResponse response, HttpServletRequest request) {

		Map<String, String> returnval = new HashMap<>();
		returnval.put("message", message);
		return new ResponseEntity<>(returnval, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/postmethod1", method = RequestMethod.POST)
	public ResponseEntity<?> apiTestPostMethod1( @RequestBody Object objParam, HttpServletResponse response, HttpServletRequest request) {

		Map<String, String> returnval = new HashMap<>();
		returnval.put("message", "working..");
		return new ResponseEntity<>(returnval, HttpStatus.OK);
		
	}
	
	@PostMapping("/response")
    @ResponseBody
    public ResponseEntity<?> postResponseController(
      @RequestBody LoginForm loginForm) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		System.out.println(loginForm.getPassword());
		String plainTextPass = RSAUtil.decrypt(loginForm.getPassword());
		System.out.println(plainTextPass);
		Map<String, String> returnval = new HashMap<>();
		returnval.put("message", "working..");
		return new ResponseEntity<>(returnval, HttpStatus.OK);
     }
}
