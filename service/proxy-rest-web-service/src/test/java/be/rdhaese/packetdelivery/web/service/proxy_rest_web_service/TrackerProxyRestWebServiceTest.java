package be.rdhaese.packetdelivery.web.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.TrackerWebService;
import be.rdhaese.packetdelivery.dto.LocationUpdateDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.RemarkDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Collection;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */
public class TrackerProxyRestWebServiceTest extends AbstractProxyRestWebServiceTest {

    @Autowired
    private TrackerWebService trackerWebService;

    @Test
    public void testGetCompanyAddress() throws Exception {
        LongLatDTO longLatDto = new LongLatDTO(2D, 3D);

        server.expect(requestTo(getWithServerPath("tracker/company-address")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(convertObjectToJsonBytes(longLatDto), MediaType.APPLICATION_JSON_UTF8));

        assertEquals(longLatDto, trackerWebService.getCompanyAddress());
    }

    @Test
    public void testGetPacketAddressForPacketId() throws Exception {
        LongLatDTO longLatDto = new LongLatDTO(2D, 3D);

        server.expect(requestTo(getWithServerPath("tracker/packet-address/packetId")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(convertObjectToJsonBytes(longLatDto), MediaType.APPLICATION_JSON_UTF8));

        assertEquals(longLatDto, trackerWebService.getPacketAddress("packetId"));
    }

    @Test
    public void testGetLocationUpdatesForPacketId() throws Exception {
        LocationUpdateDTO locationUpdateDto1 = new LocationUpdateDTO(null, 2D, 3D);
        LocationUpdateDTO locationUpdateDto2 = new LocationUpdateDTO(null, 4D, 5D);
        Collection<LocationUpdateDTO> locationUpdateDTOs = Arrays.asList(locationUpdateDto1, locationUpdateDto2);

        server.expect(requestTo(getWithServerPath("tracker/location-updates/packetId")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(convertObjectToJsonBytes(locationUpdateDTOs), MediaType.APPLICATION_JSON_UTF8));

        assertEquals(2, trackerWebService.getLocationUpdates("packetId").size());
    }

    @Test
    public void testGetRemarksForPacketId() throws Exception {
        RemarkDTO remarkDto1 = new RemarkDTO("desc1", null);
        RemarkDTO remarkDto2 = new RemarkDTO("desc2", null);
        Collection<RemarkDTO> remarkDTOs = Arrays.asList(remarkDto1, remarkDto2);

        server.expect(requestTo(getWithServerPath("tracker/remarks/packetId")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(convertObjectToJsonBytes(remarkDTOs), MediaType.APPLICATION_JSON_UTF8));

        assertEquals(2, trackerWebService.getRemarks("packetId").size());
    }

    @Test
    public void testGetAmountOfPacketLeftBeforeForPacketId() throws Exception {
        server.expect(requestTo(getWithServerPath("tracker/packets-left-before/packetId")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("2", MediaType.APPLICATION_JSON_UTF8));

        assertEquals(2, trackerWebService.getAmountOfPacketsLeftBefore("packetId").intValue());
    }
}
