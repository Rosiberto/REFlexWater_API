package com.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.annotations.ApiIgnore;


/**
 * 
 * @author Rosiberto
 * @category Controller
 * @apiNote Controller class that routes views
 * @version 2020.06.1
 *
 */


/*Para trabalhar com @RestController o retorno de um método 
 * para exibir uma página HTML deve ser do tipo ModelAndView 
 * 
 * Exemplo:
 * 
 * @GetMapping("/cep")
 * public ModelAndView b(){
 * 	ModelAndView mv = new ModelAndView("pages/editorcep");
 * 	return mv;
 * 	}
 */

@Controller
@RequestMapping(value = "/")
@ApiIgnore
public class ViewController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String goIndex(){
		return "index";		
	}
	
	@RequestMapping(value = "subscription", method = RequestMethod.GET)
	public String goSubscription(){
		return "pages/assinatura";		
	}
	
	@RequestMapping(value = "cep", method = RequestMethod.GET)
	public String goEditorCEP(){
		return "pages/editorcep";		
	}

	@RequestMapping(value = "event", method = RequestMethod.GET)
	public String goEvent(){
		return "pages/evento";		
	}
	
	@RequestMapping(value = "notify", method = RequestMethod.GET)
	public String goNotify(){
		return "pages/notificacao";		
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String goLogin(){		
		return "login";		
	}
	
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String goRegister(){
		return "register";		
	}
		
	@RequestMapping(value = "contact", method = RequestMethod.GET)
	public String goContact(){
		return "contatos";
	}
}
