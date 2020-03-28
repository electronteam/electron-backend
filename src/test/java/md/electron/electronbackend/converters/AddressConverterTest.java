package md.electron.electronbackend.converters;

import md.electron.electronbackend.data.AddressData;
import md.electron.electronbackend.persistence.model.Address;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AddressConverter.class})
public class AddressConverterTest {

    private static final String TEST_CITY = "testCity";
    private static final String TEST_STREET = "testStreet";
    private static final Long TEST_ID = 1L;

    @Autowired
    AddressConverter addressConverter;

    Address addressStub;

    @Before
    public void Before() {
        addressStub = new Address();
        addressStub.setId(TEST_ID);
        addressStub.setCity(TEST_CITY);
        addressStub.setStreet(TEST_STREET);
    }

    @Test
    public void addressConvert() {
        AddressData result = addressConverter.convert(addressStub);
        assertEquals(TEST_CITY, result.getCity());
        assertEquals(TEST_STREET, result.getStreet());
    }

}
