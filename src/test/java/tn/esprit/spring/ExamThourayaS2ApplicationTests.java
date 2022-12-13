 package tn.esprit.spring.services; 

import org.junit.jupiter.api.Assertions;
 import org.junit.jupiter.api.Test;
 import org.junit.jupiter.api.extension.ExtendWith;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
 import org.springframework.boot.test.context.SpringBootTest;

 import tn.esprit.spring.entities.Voyage;
 import tn.esprit.spring.repository.VoyageRepository;
 import tn.esprit.spring.services.VoyageServiceImpl;


 import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.mockito.Mockito.times;
 import static org.mockito.Mockito.verify;
 import static org.mockito.Mockito.when;

 import java.util.ArrayList;
 import java.util.List;
 import java.util.Optional;
 import java.util.Date;

 @SpringBootTest
 @ExtendWith(MockitoExtension.class)
 public class ExamThourayaS2ApplicationTests {

     @Mock
     VoyageRepository voyageRepository;

     @InjectMocks
     VoyageServiceImpl voyageService;

     @Test
     public void testRetrieveVoyage() {

         Voyage voyage = new Voyage(1L, 10L, "tunis", "SOUSSE", new Date(), new Date(), 12, 10, null, null);

         voyage.setIdVoyage(1L);

         Mockito.when(voyageRepository.findById(1L)).thenReturn(Optional.of(voyage));
         voyageService.retrieveVoyage(1L);
         Assertions.assertNotNull(voyage);

         System.out.println(voyage);
         System.out.println(" Retrieve is working correctly...!!");

     }


    @Test
    public void createVoyagekTest()
     {
         Voyage voyage2 = new Voyage(2L, 10L, "tunis", "SOUSSE", new Date(), new Date(), 12, 10, null, null);
         voyage2.setIdVoyage(2L);

         voyageService.addVoyage(voyage2);
         verify(voyageRepository, times(1)).save(voyage2);
         System.out.println(voyage2);
         System.out.println(" Create is working correctly...!!");
     }


     @Test
  public   void getAllVoyageTest()
     {
         List<Voyage> Voyagelist = new ArrayList<Voyage>() {

             {
                add(new Voyage(3L, 10L, "tunis", "SOUSSE", new Date(), new Date(), 12, 10, null, null));
                 add(new Voyage(4L, 10L, "tunis", "SOUSSE", new Date(), new Date(), 12, 10, null, null));
                 add(new Voyage(5L, 10L, "tunis", "SOUSSE", new Date(), new Date(), 12, 10, null, null));
             }};


         when(voyageService.retrieveAllVoyage()).thenReturn(Voyagelist);
         //test
         List<Voyage> voyageList = voyageService.retrieveAllVoyage();
         assertEquals(3, voyageList.size());
         System.out.println(" Retrieve all is working correctly...!!");
     }




 }
