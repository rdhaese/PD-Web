package be.rdhaese.packetdelivery.web.front_end.thymeleaf_implementation;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.ContactInformationWebService;
import be.rdhaese.packetdelivery.back_end.web_service.interfaces.LongLatWebService;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.ContactDetailsDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */

public class ContactControllerImplTest extends AbstractControllerTest {

    @Autowired //Mock, see TestConfig
    private ContactInformationWebService contactInformationWebService;

    @Autowired //Mock, see TestConfig
    private LongLatWebService longLatWebService;

    @Before
    @Override
    public void setUp(){
        super.setUp();
        reset(contactInformationWebService, longLatWebService);
    }
    @Test
    public void testGetContact() throws Exception {
        ContactDetailsDTO contactDetailsDto = new ContactDetailsDTO();
        contactDetailsDto.setStreet("street");
        contactDetailsDto.setNumber("number");
        contactDetailsDto.setCity("city");
        contactDetailsDto.setPostalCode("postalCode");
        AddressDTO addressDto = new AddressDTO("street", "number", null, "city", "postalCode");
        LongLatDTO longLatDto = new LongLatDTO(2D, 3D);

        when(contactInformationWebService.get()).thenReturn(contactDetailsDto);
        when(longLatWebService.getForAddress(addressDto)).thenReturn(longLatDto);

        mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(view().name("contact"))
                .andExpect(model().attribute("contactInformation", is(contactDetailsDto)))
                .andExpect(model().attribute("longLat", is(longLatDto)));

        verify(contactInformationWebService, times(1)).get();
        verify(longLatWebService, times(1)).getForAddress(addressDto);
    }

}
