package library.web.controllers;

import java.util.List;

import library.service.StdDocRegister;
import library.service.StdDocRegisterRecord;
import library.service.StdDocService;
import library.web.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("docs")
@SessionAttributes("reg_row_plh")
public class DocRegisterController {

	@Autowired
	private StdDocRegister docRegister;
	@Autowired
	private StdDocService docService;
	
	@SuppressWarnings("unused")
	@ModelAttribute("reg_row_plh")
	private PagedListHolder<StdDocRegisterRecord> prepareRegRowPLH() {
		PagedListHolder<StdDocRegisterRecord> plh = new PagedListHolder<StdDocRegisterRecord>();
		plh.setSource(docRegister.getRows());
		// plh.setPageSize(2);
		return plh;
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String list(
	        @ModelAttribute("reg_row_plh") PagedListHolder<StdDocRegisterRecord> plh,
	        @RequestParam(value = "update", defaultValue = "false") boolean update) {

		
		plh.setSource(docRegister.getRows());
		
		return "doc.register";
	}
	
	@RequestMapping(value = "register/filter", method = RequestMethod.POST)
	public String filter(@RequestParam(value="doc_code", defaultValue="") String docCode,
			@RequestParam(value="doc_name", defaultValue="") String name,
			@ModelAttribute("reg_row_plh") PagedListHolder<StdDocRegisterRecord> rrPlh){
		
		rrPlh.setSource(docRegister.filter(name, docCode));	
		return "doc.register";
	}
	
	@RequestMapping(value = "register/get_page", method = RequestMethod.POST)
	public String getPage(@ModelAttribute("reg_row_plh") PagedListHolder<StdDocRegisterRecord> rrPlh) {
		return "doc.register";
	}
	
	@RequestMapping(value = "register/delete", method = RequestMethod.POST)
	public String deleteVersions(
			@ModelAttribute("reg_row_plh") PagedListHolder<StdDocRegisterRecord> regRowPlh,
			@RequestParam("selected") List<Boolean> selectFlags) {
		
		List<StdDocRegisterRecord> selected = 
			Utils.getSelected(selectFlags, regRowPlh.getPageList());
		
		docService.deleteCards(docRegister.cardsFromRecords(selected));
		regRowPlh.getSource().removeAll(selected);
		
		return "doc.register";
	}
	
	@ExceptionHandler(CannotGetJdbcConnectionException.class)
	public ModelAndView handleJdbcConnectException(
			CannotGetJdbcConnectionException e) {
		ModelAndView mnv = new ModelAndView("doc.register.exception");
		mnv.addObject("message", "Не удается соединиться с базой данных.");
		return mnv;
	}
}
