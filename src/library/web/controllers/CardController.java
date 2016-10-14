package library.web.controllers;

import java.util.List;

import library.model.DocVersion;
import library.model.StandartCard;
import library.service.StdDocService;
import library.validation.StandartCardValidator;
import library.web.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Контроллер создания/редактирования карточки документа.
 * 
 * @author Andrey Kukushkin
 *
 */
@Controller
@RequestMapping("docs/card")
@SessionAttributes("ver_plh")
public class CardController {
	
	@Autowired
	private StandartCardValidator scValidator;
	
	@Autowired
	StdDocService sdocService;
	
	@SuppressWarnings("unused")
    @ModelAttribute("ver_plh")
	private PagedListHolder<DocVersion> prepareVerPLH(
			@RequestParam(value = "card_id", required = false) Long cardId) {
		PagedListHolder<DocVersion> plh = 
			new PagedListHolder<DocVersion>();
		if(cardId != null) {
			plh.setSource(sdocService.findDocVersionsByCardId(cardId));
			plh.setPageSize(2);
		}
		return plh;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView addCard() {
		
		StandartCard sc = new StandartCard();
		sc.setRegNum(sdocService.getNextRegNum());
		
		ModelAndView mnv = new ModelAndView("doc.card.edit");
		mnv.addObject("card", sc);
		
		return mnv;
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView editCard(@RequestParam("card_id") Long id,
			@ModelAttribute("ver_plh") PagedListHolder<DocVersion> vplh) {
		
		StandartCard sc = sdocService.findCard(id);
		
		vplh.setSource(sdocService.findDocVersionsByCardId(id));
		
		ModelAndView mnv = new ModelAndView("doc.card.edit");
		mnv.addObject("card", sc);
		
		return mnv;
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute("card") StandartCard card,
			BindingResult bRes) {
		
		scValidator.validate(card, bRes);
		if( bRes.hasErrors() ) {
			return "doc.card.edit";
		}
		
		sdocService.save(card);
		
		return "redirect:/docs/register.do?update=true";
	}
	
	@RequestMapping(value = "versions", method = RequestMethod.POST)
	public String getVersionsPage(@ModelAttribute("card") StandartCard card,
			@ModelAttribute("ver_plh") PagedListHolder<DocVersion> vplh) {
		
		return "doc.card.edit";
	}
	
	@RequestMapping(value = "versions/delete", method = RequestMethod.POST)
	public String deleteVersions(@ModelAttribute("card") StandartCard card,
			@ModelAttribute("ver_plh") PagedListHolder<DocVersion> vplh,
			@RequestParam("selected") List<Boolean> selectFlags) {
		
		List<DocVersion> selected = 
			Utils.getSelected(selectFlags, vplh.getPageList());
		
		sdocService.deleteVersions(selected);
		vplh.getSource().removeAll(selected);
		
		return "doc.card.edit";
	}
	
}