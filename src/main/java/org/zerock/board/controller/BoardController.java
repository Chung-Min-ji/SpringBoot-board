package org.zerock.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.service.BoardService;

@Controller
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @GetMapping("/list")
    public void list(@ModelAttribute("reqDTO") PageRequestDTO reqDTO, Model model){
        log.info("list({}, model) invoked....", reqDTO);

        model.addAttribute("result", service.getList(reqDTO));
    }

    @GetMapping("/register")
    public void register(){
        log.info("register Get...");
    }

    @PostMapping("/register")
    public String regsterPost(BoardDTO dto, RedirectAttributes rttrs){
        log.info("registerPost({}, rttrs) invoked...", dto);

        Long bno = service.register(dto);
        rttrs.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@ModelAttribute("reqDTO") PageRequestDTO reqDTO, Long bno, Model model) {
        log.info("read({}, {}, model) invoked.", reqDTO, bno);

        BoardDTO boardDTO = service.get(bno);
        log.info("\t + boardDTO : {}", boardDTO);

        model.addAttribute("dto", boardDTO);
    }

    @PostMapping("/remove")
    public String remove(long bno, RedirectAttributes rttrs){
        log.info("remove({}, rttrs) invoked...", bno);

        service.removeWithReplies(bno);

        rttrs.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    }

    @PostMapping("/modify")
    public String modify(BoardDTO dto,
                         @ModelAttribute("reqDTO") PageRequestDTO reqDTO,
                         RedirectAttributes rttrs){
        log.info("modify({}, {}, rttrs) invoked....", dto, reqDTO);

        service.modify(dto);

        rttrs.addAttribute("page", reqDTO.getPage());
        rttrs.addAttribute("type", reqDTO.getType());
        rttrs.addAttribute("keyword", reqDTO.getKeyword());

        rttrs.addAttribute("bno", dto.getBno());

        return "redirect:/board/read";
    }
}
