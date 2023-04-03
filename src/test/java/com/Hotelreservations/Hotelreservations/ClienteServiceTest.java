package com.Hotelreservations.Hotelreservations;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.Hotelreservations.Hotelreservations.exception.ApiRequestException;
import com.Hotelreservations.Hotelreservations.model.Cliente;
import com.Hotelreservations.Hotelreservations.repository.ClienteRepository;
import com.Hotelreservations.Hotelreservations.service.ClienteService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void crearClienteValido() {
        Cliente cliente = new Cliente(123456789L, "Juan", "Pérez", "Calle 123", 30, "juan.perez@example.com");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente clienteCreado = clienteService.crear(cliente);

        verify(clienteRepository, times(1)).save(cliente);
        assertEquals(cliente, clienteCreado);
    }

    @Test(expected = ApiRequestException.class)
    public void crearClienteInvalido() {
        Cliente cliente = new Cliente(12345678L, "", "Pérez", "Calle 123", 30, "juan.perez@example.com");

        clienteService.crear(cliente);
    }
}
