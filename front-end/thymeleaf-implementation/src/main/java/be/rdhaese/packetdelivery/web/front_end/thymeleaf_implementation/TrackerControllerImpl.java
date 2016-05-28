package be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.TrackerWebService;
import be.rdhaese.packetdelivery.dto.LocationUpdateDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.RemarkDTO;
import be.rdhaese.packetdelivery.web.front_end.interfaces.TrackerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 *
 * @author Robin D'Haese
 */
@Controller
public class TrackerControllerImpl implements TrackerController {

    private static final String ATTR_PACKET = "packet";
    private static final String ATTR_COMPANY_ADDRESS = "companyAddress";
    private static final String ATTR_PACKET_ADDRESS = "packetAddress";
    private static final String ATTR_LOCATION_UPDATES = "locationUpdates";
    private static final String ATTR_AMOUNT_OF_PACKETS = "amountOfPackets";
    private static final String ATTR_REMARKS = "remarks";
    private static final String ATTR_ERROR = "error";


    private static final String PAGE_INDEX = "index";

    @Autowired
    private TrackerWebService trackerService;

    @Override
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String track(@RequestParam(required = false) String packetId, HttpSession session, Model model) {
        if ((packetId != null) && (!(packetId = packetId.trim()).isEmpty())) {
            //If a packetId is present as parameter, put it on the session
            session.setAttribute(ATTR_PACKET, packetId);
        }

        //Get the packetId that is present on the session
        packetId = (String) session.getAttribute(ATTR_PACKET);

        if (packetId != null) {
            try {
                //Get packetdelivery address and put it on the request
                LongLatDTO companyAddress = trackerService.getCompanyAddress();
                model.addAttribute(ATTR_COMPANY_ADDRESS, companyAddress);

                //Get address for packet and put it on the request
                LongLatDTO packetAddress = trackerService.getPacketAddress(packetId);
                model.addAttribute(ATTR_PACKET_ADDRESS, packetAddress);

                //Get location updates for round where packet is part of and put them on request
                Collection<LocationUpdateDTO> locationUpdates = trackerService.getLocationUpdates(packetId);
                model.addAttribute(ATTR_LOCATION_UPDATES, locationUpdates);

                //Get remarks for round where packet is part of and put those on the request
                Collection<RemarkDTO> remarks = trackerService.getRemarks(packetId);
                model.addAttribute(ATTR_REMARKS, remarks);

                //Get amount of packets that are left before the tracked one and put it on the request
                model.addAttribute(ATTR_AMOUNT_OF_PACKETS, trackerService.getAmountOfPacketsLeftBefore(packetId));
            } catch (Exception e) {
                //Remove packet id from session
                session.setAttribute(ATTR_PACKET, null);

                //Set an error flag on the request
                model.addAttribute(ATTR_ERROR, true);
            }
        }
        return PAGE_INDEX;
    }

    @Override
    @RequestMapping(value = "/clear-packet", method = RequestMethod.GET)
    public String clearPacket(HttpSession session) {
        //Remove the packet id from the session
        session.setAttribute(ATTR_PACKET, null);
        return PAGE_INDEX;
    }
}
