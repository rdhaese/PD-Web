package be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.TrackerWebService;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LocationUpdateDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.RemarkDTO;
import be.rdhaese.packetdelivery.web.front_end.interfaces.TrackerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created on 14/02/2016.
 *
 * @author Robin D'Haese
 */
@Controller
public class TrackerControllerImpl implements TrackerController {

    private static final String ATTR_PACKET = "packet";
    private static final String ATTR_COMPANY_ADDRESS = "companyAddress";
    private static final String ATTR_PACKET_ADDRESS = "packetAddress";
    private static final String ATTR_LOCATION_UPDATES = "locationUpdates";
    private static final String ATTR_REMARKS = "remarks";
    private static final String ATTR_ERROR = "error";

  @Autowired
    private TrackerWebService trackerService;

    @Override
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String track(@RequestParam(required = false) String packetId, HttpServletRequest request ) {
        if ((packetId != null) && (!(packetId = packetId.trim()).isEmpty())) {
            //If a packetId is present as parameter, put it on the session
            request.getSession().setAttribute(ATTR_PACKET, packetId);
        }

        //Get the packetId that is present on the session
        packetId = (String) request.getSession().getAttribute(ATTR_PACKET);

        if (packetId != null){
            try {
                //Get packetdelivery address and put it on the request
                LongLatDTO companyAddress = trackerService.getCompanyAddress();
                request.setAttribute(ATTR_COMPANY_ADDRESS, companyAddress);

                //Get address for packet and put it on the request
                LongLatDTO packetAddress = trackerService.getPacketAddress(packetId);
                request.setAttribute(ATTR_PACKET_ADDRESS, packetAddress);

                //Get location updates for round where packet is part of and put them on request
                Collection<LocationUpdateDTO> locationUpdates = trackerService.getLocationUpdates(packetId);
                request.setAttribute(ATTR_LOCATION_UPDATES, locationUpdates);

                //Get remarks for round where packet is part of and put those on the request
                Collection<RemarkDTO> remarks = trackerService.getRemarks(packetId);
                request.setAttribute(ATTR_REMARKS, remarks);
            } catch (Exception e){
                //Remove possible attributes from the request and session
                request.getSession().setAttribute(ATTR_PACKET, null);
                request.setAttribute(ATTR_COMPANY_ADDRESS, null);
                request.setAttribute(ATTR_PACKET_ADDRESS, null);
                request.setAttribute(ATTR_LOCATION_UPDATES, null);
                request.setAttribute(ATTR_REMARKS, null);

                //Set an error flag on the request
                request.setAttribute(ATTR_ERROR, true);
            }
        }
        return "index";
    }

    @Override
    @RequestMapping(value = "/clear-packet", method = RequestMethod.GET)
    public String clearPacket(HttpServletRequest request) {
        request.getSession().setAttribute(ATTR_PACKET, null);
        return "index";
    }


}
