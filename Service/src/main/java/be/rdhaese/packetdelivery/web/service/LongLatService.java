package be.rdhaese.packetdelivery.web.service;

import be.rdhaese.packetdelivery.dto.AddressDTO;
import be.rdhaese.packetdelivery.dto.LongLatDTO;

/**
 * Created on 21/02/2016.
 *
 * @author Robin D'Haese
 */
public interface LongLatService {

    LongLatDTO getForAddress(AddressDTO addressDTO);
}
