package be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.TrackerWebService;
import be.rdhaese.packetdelivery.dto.LocationUpdateDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import be.rdhaese.packetdelivery.dto.RemarkDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author Robin D'Haese
 */
public class TrackerControllerImplTest extends AbstractControllerTest {

    @Autowired //Mock, see TestConfig
    private TrackerWebService trackerWebService;

    @Before
    @Override
    public void setUp(){
        super.setUp();
        reset(trackerWebService);
    }

    @Test
    public void testTrackNoPacketId() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeDoesNotExist("packet"))
                .andExpect(model().attributeDoesNotExist("companyAddress"))
                .andExpect(model().attributeDoesNotExist("packetAddress"))
                .andExpect(model().attributeDoesNotExist("locationUpdates"))
                .andExpect(model().attributeDoesNotExist("amountOfPackets"))
                .andExpect(model().attributeDoesNotExist("remarks"))
                .andExpect(model().attributeDoesNotExist("error"));

        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeDoesNotExist("packet"))
                .andExpect(model().attributeDoesNotExist("companyAddress"))
                .andExpect(model().attributeDoesNotExist("packetAddress"))
                .andExpect(model().attributeDoesNotExist("locationUpdates"))
                .andExpect(model().attributeDoesNotExist("amountOfPackets"))
                .andExpect(model().attributeDoesNotExist("remarks"))
                .andExpect(model().attributeDoesNotExist("error"));
    }

    @Test
    public void testTrackPacketAsAttribute() throws Exception {
        LongLatDTO companyAddress = new LongLatDTO(2D, 3D);
        LongLatDTO packetAddress = new LongLatDTO(4D, 5D);
        LocationUpdateDTO locationUpdateDto1 = new LocationUpdateDTO(null, 2D, 3D);
        LocationUpdateDTO locationUpdateDto2 = new LocationUpdateDTO(null, 4D, 5D);
        Collection<LocationUpdateDTO> locationUpdates = Arrays.asList(locationUpdateDto1, locationUpdateDto2);
        RemarkDTO remarkDto1 = new RemarkDTO("desc1", null);
        RemarkDTO remarkDto2 = new RemarkDTO("desc2", null);
        Collection<RemarkDTO> remarks = Arrays.asList(remarkDto1, remarkDto2);

        when(trackerWebService.getCompanyAddress()).thenReturn(companyAddress);
        when(trackerWebService.getPacketAddress("packetId")).thenReturn(packetAddress);
        when(trackerWebService.getLocationUpdates("packetId")).thenReturn(locationUpdates);
        when(trackerWebService.getRemarks("packetId")).thenReturn(remarks);
        when(trackerWebService.getAmountOfPacketsLeftBefore("packetId")).thenReturn(2);

        HttpSession httpSession = mockMvc.perform(get("/?packetId=packetId"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("companyAddress", is(companyAddress)))
                .andExpect(model().attribute("packetAddress", is(packetAddress)))
                .andExpect(model().attribute("locationUpdates", is(locationUpdates)))
                .andExpect(model().attribute("remarks", is(remarks)))
                .andExpect(model().attribute("amountOfPackets", is(2)))
                .andReturn().getRequest().getSession();

        assertEquals("packetId", httpSession.getAttribute("packet"));

        verify(trackerWebService, times(1)).getCompanyAddress();
        verify(trackerWebService, times(1)).getPacketAddress("packetId");
        verify(trackerWebService, times(1)).getLocationUpdates("packetId");
        verify(trackerWebService, times(1)).getRemarks("packetId");
        verify(trackerWebService, times(1)).getAmountOfPacketsLeftBefore("packetId");
    }

    @Test
    public void testTrackWithError() throws Exception {
        when(trackerWebService.getCompanyAddress()).thenThrow(Exception.class);

        mockMvc.perform(get("/?packetId=packetId"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeDoesNotExist("packet"))
                .andExpect(model().attributeDoesNotExist("companyAddress"))
                .andExpect(model().attributeDoesNotExist("packetAddress"))
                .andExpect(model().attributeDoesNotExist("locationUpdates"))
                .andExpect(model().attributeDoesNotExist("amountOfPackets"))
                .andExpect(model().attributeDoesNotExist("remarks"))
                .andExpect(model().attribute("error", is(true)));
    }

    @Test
    public void testClearPacket() throws Exception {
        RequestBuilder requestBuilder = new RequestBuilder() {
            @Override
            public MockHttpServletRequest buildRequest(ServletContext servletContext) {
                MockHttpServletRequest request = new MockHttpServletRequest(servletContext, "GET", "/clear-packet");
                request.getSession().setAttribute("packet", "packetId");
                return request;
            }
        };

        HttpSession httpSession = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andReturn().getRequest().getSession();

        assertNull(httpSession.getAttribute("packet"));
    }
}
