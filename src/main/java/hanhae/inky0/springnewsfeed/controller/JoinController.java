package hanhae.inky0.springnewsfeed.controller;

import hanhae.inky0.springnewsfeed.dto.JoinDTO;
import hanhae.inky0.springnewsfeed.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO){
        joinService.joinProcess(joinDTO);
        return "ok";
    }
}
