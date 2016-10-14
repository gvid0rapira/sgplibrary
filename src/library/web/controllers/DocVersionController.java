package library.web.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import library.model.ContentType;
import library.model.DocContent;
import library.model.DocSendRecord;
import library.model.DocVersion;
import library.model.StandartCard;
import library.service.StdDocService;
import library.validation.DocVersionValidator;
import library.web.utils.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Контроллер создания/редактирования версии документа.
 * 
 * @author Andrey Kukushkin
 *
 */
@Controller
@RequestMapping("docs/version")
@SessionAttributes("send_recs_plh")
public class DocVersionController {
	
	Log log = LogFactory.getLog(DocVersionController.class);
	
	@Autowired
	StdDocService docService;
	@Autowired
	DocVersionValidator dvValidator;
	
	@SuppressWarnings("unused")
    @ModelAttribute("send_recs_plh")
	private PagedListHolder<DocSendRecord> prepareMailedDepsPLH(
			@RequestParam(value = "ver_id", required = false) Long versionId) {
		PagedListHolder<DocSendRecord> plh = 
			new PagedListHolder<DocSendRecord>();
		if(versionId != null) {
			plh.setSource(docService.getSendRecordsForVersion(versionId));
			plh.setPageSize(2);
		}
		return plh;
	}
	
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam("card_id") Long cardId) {
		
		StandartCard sc = docService.findCard(cardId);
		DocVersion dv = new DocVersion();
		dv.setApproDate( new Date() );
		dv.setCard(sc);
		
		ModelAndView mnv = new ModelAndView("doc.version.edit");
		mnv.addObject("version", dv);
		
		return mnv;
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam("ver_id") Long verId,
			@ModelAttribute("send_recs_plh") PagedListHolder<DocSendRecord> recPlh) {
		
		DocVersion dv = docService.findDocVersion(verId);
		
		ModelAndView mnv = new ModelAndView("doc.version.edit");
		mnv.addObject("version", dv);
		
		recPlh.setSource(docService.getSendRecordsForVersion(verId));
		return mnv;
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute("version") DocVersion v,
			BindingResult br,
			@RequestParam("file") MultipartFile file) throws IOException {
		
		dvValidator.validate(v, br);
		
		if( br.hasErrors() ) {
			return "doc.version.edit";
		}
		
		DocContent cnt = new DocContent();
		
		if( v.getId() == null ) {
			if (file.isEmpty()) {
				br.rejectValue("content", "doc.content.empty", 
						"Необходимо выбрать файл при добавлении версии.");
				log.info("Необходимо выбрать файл при добавлении версии.");
			}
		} else {
			// Достаем существующий контент.
			cnt = docService.findContentByVersionId(v.getId());
			byte[] fileContent = docService.getFileContent(cnt.getId());
			cnt.setContent(fileContent);
			if( cnt == null ) {
				throw new RuntimeException(
						String.format("Содержимоет версии c id %s равно null.", 
								v.getId()));
			}
		}
		
		// Если файл пришел.
		if( !file.isEmpty()) {
			
			ContentType ct =  docService.getContentTypeByMime( 
					file.getContentType() );
			if (ct == null) {
				br.rejectValue("content", "content_type.not_supported", 
					"Не поддерживаемый тип документа.");
				log.info("Не поддерживаемый тип данных.");
			}
			
			cnt.setCntType(ct);
			
			cnt.setContent(file.getBytes());
			
			
			cnt.setFileName(file.getOriginalFilename());
		
		}
		// Проверка на ошибки в логике.
		if( br.hasErrors() ) {
			return "doc.version.edit";
		}
		
		v.setContent(cnt);
		
		docService.save(v);
		
		return "redirect:/docs/card/edit.do?card_id=" + v.getCard().getId();
	}
	
	@RequestMapping(value = "download", method = RequestMethod.GET)
	@ResponseBody
	public void downloadFile(@RequestParam( "version_id" ) Long versionId,
			HttpServletResponse response) throws IOException {
		
		DocContent content = docService.findContentByVersionId(versionId);
		byte[] file = docService.getFileContent(versionId);
		response.setContentType(content.getCntType().getMime());
		response.setHeader("Content-disposition", 
				"attachment; filename=file." + content.getCntType().getExt());
		
		
		ServletOutputStream out = response.getOutputStream();
		out.write(file);
		
		out.flush();
	}
	
	@RequestMapping(value = "send_records/get_page", method = RequestMethod.POST)
	public String getVersionsPage(@ModelAttribute("version") DocVersion version,
			@ModelAttribute("send_recs_plh") PagedListHolder<DocSendRecord> srecPlh) {
		
		return "doc.version.edit";
	}
	@RequestMapping(value = "send_records/delete", method = RequestMethod.POST)
	public String deleteSendRecords(@ModelAttribute("version") DocVersion version,
			@ModelAttribute("send_recs_plh") PagedListHolder<DocSendRecord> srecPlh,
			@RequestParam("selected") List<Boolean> selectFlags) {
		
		List<DocSendRecord> selRecs = 
			Utils.getSelected(selectFlags, srecPlh.getPageList());
		
		docService.deleteSendRecords(selRecs);
		
		return "redirect:/docs/version/edit.do?ver_id=" + version.getId();
	}
	
}