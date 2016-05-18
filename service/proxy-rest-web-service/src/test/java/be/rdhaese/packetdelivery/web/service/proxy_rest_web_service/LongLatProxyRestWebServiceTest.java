package be.rdhaese.packetdelivery.web.service.proxy_rest_web_service;

import be.rdhaese.packetdelivery.back_end.web_service.interfaces.LongLatWebService;
import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created on 18/05/2016.
 *
 * @author Robin D'Haese
 */
public class LongLatProxyRestWebServiceTest extends AbstractProxyRestWebServiceTest {

    @Autowired
    private LongLatWebService longLatWebService;

    @Test
    public void testGetForAddress() throws Exception {
        AddressDTO addressDTO = new AddressDTO("street", "number", null, "city", "postalCode");
        LongLatDTO longLatDTO = new LongLatDTO(2D, 3D);

        server.expect(requestTo(getWithServerPath("long-lat/for-address")))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.street").value("street"))
                .andRespond(withSuccess(convertObjectToJsonBytes(longLatDTO), MediaType.APPLICATION_JSON_UTF8));

        assertEquals(longLatDTO, longLatWebService.getForAddress(addressDTO));
    }
}
