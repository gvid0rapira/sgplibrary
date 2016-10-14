package library.web.controllers;

import java.util.List;

import library.dao.IDepartmentDAO;
import library.model.Department;
import library.model.DocVersion;
import library.service.DepartmentService;
import library.service.StdDocService;
import library.web.utils.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *  онтроллер функции рассылки.
 * 
 * @author Andrey Kukushkin.
 *
 */
@Controller
@RequestMapping("docs/send_record")
@SessionAttributes("dep_plh")
public class SendRecordController {
	
	Log log = LogFactory.getLog(SendRecordController.class);
	
	@Autowired
	DepartmentService depService;
	@Autowired
	StdDocService docService;
	
	@SuppressWarnings("unused")
	@ModelAttribute("dep_plh")
    private PagedListHolder<Department> prepareDepPLH() {
		PagedListHolder<Department> plh 
			= new PagedListHolder<Department>(
					depService.getAllDeps()
					);
		
		plh.setPageSize(2);
		return plh;
	}
	
	@RequestMapping(value = "select_dep", method = RequestMethod.GET)
	public String  selectDepartment(
			@RequestParam("version_id") Long versionId,
			Model model) {
		// ƒл€ сохранени€ между запросами id отсылаемой версии документа,
		// фиктивна€ верси€ документа с инициализированным ID добавл€етс€
		// в модель.
		DocVersion dv = new DocVersion();
		dv.setId(versionId);
		model.addAttribute("version", dv);
		
		return "doc.send_record.select_dep";
	}
	
	@RequestMapping(value = "dep_list_page", method = RequestMethod.POST)
	public String getDepListPage(
			@ModelAttribute("version") DocVersion version,
			@ModelAttribute("dep_plh") PagedListHolder<Department> depPlh) {
		
		return "doc.send_record.select_dep";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addSendRecord(@ModelAttribute("version") DocVersion version,
			@ModelAttribute("dep_plh") PagedListHolder<Department> depPlh,
			@RequestParam("selected") List<Boolean> selectFlags) {
		
		List<Department> selectedDeps =
			Utils.getSelected(selectFlags, depPlh.getPageList());
		
		docService.addSendRecord(version, selectedDeps);
		
		return "redirect:/docs/version/edit.do?ver_id=" + version.getId();
	}
}
