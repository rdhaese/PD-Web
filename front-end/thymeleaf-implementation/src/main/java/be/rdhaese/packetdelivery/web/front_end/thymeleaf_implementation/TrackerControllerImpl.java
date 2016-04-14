package be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation;

import be.rdhaese.packetdelivery.web.front_end.interfaces.TrackerController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 14/02/2016.
 *
 * @author Robin D'Haese
 */
@Controller
public class TrackerControllerImpl implements TrackerController {

    @Override
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String track(@RequestParam(required = false) String packetId, HttpServletRequest request ) {
        //TODO get packet from backend
        if ((packetId != null) && (!(packetId = packetId.trim()).isEmpty())) {
            request.getSession().setAttribute("packet", packetId);
        }
        return "index";
    }

    @Override
    @RequestMapping(value = "/clear-packet", method = RequestMethod.GET)
    public String clearPacket(HttpServletRequest request) {
        request.getSession().setAttribute("packet", null);
        return "index";
    }


}
