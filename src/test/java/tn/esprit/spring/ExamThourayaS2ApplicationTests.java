package tn.esprit.spring;

import tn.esprit.spring.entities.Voyage;
import tn.esprit.spring.repositories.VoyageRepository;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ExamThourayaS2ApplicationTests {

	@Mock
    VoyageRepository sr;
    @InjectMocks
    VoyageServiceImpl ss;

    @Mock
    VoyageRepository voyageRepository;

   @InjectMocks
   VoyageServiceImpl voyageService;

    Voyage p1 = new Voyage(55L, 25L ,"tunis","SOUSSE",new Date(),new Date(),10,12,null,null);
    Voyage p2 = new Voyage(26L, 25L ,"tunis","SOUSSE",new Date(),new Date(),10,12,null,null);


    List<Voyage> listVoyages = new ArrayList<Voyage>() {
        {
            add(p1);
            add(new Voyage(12L, 48L ,"tunis","SOUSSE",new Date(),new Date(),10,12,null,null));
            add(new Voyage(23L, 25L ,"tunis","SOUSSE",new Date(),new Date(),10,12,null,null));
        }
    };



    @Test
    public void testRetrieveVoyage() {

        Mockito.when(voyageRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(p1));
        Voyage voyage1 = voyageService.retrieveVoyage(55L);
        assertNotNull(voyage1);
        System.out.println("1");
        assertEquals(voyage1.getIdVoyage(),55L);
   }

    @Test
    public void testretrieveAllVoyages() {
        Mockito.when(voyageRepository.findAll()).thenReturn(listVoyages);
        List<Voyage> listvoyage3 = voyageService.retrieveAllVoyages();
        assertEquals(3, listvoyage3.size());
        //assertEquals(produit1.getIdProduit(),55L);
        System.out.println("2555");
    }

    @Test
    public void testaddVoyage(){
        Mockito.when(voyageRepository.save(p1)).thenReturn(p1);
        Voyage voyage1 = voyageService.addVoyage(p1);
        //assertNotNull(produit1);
        Mockito.verify(voyageRepository, times(1)).save(Mockito.any(Voyage.class));
        System.out.println("3");
    }

}
