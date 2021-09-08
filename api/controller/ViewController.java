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

	/***************************** WEB ***********************************/
	@RequestMapping(value = "accessdenied", method = RequestMethod.GET)
	public String accessdenied(){		
		return "pages/error/accessdenied";		
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
		return "contact";
	}

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

	@RequestMapping(value = "statisticp", method = RequestMethod.GET)
	public String goStatisticP(){
		return "pages/estatisticaPump";		
	}

	@RequestMapping(value = "statisticr", method = RequestMethod.GET)
	public String goStatisticR(){
		return "pages/estatisticaReservoir";		
	}

	@RequestMapping(value = "statisticv", method = RequestMethod.GET)
	public String goStatisticV(){
		return "pages/estatisticaValve";		
	}

	@RequestMapping(value = "statistict", method = RequestMethod.GET)
	public String goStatisticT(){
		return "pages/estatisticaTank";		
	}

	@RequestMapping(value = "monitoring", method = RequestMethod.GET)
	public String goMonitoring(){
		return "pages/monitoramento";		
	}
	/*****************************  ***********************************/

	
	/***************************** MOBILE *****************************/
	@RequestMapping(value = "mobile/accessdenied", method = RequestMethod.GET)
	public String accessDenied(){		
		return "pages/error/accessdenied-mobile";		
	}
	
	//@RequestMapping(value = "mobile/register", method = RequestMethod.GET)
	//public String goRegisterMobile(){
	//	return "mobile/registerMobile";		
	//}
	
	@RequestMapping(value = "mobile/login", method = RequestMethod.GET)
	public String goLoginMobile(){		
		return "mobile/login-mobile";		
	}
	
	@RequestMapping(value = "mobile/logout", method = RequestMethod.GET)
	public String goLogoutMobile(){		
		return "mobile/login-mobile";		
	}
	
	@RequestMapping(value = "mobile/page", method = RequestMethod.GET)
	public String goMobile(){
		return "mobile/index";		
	}
	
	@RequestMapping(value = "mnotify", method = RequestMethod.GET)
	public String goMobileNotify(){
		return "mobile/mnotificacao";		
	}
	
	@RequestMapping(value = "mstatisticp", method = RequestMethod.GET)
	public String goMobileStatisticP(){
		return "mobile/mestatisticaPump";		
	}

	@RequestMapping(value = "mstatisticr", method = RequestMethod.GET)
	public String goMobileStatisticR(){
		return "mobile/mestatisticaReservoir";		
	}

	@RequestMapping(value = "mstatisticv", method = RequestMethod.GET)
	public String goMobileStatisticV(){
		return "mobile/mestatisticaValve";		
	}

	@RequestMapping(value = "mstatistict", method = RequestMethod.GET)
	public String goMobileStatisticT(){
		return "mobile/mestatisticaTank";		
	}

	@RequestMapping(value = "mmonitoring", method = RequestMethod.GET)
	public String goMobileMonitoring(){
		return "mobile/mmonitoramento";		
	}
	/*****************************  ***********************************/
	
	

}
